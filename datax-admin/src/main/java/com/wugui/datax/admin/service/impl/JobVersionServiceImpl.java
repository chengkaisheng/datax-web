package com.wugui.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.enums.ExecutorBlockStrategyEnum;
import com.wugui.datatx.core.glue.GlueTypeEnum;
import com.wugui.datax.admin.constans.OperationType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.core.cron.CronExpression;
import com.wugui.datax.admin.core.route.ExecutorRouteStrategyEnum;
import com.wugui.datax.admin.core.thread.JobScheduleHelper;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.dto.DataXJsonBuildDto;
import com.wugui.datax.admin.dto.QualityJsonBuildDto;
import com.wugui.datax.admin.entity.JobGroup;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobProjectGroup;
import com.wugui.datax.admin.entity.JobVersion;
import com.wugui.datax.admin.mapper.JobGroupMapper;
import com.wugui.datax.admin.mapper.JobInfoMapper;
import com.wugui.datax.admin.mapper.JobVersionMapper;
import com.wugui.datax.admin.service.DataxJsonService;
import com.wugui.datax.admin.service.JobProjectGroupService;
import com.wugui.datax.admin.service.JobVersionService;
import com.wugui.datax.admin.service.QualityJsonService;
import com.wugui.datax.admin.util.DateFormatUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author hf
 * @creat 2021-02-02-15:04
 */
@Service
public class JobVersionServiceImpl extends ServiceImpl<JobVersionMapper, JobVersion> implements JobVersionService {
    private static Logger logger = LoggerFactory.getLogger(JobVersionServiceImpl.class);
    @Autowired
    private JobVersionMapper jobVersionMapper;
    @Autowired
    private JobVersionService jobVersionService;
    @Autowired
    private JobInfoMapper jobInfoMapper;
    @Autowired
    private QualityJsonService qualityJsonService;
    @Resource
    private DataxJsonService dataxJsonService;
    @Resource
    private JobGroupMapper jobGroupMapper;
    @Resource
    private JobProjectGroupService jobProjectGroupService;

    @Override
    public List<JobVersion> pageList(int jobId) {
        QueryWrapper<JobVersion> queryWrapper=new QueryWrapper<>();
        List<JobVersion> jobVersionList=this.list(queryWrapper.eq("job_id",jobId));
        return jobVersionList;
    }

    @Override
    public String saveVersion(JobInfo jobInfo) {
        JobVersion jobVersion=new JobVersion();
        jobVersion.setJobId(jobInfo.getId());
        jobVersion.setOperation(OperationType.UPDATE_OPERATION);
        jobVersion.setVersionTime(new Date());
        List<JobVersion> list = jobVersionService.list(new QueryWrapper<JobVersion>().eq("job_id", jobInfo.getId()).orderByDesc("version"));
        if(list != null && list.size() != 0){
            jobVersion.setVersion(list.get(0).getVersion() + 1);
        }else {
            jobVersion.setVersion(0);
        }
        jobVersion.setJobGroup(jobInfo.getJobGroup());
        jobVersion.setJobCron(jobInfo.getJobCron());
        jobVersion.setJobDesc(jobInfo.getJobDesc());
        jobVersion.setProjectId(jobInfo.getProjectId());
        jobVersion.setAddTime(jobInfo.getAddTime());
        jobVersion.setUpdateTime(jobInfo.getUpdateTime());
        jobVersion.setUserId(jobInfo.getUserId());
        jobVersion.setAlarmEmail(jobInfo.getAlarmEmail());
        jobVersion.setExecutorRouteStrategy(jobInfo.getExecutorRouteStrategy());
        jobVersion.setExecutorHandler(jobInfo.getExecutorHandler());
        jobVersion.setExecutorParam(jobInfo.getExecutorParam());
        jobVersion.setExecutorBlockStrategy(jobInfo.getExecutorBlockStrategy());
        jobVersion.setExecutorTimeout(jobInfo.getExecutorTimeout());
        jobVersion.setExecutorFailRetryCount(jobInfo.getExecutorFailRetryCount());
        jobVersion.setGlueType(jobInfo.getGlueType());
        jobVersion.setGlueSource(jobInfo.getGlueSource());
        jobVersion.setGlueRemark(jobInfo.getGlueRemark());
        jobVersion.setGlueUpdatetime(jobInfo.getGlueUpdatetime());
        jobVersion.setChildJobId(jobInfo.getChildJobId());
        jobVersion.setJobJson(jobInfo.getJobJson());
        jobVersion.setReplaceParam(jobInfo.getReplaceParam());
        jobVersion.setJvmParam(jobInfo.getJvmParam());
        jobVersion.setIncStartTime(jobInfo.getIncStartTime());
        jobVersion.setPartitionInfo(jobInfo.getPartitionInfo());
        jobVersion.setLastHandleCode(jobInfo.getLastHandleCode());
        jobVersion.setReplaceParamType(jobInfo.getReplaceParamType());
        jobVersion.setReaderTable(jobInfo.getReaderTable());
        jobVersion.setPrimaryKey(jobInfo.getPrimaryKey());
        jobVersion.setIncStartId(jobInfo.getIncStartId());
        jobVersion.setIncrementType(jobInfo.getIncrementType());
        jobVersion.setDatasourceId(jobInfo.getDatasourceId());
        jobVersion.setJobType(jobInfo.getJobType());
        jobVersion.setJobParam(jobInfo.getJobParam());
        boolean n=this.save(jobVersion);
        if (n){
            return "成功";
        }else {
            return "失败";
        }
    }

    @Override
    public ReturnT<String> rollBackVersion(JobInfo jobInfo) {
        // valid
        if (!CronExpression.isValidExpression(jobInfo.getJobCron())) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        /*if (jobInfo.getGlueType().equals(GlueTypeEnum.BEAN.getDesc()) && jobInfo.getJobJson().trim().length() <= 2) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_input") + I18nUtil.getString("jobinfo_field_jobjson")));
        }*/
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
        JobProjectGroup jobProjectGroup=new JobProjectGroup();
        jobProjectGroup.setId(exists_jobInfo.getProjectGroupId());
        jobProjectGroup.setName(exists_jobInfo.getJobDesc());
        jobProjectGroupService.updateById(jobProjectGroup);
        return new  ReturnT<>("回滚成功!");
    }

    private boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
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
}
