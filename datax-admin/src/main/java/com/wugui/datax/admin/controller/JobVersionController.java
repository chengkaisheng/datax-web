package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobVersion;
import com.wugui.datax.admin.service.JobVersionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @author hf
 * @creat 2021-02-02-15:05
 */
@RestController
@RequestMapping("/api/jobVersion")
public class JobVersionController extends BaseController{

    @Autowired
    private JobVersionService jobVersionService;

    @GetMapping("/pageVersionList")
    @ApiOperation("任务版本列表")
    public R<List<JobVersion>> pageList(@RequestParam(required = false, defaultValue = "0") int jobId) {
        return success(jobVersionService.pageList(jobId));
    }

    @PostMapping("/saveVersion")
    @ApiOperation("任务版本列表")
    public R saveVersion(@RequestBody JobInfo jobInfo) {
        return R.ok(jobVersionService.saveVersion(jobInfo));
    }

    @PostMapping("/rollBackVersion")
    @ApiOperation("回滚任务版本")
    public Object rollBackVersion(@RequestBody JobInfo jobInfo) {
        return jobVersionService.rollBackVersion(jobInfo);
    }
}
