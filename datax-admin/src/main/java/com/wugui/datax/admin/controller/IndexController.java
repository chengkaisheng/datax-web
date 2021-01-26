package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.Dashboard;
import com.wugui.datax.admin.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * index controller
 *
 * @author jingwk 2019-12-22 16:13:16
 */
@RestController
@Api(tags = "首页接口")
@RequestMapping("/api")
public class IndexController {

    @Resource
    private JobService jobService;


    @GetMapping("/index")
    @ApiOperation("监控图")
    public ReturnT<Map<String, Object>> index() {
        return new ReturnT<>(jobService.dashboardInfo());
    }

    @PostMapping("/chartInfo")
    @ApiOperation("图表信息")
    public ReturnT<Map<String, Object>> chartInfo() {
        return jobService.chartInfo();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping("/getRunReport")
    public ReturnT<Dashboard> getRunReport() throws IOException {
        return jobService.getRunReport();
    }

    @RequestMapping("/getProjectCountReport")
    @ApiOperation("获取项目数量相关报表")
    public ReturnT<Dashboard> getProjectCountReport(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getProjectCountReport(userId);
    }

    @RequestMapping("/getItemTaskDistribution")
    @ApiOperation(" 项目任务数量分布")
    public ReturnT<Dashboard> getItemTaskDistribution(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getItemTaskDistribution(userId);
    }

    @RequestMapping("/getItemTaskTypeDistribution")
    @ApiOperation("项目任务类型分布")
    public ReturnT<Dashboard> getItemTaskTypeDistribution(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getItemTaskTypeDistribution(userId);
    }

    @RequestMapping("/getItemTaskRunStateDistribution")
    @ApiOperation("项目任务运行状态分布")
    public ReturnT<Dashboard> getItemTaskRunStateDistribution(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getItemTaskRunStateDistribution(userId);
    }

    @RequestMapping("/getDataSourceReport")
    @ApiOperation("获取数据源相关报表")
    public ReturnT<Dashboard> getDataSourceReport(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getDataSourceReport(userId);
    }

    @PostMapping("/getTriggerCountReport")
    @ApiOperation("获取任务成功，失败，正在执行的报表")
    public ReturnT<Map<String, Object>> getTriggerCountReport() {
        return jobService.getTriggerCountReport();
    }

    @PostMapping("/getTaskResultReport")
    @ApiOperation("获取任务结果报表")
    public ReturnT<Map<String, Object>> getTaskResultReport() {
        return jobService.getTaskResultReport();
    }
    @PostMapping("/getTaskTypeDistribution")
    @ApiOperation("任务类型分布统计")
    public ReturnT<Dashboard> getTaskTypeDistribution(@RequestParam("userId") Integer userId) {
        return jobService.getTaskTypeDistribution(userId);
    }

    @PostMapping("/getTaskExecutorDistribution")
    @ApiOperation(" 任务统计分布(按照执行器)")
    public ReturnT<Dashboard> getTaskExecutorDistribution(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getTaskExecutorDistribution(userId);
    }

    @RequestMapping("/getRuleReport")
    @ApiOperation("获取规则报表")
    public ReturnT<Dashboard> getRuleReport(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getRuleReport(userId);
    }

    @RequestMapping("/getUsedRule")
    @ApiOperation("规则统计")
    public ReturnT<Dashboard> getUsedRule(@RequestParam("userId") Integer userId) throws IOException {
        return jobService.getUsedRule(userId);
    }

    @RequestMapping("/getInterface")
    @ApiOperation("接口统计")
    public ReturnT<Dashboard> getInterface() throws IOException {
        return jobService.getInterface();
    }
}
