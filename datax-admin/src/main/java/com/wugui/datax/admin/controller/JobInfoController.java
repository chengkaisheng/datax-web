package com.wugui.datax.admin.controller;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.util.DateUtil;
import com.wugui.datax.admin.core.cron.CronExpression;
import com.wugui.datax.admin.core.thread.JobTriggerPoolHelper;
import com.wugui.datax.admin.core.trigger.TriggerTypeEnum;
import com.wugui.datax.admin.core.util.I18nUtil;
import com.wugui.datax.admin.dto.DataXBatchJsonBuildDto;
import com.wugui.datax.admin.dto.TriggerJobDto;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobInfoDetail;
import com.wugui.datax.admin.service.JobService;
import com.wugui.datax.admin.util.ExcelData;
import com.wugui.datax.admin.util.ExportExcelUtils;
import com.wugui.datax.admin.util.PageUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * index controller
 *
 * @author xuxueli 2015-12-19 16:13:16
 */
@Api(tags = "任务配置接口")
@RestController
@RequestMapping("/api/job")
public class JobInfoController extends BaseController{

    @Resource
    private JobService jobService;


    @GetMapping("/pageList")
    @ApiOperation("任务列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "0") int current,
                                        @RequestParam(required = false, defaultValue = "10") int size,
                                        int jobGroup, int triggerStatus, String jobDesc, String glueType, Integer[] projectIds) {

        return new ReturnT<>(jobService.pageList((current-1)*size, size, jobGroup, triggerStatus, jobDesc, glueType, 0, projectIds));
    }

    @GetMapping("/list")
    @ApiOperation("全部任务列表")
    public ReturnT<List<JobInfo>> list(){
        return new ReturnT<>(jobService.list());
    }

    @PostMapping("/add")
    @ApiOperation("添加任务")
    public ReturnT<String> add(HttpServletRequest request, @RequestBody JobInfo jobInfo) {
        jobInfo.setUserId(getCurrentUserId(request));
        return jobService.add(jobInfo);
    }

    @PostMapping("/update")
    @ApiOperation("更新任务")
    public ReturnT<String> update(HttpServletRequest request,@RequestBody JobInfo jobInfo) {
        jobInfo.setUserId(getCurrentUserId(request));
        return jobService.update(jobInfo);
    }

    @PostMapping(value = "/remove/{id}")
    @ApiOperation("移除任务")
    public ReturnT<String> remove(@PathVariable(value = "id") int id) {
        return jobService.remove(id);
    }

    @RequestMapping(value = "/stop",method = RequestMethod.POST)
    @ApiOperation("停止任务")
    public ReturnT<String> pause(int id) {
        return jobService.stop(id);
    }

    @RequestMapping(value = "/start",method = RequestMethod.POST)
    @ApiOperation("开启任务")
    public ReturnT<String> start(int id) {
        return jobService.start(id);
    }

    @PostMapping(value = "/trigger")
    @ApiOperation("触发任务")
    public ReturnT<String> triggerJob(@RequestBody TriggerJobDto dto) throws IOException {
        // force cover job param
        String executorParam=dto.getExecutorParam();
        if (executorParam == null) {
            executorParam = "";
        }
        JobTriggerPoolHelper.trigger(dto.getJobId(), TriggerTypeEnum.MANUAL, -1, null, executorParam,null,null,0);
        return ReturnT.SUCCESS;
    }

    @GetMapping("/nextTriggerTime")
    @ApiOperation("获取近5次触发时间")
    public ReturnT<List<String>> nextTriggerTime(String cron) {
        List<String> result = new ArrayList<>();
        try {
            CronExpression cronExpression = new CronExpression(cron);
            Date lastTime = new Date();
            for (int i = 0; i < 5; i++) {
                lastTime = cronExpression.getNextValidTimeAfter(lastTime);
                if (lastTime != null) {
                    result.add(DateUtil.formatDateTime(lastTime));
                } else {
                    break;
                }
            }
        } catch (ParseException e) {
            return new ReturnT<>(ReturnT.FAIL_CODE, I18nUtil.getString("jobinfo_field_cron_invalid"));
        }
        return new ReturnT<>(result);
    }

    @PostMapping("/batchAdd")
    @ApiOperation("批量创建任务")
    public ReturnT<String> batchAdd(@RequestBody DataXBatchJsonBuildDto dto) throws IOException {
        if (dto.getTemplateId() ==0) {
            return new ReturnT<>(ReturnT.FAIL_CODE, (I18nUtil.getString("system_please_choose") + I18nUtil.getString("jobinfo_field_temp")));
        }
        return jobService.batchAdd(dto);
    }

    @PostMapping("/addVirtualTask")
    @ApiOperation("保存虚任务")
    public ReturnT<String> addVirtualTask(HttpServletRequest request, @RequestBody JobInfo jobInfo) {
        jobInfo.setUserId(getCurrentUserId(request));
        return jobService.addVirtualTask(jobInfo);
    }

    @PostMapping("/updateVirtualTask")
    @ApiOperation("更新虚任务")
    public ReturnT<String> updateVirtualTask(HttpServletRequest request,@RequestBody JobInfo jobInfo) {
        return jobService.updateVirtualTask(jobInfo);
    }

    @PostMapping(value = "/triggerVirtualTask")
    @ApiOperation("触发虚任务")
    public ReturnT<String> triggerVirtualTask(@RequestBody JobInfo jobInfo) {
        return jobService.triggerVirtualTask(jobInfo);
    }

    @GetMapping("/jobResult")
    @ApiOperation("查看任务结果")
    public ReturnT<Map<String,Object>> jobResult(@RequestParam("taskId") Integer taskId,
                                              @RequestParam(required = false, defaultValue = "1") int current,
                                              @RequestParam(required = false, defaultValue = "10") int size){
        Map<String,Object> map1 = new HashMap();
        Map<String,Object> map  = jobService.jobResult(taskId);
        int count = ((List)map.get("list")).size();

        List list1 = PageUtil.startPage((List)map.get("list"), current, size);
        map1.put("count",count);
        map1.put("data",list1);
        map1.put("colums",map.get("colums"));
        return new ReturnT<>(map1);
    }

    @GetMapping("/excel")
    @ApiOperation("导出为excel")
    public void excel(HttpServletResponse response,@RequestParam("taskId") Integer taskId) throws Exception {
        ExcelData data = new ExcelData();
        data.setName("Data");

        Map<String,Object> map  = jobService.jobResult(taskId);
        data.setTitles((List) map.get("colums"));

        List<List<Object>> list = (List<List<Object>>) map.get("list");
        data.setRows(list);

        ExportExcelUtils.exportExcel(response,"异常数据.xls",data);

    }
}
