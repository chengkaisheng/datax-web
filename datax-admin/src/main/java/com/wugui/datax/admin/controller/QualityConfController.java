package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.dto.QualityConfDto;
import com.wugui.datax.admin.service.JobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/api/qualityConf")
@Api(tags = "质量任务规则配置模块")
public class QualityConfController {

    @Resource
    private JobService jobService;

    @GetMapping("/pageConf")
    @ApiOperation("质量规则配置列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String name){

        Map<String,Object> map = jobService.getPageConf(current,size,name);

        return new ReturnT<>(map);
    }
}
