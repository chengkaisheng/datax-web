package com.wugui.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wugui.datatx.core.biz.ExecutorBiz;
import com.wugui.datatx.core.biz.model.LogResult;
import com.wugui.datatx.core.biz.model.NetWork;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.enums.ExecutorBlockStrategyEnum;
import com.wugui.datatx.core.glue.GlueTypeEnum;
import com.wugui.datatx.core.log.JobFileAppender;
import com.wugui.datatx.core.util.DateUtil;
import com.wugui.datax.admin.core.conf.JobAdminConfig;
import com.wugui.datax.admin.core.cron.CronExpression;
import com.wugui.datax.admin.core.route.ExecutorRouteStrategyEnum;
import com.wugui.datax.admin.core.scheduler.JobScheduler;
import com.wugui.datax.admin.core.thread.JobScheduleHelper;
import com.wugui.datax.admin.core.thread.JobTriggerPoolHelper;
import com.wugui.datax.admin.core.trigger.TriggerTypeEnum;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.dto.DataXBatchJsonBuildDto;
import com.wugui.datax.admin.dto.DataXJsonBuildDto;
import com.wugui.datax.admin.dto.QualityConfDto;
import com.wugui.datax.admin.dto.QualityJsonBuildDto;
import com.wugui.datax.admin.entity.JobGroup;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobLogReport;
import com.wugui.datax.admin.entity.JobTemplate;
import com.wugui.datax.admin.entity.*;
import com.wugui.datax.admin.mapper.*;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.DataxJsonService;
import com.wugui.datax.admin.service.JobService;
import com.wugui.datax.admin.service.QualityJsonService;
import com.wugui.datax.admin.util.DateFormatUtils;
import com.wugui.datax.admin.util.NetWorkUtils;
import com.wugui.datax.admin.util.UUIDUtils;
import com.wugui.datax.admin.util.UploadUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.*;

