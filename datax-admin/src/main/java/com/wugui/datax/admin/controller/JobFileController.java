package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobFile;
import com.wugui.datax.admin.service.JobFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/1/11 14:28
 */
@Api(tags = "任务文件夹接口")
@RestController
@RequestMapping("/api/jobFile")
public class JobFileController extends BaseController{
    @Resource
    private JobFileService jobFileService;

    @PostMapping("/add")
    @ApiOperation("添加任务文件夹")
    public ReturnT<String> add(HttpServletRequest request, @RequestBody JobFile jobFile) {
        jobFile.setCreateId(getCurrentUserId(request));
        return jobFileService.add(jobFile);
    }
}
