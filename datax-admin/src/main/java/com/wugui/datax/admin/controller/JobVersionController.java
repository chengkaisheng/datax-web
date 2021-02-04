package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.JobVersion;
import com.wugui.datax.admin.service.JobVersionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