/**
 * core job action for xxl-job
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
@Service
public class JobServiceImpl implements JobService {
    private static Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);

    @Resource
    private JobGroupMapper jobGroupMapper;
    @Resource
    private JobInfoMapper jobInfoMapper;
    @Resource
    private  JobInfoLinkMapper jobInfoLinkMapper;
    @Resource
    private JobInfoDetailMapper jobInfoDetailMapper;
    @Resource
    private JobLogMapper jobLogMapper;
    @Resource
    private JobLogGlueMapper jobLogGlueMapper;
    @Resource
    private JobLogReportMapper jobLogReportMapper;
    @Resource
    private DatasourceQueryService datasourceQueryService;
    @Resource
    private JobTemplateMapper jobTemplateMapper;
    @Resource
    private DataxJsonService dataxJsonService;
    @Resource
    private QualityJsonService qualityJsonService;

    @Override
    public Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String glueType, int userId, Integer[] projectIds) {

        // page list
        List<JobInfo> list = jobInfoMapper.pageList(start, length, jobGroup, triggerStatus, jobDesc, glueType, userId, projectIds);
        int list_count = jobInfoMapper.pageListCount(start, length, jobGroup, triggerStatus, jobDesc, glueType, userId, projectIds);

        // package result
        Map<String, Object> maps = new HashMap<>();
        maps.put("recordsTotal", list_count);        // 总记录数
        maps.put("recordsFiltered", list_count);    // 过滤后的总记录数
        maps.put("data", list);                    // 分页列表
        return maps;
    }

    public List<JobInfo> list() {
        return jobInfoMapper.findAll();
    }

    @Override
    public ReturnT<String> add(JobInfo jobInfo) {
        //在添加任务之前 先构建json
        String jobParam = jobInfo.getJobParam();
        String jobType = jobInfo.getJobType();
        String jobJson = null;
        if(jobType.equals("DQCJOB")){
            //质量任务
            QualityJsonBuildDto qualityJsonBuildDto = JSON.parseObject(jobParam,QualityJsonBuildDto.class);
            checkParam(qualityJsonBuildDto);
            jobJson = qualityJsonService.buildJobJson(qualityJsonBuildDto);

        }else{
            //其他任务
            DataXJsonBuildDto dataXJsonBuildDto = JSON.parseObject(jobParam,DataXJsonBuildDto.class);
            checkParam(dataXJsonBuildDto);
            jobJson = dataxJsonService.buildJobJson(dataXJsonBuildDto);
        }

        // valid
        JobGroup group = jobGroupMapper.load(jobInfo.getJobGroup());
        if (group == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_jobgroup")));
        }
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        /*if (jobInfo.getGlueType().equals(GlueTypeEnum.BEAN.getDesc()) && jobInfo.getJobJson().trim().length() <= 2) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobjson")));
        }*/
        if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
        }
        if (jobInfo.getUserId() == 0 ) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (GlueTypeEnum.match(jobInfo.getGlueType()) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_gluetype") + I18nUtil.getString("system_invalid")));
        }
        if (GlueTypeEnum.BEAN == GlueTypeEnum.match(jobInfo.getGlueType()) && (jobInfo.getExecutorHandler() == null || jobInfo.getExecutorHandler().trim().length() == 0)) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + "JobHandler"));
        }


        if (StringUtils.isBlank(jobInfo.getReplaceParamType()) || !DateFormatUtils.formatList().contains(jobInfo.getReplaceParamType())) {
            jobInfo.setReplaceParamType(DateFormatUtils.TIMESTAMP);
        }

        // fix "\r" in shell
        if (GlueTypeEnum.GLUE_SHELL == GlueTypeEnum.match(jobInfo.getGlueType()) && jobInfo.getGlueSource() != null) {
            jobInfo.setGlueSource(jobInfo.getGlueSource().replaceAll("\r", ""));
        }

        // ChildJobId valid
        if (jobInfo.getChildJobId() != null && jobInfo.getChildJobId().trim().length() > 0) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (String childJobIdItem : childJobIds) {
                if (StringUtils.isNotBlank(childJobIdItem) && isNumeric(childJobIdItem) && Integer.parseInt(childJobIdItem) > 0) {
                    JobInfo childJobInfo = jobInfoMapper.loadById(Integer.parseInt(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_invalid")), childJobIdItem));
                }
            }

            // join , avoid "xxx,,"
            String temp = "";
            for (String item : childJobIds) {
                temp += item + ",";
            }
            temp = temp.substring(0, temp.length() - 1);

            jobInfo.setChildJobId(temp);
        }

        // add in db
        jobInfo.setAddTime(new Date());
        jobInfo.setJobJson(jobInfo.getJobJson());
        jobInfo.setUpdateTime(new Date());
        jobInfo.setGlueUpdatetime(new Date());
        jobInfo.setJobJson(jobJson);
        jobInfo.setJobParam(jobParam);
        jobInfoMapper.save(jobInfo);
        
        if (jobInfo.getId() < 1) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_add") + I18nUtil.getString("system_fail")));
        }

        return new ReturnT<>(String.valueOf(jobInfo.getId()));
    }

    private ReturnT<String> checkParam(DataXJsonBuildDto dto){
        String key = "system_please_choose";
        //对入参的校验
        if (dto.getReaderDatasourceId() == null) {
            return new ReturnT<>(I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_readerDataSource"));
        }
        if (dto.getWriterDatasourceId() == null) {
            return new ReturnT<>(I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_writerDataSource"));
        }
        if (CollectionUtils.isEmpty(dto.getReaderColumns())) {
            return new ReturnT<>(I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_readerColumns"));
        }
        if (CollectionUtils.isEmpty(dto.getWriterColumns())) {
            return new ReturnT<>(I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_writerColumns"));
        }
        return null;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public ReturnT<String> update(JobInfo jobInfo) {

        // valid
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        if (jobInfo.getGlueType().equals(GlueTypeEnum.BEAN.getDesc()) && jobInfo.getJobJson().trim().length() <= 2) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobjson")));
        }
        if (jobInfo.getJobDesc() == null || jobInfo.getJobDesc().trim().length() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobdesc")));
        }

        if (jobInfo.getProjectId() == 0) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobproject")));
        }
        if (jobInfo.getUserId() == 0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_author")));
        }
        if (ExecutorRouteStrategyEnum.match(jobInfo.getExecutorRouteStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorRouteStrategy") + I18nUtil.getString("system_invalid")));
        }
        if (ExecutorBlockStrategyEnum.match(jobInfo.getExecutorBlockStrategy(), null) == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_executorBlockStrategy") + I18nUtil.getString("system_invalid")));
        }

        // ChildJobId valid
        if (jobInfo.getChildJobId() != null && jobInfo.getChildJobId().trim().length() > 0) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (String childJobIdItem : childJobIds) {
                if (childJobIdItem != null && childJobIdItem.trim().length() > 0 && isNumeric(childJobIdItem)) {
                    JobInfo childJobInfo = jobInfoMapper.loadById(Integer.parseInt(childJobIdItem));
                    if (childJobInfo == null) {
                        return new ReturnT<String>(ReturnT.FAIL_CODE,
                                MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_not_found")), childJobIdItem));
                    }
                } else {
                    return new ReturnT<String>(ReturnT.FAIL_CODE,
                            MessageFormat.format((I18nUtil.getString("jobinfo_field_childJobId") + "({0})" + I18nUtil.getString("system_invalid")), childJobIdItem));
                }
            }

            // join , avoid "xxx,,"
            String temp = "";
            for (String item : childJobIds) {
                temp += item + ",";
            }
            temp = temp.substring(0, temp.length() - 1);

            jobInfo.setChildJobId(temp);
        }

        //job json valid
        String jobParam = jobInfo.getJobParam();
        String jobType = jobInfoMapper.loadById(jobInfo.getId()).getJobType();
        String jobJson = null;
        if(jobType.equals("DQCJOB")){
            //质量任务
            QualityJsonBuildDto qualityJsonBuildDto = JSON.parseObject(jobParam,QualityJsonBuildDto.class);
            checkParam(qualityJsonBuildDto);
            jobJson = qualityJsonService.buildJobJson(qualityJsonBuildDto);

        }else{
            //其他任务
            DataXJsonBuildDto dataXJsonBuildDto = JSON.parseObject(jobParam,DataXJsonBuildDto.class);
            checkParam(dataXJsonBuildDto);
            jobJson = dataxJsonService.buildJobJson(dataXJsonBuildDto);
        }
        jobInfo.setJobJson(jobJson);

        // group valid
        JobGroup jobGroup = jobGroupMapper.load(jobInfo.getJobGroup());
        if (jobGroup == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_jobgroup") + I18nUtil.getString("system_invalid")));
        }

        // stage job info
        JobInfo exists_jobInfo = jobInfoMapper.loadById(jobInfo.getId());
        if (exists_jobInfo == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("jobinfo_field_id") + I18nUtil.getString("system_not_found")));
        }

        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = exists_jobInfo.getTriggerNextTime();
        if (exists_jobInfo.getTriggerStatus() == 1 && !jobInfo.getJobCron().equals(exists_jobInfo.getJobCron())) {
            try {
                Date nextValidTime = new CronExpression(jobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
                if (nextValidTime == null) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_never_fire"));
                }
                nextTriggerTime = nextValidTime.getTime();
            } catch (ParseException e) {
                logger.error(e.getMessage(), e);
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid") + " | " + e.getMessage());
            }
        }

        BeanUtils.copyProperties(jobInfo, exists_jobInfo);
        if (StringUtils.isBlank(jobInfo.getReplaceParamType())) {
            jobInfo.setReplaceParamType(DateFormatUtils.TIMESTAMP);
        }
        exists_jobInfo.setTriggerNextTime(nextTriggerTime);
        exists_jobInfo.setUpdateTime(new Date());
        exists_jobInfo.setJobParam(jobInfo.getJobParam());

        if (GlueTypeEnum.BEAN.getDesc().equals(jobInfo.getGlueType())) {
            exists_jobInfo.setJobJson(jobInfo.getJobJson());
            exists_jobInfo.setGlueSource(null);
        } else {
            exists_jobInfo.setGlueSource(jobInfo.getGlueSource());
            exists_jobInfo.setJobJson(null);
        }
        exists_jobInfo.setGlueUpdatetime(new Date());
        jobInfoMapper.update(exists_jobInfo);


        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> remove(int id) {
        JobInfo xxlJobInfo = jobInfoMapper.loadById(id);
        if (xxlJobInfo == null) {
            return ReturnT.SUCCESS;
        }

        jobInfoMapper.delete(id);
        jobLogMapper.delete(id);
        jobLogGlueMapper.deleteByJobId(id);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> start(int id) {
        JobInfo xxlJobInfo = jobInfoMapper.loadById(id);

        // next trigger time (5s后生效，避开预读周期)
        long nextTriggerTime = 0;
        try {
            Date nextValidTime = new CronExpression(xxlJobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
            if (nextValidTime == null) {
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_never_fire"));
            }
            nextTriggerTime = nextValidTime.getTime();
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid") + " | " + e.getMessage());
        }

        xxlJobInfo.setTriggerStatus(1);
        xxlJobInfo.setTriggerLastTime(0);
        xxlJobInfo.setTriggerNextTime(nextTriggerTime);

        xxlJobInfo.setUpdateTime(new Date());
        jobInfoMapper.update(xxlJobInfo);
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<String> stop(int id) {
        JobInfo jobInfo = jobInfoMapper.loadById(id);

        jobInfo.setTriggerStatus(0);
        jobInfo.setTriggerLastTime(0);
        jobInfo.setTriggerNextTime(0);

        jobInfo.setUpdateTime(new Date());
        jobInfoMapper.update(jobInfo);
        return ReturnT.SUCCESS;
    }

    @Override
    public Map<String, Object> dashboardInfo() {

        int jobInfoCount = jobInfoMapper.findAllCount();
        int jobLogCount = 0;
        int jobLogSuccessCount = 0;
        JobLogReport jobLogReport = jobLogReportMapper.queryLogReportTotal();
        if (jobLogReport != null) {
            jobLogCount = jobLogReport.getRunningCount() + jobLogReport.getSucCount() + jobLogReport.getFailCount();
            jobLogSuccessCount = jobLogReport.getSucCount();
        }

        // executor count
        Set<String> executorAddressSet = new HashSet<>();
        List<JobGroup> groupList = jobGroupMapper.findAll();

        if (groupList != null && !groupList.isEmpty()) {
            for (JobGroup group : groupList) {
                if (group.getRegistryList() != null && !group.getRegistryList().isEmpty()) {
                    executorAddressSet.addAll(group.getRegistryList());
                }
            }
        }

        int executorCount = executorAddressSet.size();

        Map<String, Object> dashboardMap = new HashMap<>();
        dashboardMap.put("jobInfoCount", jobInfoCount);
        dashboardMap.put("jobLogCount", jobLogCount);
        dashboardMap.put("jobLogSuccessCount", jobLogSuccessCount);
        dashboardMap.put("executorCount", executorCount);
        return dashboardMap;
    }

    @Override
    public ReturnT<Map<String, Object>> chartInfo() {
        // process
        List<String> triggerDayList = new ArrayList<String>();
        List<Integer> triggerDayCountRunningList = new ArrayList<Integer>();
        List<Integer> triggerDayCountSucList = new ArrayList<Integer>();
        List<Integer> triggerDayCountFailList = new ArrayList<Integer>();
        int triggerCountRunningTotal = 0;
        int triggerCountSucTotal = 0;
        int triggerCountFailTotal = 0;

        List<JobLogReport> logReportList = jobLogReportMapper.queryLogReport(DateUtil.addDays(new Date(), -7), new Date());

        if (logReportList != null && logReportList.size() > 0) {
            for (JobLogReport item : logReportList) {
                String day = DateUtil.formatDate(item.getTriggerDay());
                int triggerDayCountRunning = item.getRunningCount();
                int triggerDayCountSuc = item.getSucCount();
                int triggerDayCountFail = item.getFailCount();

                triggerDayList.add(day);
                triggerDayCountRunningList.add(triggerDayCountRunning);
                triggerDayCountSucList.add(triggerDayCountSuc);
                triggerDayCountFailList.add(triggerDayCountFail);

                triggerCountRunningTotal += triggerDayCountRunning;
                triggerCountSucTotal += triggerDayCountSuc;
                triggerCountFailTotal += triggerDayCountFail;
            }
        } else {
            for (int i = -6; i <= 0; i++) {
                triggerDayList.add(DateUtil.formatDate(DateUtil.addDays(new Date(), i)));
                triggerDayCountRunningList.add(0);
                triggerDayCountSucList.add(0);
                triggerDayCountFailList.add(0);
            }
        }

        Map<String, Object> result = new HashMap<>();
        result.put("triggerDayList", triggerDayList);
        result.put("triggerDayCountRunningList", triggerDayCountRunningList);
        result.put("triggerDayCountSucList", triggerDayCountSucList);
        result.put("triggerDayCountFailList", triggerDayCountFailList);

        result.put("triggerCountRunningTotal", triggerCountRunningTotal);
        result.put("triggerCountSucTotal", triggerCountSucTotal);
        result.put("triggerCountFailTotal", triggerCountFailTotal);

        return new ReturnT<>(result);
    }


    @Override
    public ReturnT<String> batchAdd(DataXBatchJsonBuildDto dto) throws IOException {

        String key = "system_please_choose";
        List<String> rdTables = dto.getReaderTables();
        List<String> wrTables = dto.getWriterTables();
        if (dto.getReaderDatasourceId() == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_readerDataSource"));
        }
        if (dto.getWriterDatasourceId() == null) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString(key) + I18nUtil.getString("jobinfo_field_writerDataSource"));
        }
        if (rdTables.size() != wrTables.size()) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("json_build_inconsistent_number_r_w_tables"));
        }

        DataXJsonBuildDto jsonBuild = new DataXJsonBuildDto();

        List<String> rColumns;
        List<String> wColumns;
        for (int i = 0; i < rdTables.size(); i++) {
            rColumns = datasourceQueryService.getColumns(dto.getReaderDatasourceId(), rdTables.get(i));
            wColumns = datasourceQueryService.getColumns(dto.getWriterDatasourceId(), wrTables.get(i));

            jsonBuild.setReaderDatasourceId(dto.getReaderDatasourceId());
            jsonBuild.setWriterDatasourceId(dto.getWriterDatasourceId());

            jsonBuild.setReaderColumns(rColumns);
            jsonBuild.setWriterColumns(wColumns);

            jsonBuild.setRdbmsReader(dto.getRdbmsReader());
            jsonBuild.setRdbmsWriter(dto.getRdbmsWriter());

            List<String> rdTable = new ArrayList<>();
            rdTable.add(rdTables.get(i));
            jsonBuild.setReaderTables(rdTable);

            List<String> wdTable = new ArrayList<>();
            wdTable.add(wrTables.get(i));
            jsonBuild.setWriterTables(wdTable);

            String json = dataxJsonService.buildJobJson(jsonBuild);

            JobTemplate jobTemplate = jobTemplateMapper.loadById(dto.getTemplateId());
            JobInfo jobInfo = new JobInfo();
            BeanUtils.copyProperties(jobTemplate, jobInfo);
            jobInfo.setJobJson(json);
            jobInfo.setJobDesc(rdTables.get(i));
            jobInfo.setAddTime(new Date());
            jobInfo.setUpdateTime(new Date());
            jobInfo.setGlueUpdatetime(new Date());
            jobInfoMapper.save(jobInfo);
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public Map<String, Object> getPageConf(int current, int size, String name) {
        Map<String,Object> map = new HashMap<>();
        JSONObject jsonObject1 = null;
        JSONObject jsonObject2 = null;
        //先查询出所有的质量任务
        List<QualityConfDto> list = jobInfoMapper.pageConfList((current-1) * size,size,name,"DQCJOB");
        int count = jobInfoMapper.selectConfCount((current-1) * size,size,name,"DQCJOB");
        for (int i = 0 ; i < list.size(); i++){
            jsonObject1 = JSONObject.parseObject(list.get(i).getJobJson());
            String reader = jsonObject1.getJSONObject("job").getJSONArray("content").get(0).toString();
            logger.info("reader={}",reader);
            jsonObject2 = JSONObject.parseObject(reader);
            String querySql = jsonObject2.getJSONObject("reader").getJSONObject("parameter").
                    getJSONArray("connection").getJSONObject(0).getJSONArray("querySql").get(0).toString();
            logger.info("querySql={}",querySql);

        }
        return null;
    }

    @Override
    public ReturnT<String> addVirtualTask(JobInfo jobInfo) {
        ReturnT<String> result=null;
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "请填写正确的Cron表达式!");
        }
        if (UUIDUtils.isEmpty(jobInfo.getJobJson())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "流程图信息为空!");
        }
        if (UUIDUtils.isEmpty(jobInfo.getJobDesc())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "任务名称不能为空!");
        }
        if (jobInfo.getProjectId()<=0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, "任务所属的项目Id不能为空!");
        }
        jobInfo.setJobGroup(1);
        // add in db
        jobInfo.setAddTime(new Date());
        jobInfo.setUpdateTime(new Date());
        jobInfo.setGlueUpdatetime(new Date());
        jobInfo.setExecutorRouteStrategy("RANDOM");
        jobInfo.setGlueType("BEAN");
        if (jobInfo.getId() < 1) {
            jobInfo.setTriggerStatus(0);
            int n=jobInfoMapper.save(jobInfo);
            if(n>0){
                result= new ReturnT<>(ReturnT.SUCCESS_CODE,"保存成功");
            }else {
                result= new ReturnT<>(ReturnT.FAIL_CODE, "保存失败");
            }
        }else {
            result=updateVirtualTask(jobInfo);
        }
        return result;
    }

    @Override
    public ReturnT<String> updateVirtualTask(JobInfo jobInfo) {
        JobInfo exists_jobInfo = jobInfoMapper.loadById(jobInfo.getId());
        if(!UUIDUtils.notEmpty(exists_jobInfo)){
            return new ReturnT<>(ReturnT.FAIL_CODE, "没有当前任务信息,信息已被更改,请重新提交");
        }
        // 获取下次执行时间 (5s后生效，避开预读周期)
        long nextTriggerTime = jobInfo.getTriggerNextTime();
        if (!jobInfo.getJobCron().equals(exists_jobInfo.getJobCron())) {
            try {
                Date nextValidTime = new CronExpression(jobInfo.getJobCron()).getNextValidTimeAfter(new Date(System.currentTimeMillis() + JobScheduleHelper.PRE_READ_MS));
                if (nextValidTime == null) {
                    return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_never_fire"));
                }
                nextTriggerTime = nextValidTime.getTime();
            } catch (ParseException e) {
                logger.error(e.getMessage(), e);
                return new ReturnT<String>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid")+ " | " + e.getMessage());
            }
        }

        BeanUtils.copyProperties(jobInfo, exists_jobInfo);
        exists_jobInfo.setTriggerNextTime(nextTriggerTime);
        int n=jobInfoMapper.update(exists_jobInfo);
        if(n>0){
            return new ReturnT<>(ReturnT.SUCCESS_CODE,"保存成功");
        }else {
            return new ReturnT<>(ReturnT.FAIL_CODE, "保存失败");
        }
    }

    @Override
    public ReturnT<String> triggerVirtualTask(JobInfo jobInfo) {
        JobInfo jobInfoVirtualTask=jobInfoMapper.loadById(jobInfo.getId());
        String jobInfoId=UUIDUtils.getUUID();
        NetWorkUtils.triggerVirtualTask(jobInfoId,jobInfoVirtualTask);
        List<JobInfoLink> scheduleList=new ArrayList<>();
        scheduleList = jobInfoLinkMapper.loadTriggerVirtualTask(jobInfoId);
        if (scheduleList != null && scheduleList.size() > 0) {
            JobLog jobLog= NetWorkUtils.createVirtualLog(jobInfoVirtualTask);
            for (JobInfoLink jobInfoLink : scheduleList) {
                JobTriggerPoolHelper.trigger(jobInfoLink.getId(),TriggerTypeEnum.MANUAL, -1, null, "",jobInfoId,jobInfoLink.getInfoId(),jobLog.getId());
            }
        }
        return ReturnT.SUCCESS;
    }

    @Override
    public ReturnT<LogResult> loadingVirtualLog(String log,JobLog virtualJobLog) {
        try{
            LogResult virtualLogResult=new LogResult();
            virtualLogResult.setEnd(false);
            virtualLogResult.setFromLineNum(1);
            String str="------------------------------------------------------------------虚任务开始执行"+"---------------------------------当前任务Id为："+virtualJobLog.getJobId()+"---------------------------------任务名称为："+virtualJobLog.getJobDesc()+"\r\n\n\n";
            log = log.replaceAll("(\\\r\\\n|\\\r|\\\n|\\\n\\\r)", "");
            String[] logId = log.split(",");
            int toLineNum=0;
            for (int i = 0; i < logId.length; i++) {
                long virtualLogId = (logId[i] != null && logId[i].trim().length() > 0 && isNumeric(logId[i])) ? Long.parseLong(logId[i]): -1;
                if(virtualLogId>0){
                    JobLog jobLog=jobLogMapper.load(virtualLogId);
                    long triggerTime=jobLog.getTriggerTime().getTime();
                    String logFileName = JobFileAppender.makeLogFileName(new Date(triggerTime), virtualLogId);
                    LogResult logResult = JobFileAppender.readLog("/root/datax-web-2.1.2/modules/datax-executor"+logFileName, 1);
                    str+="---------------------------------子任务Id:"+jobLog.getJobId()+"---------------------------------子任务名称:"+jobLog.getJobDesc()+"开始执行---------------------------------\n"
                            +logResult.getLogContent()+"---------------------------------子任务Id:"+jobLog.getJobId()+"---------------------------------子任务名称:"+jobLog.getJobDesc()+"执行结束---------------------------------"+"\r\n\n\n";
                    toLineNum+=logResult.getToLineNum();
                }
            }
            virtualLogResult.setToLineNum(toLineNum);
            virtualLogResult.setLogContent(str);
            return new ReturnT<>(virtualLogResult);
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            return new ReturnT<>(ReturnT.FAIL_CODE, e.getMessage());
        }
    }


}
