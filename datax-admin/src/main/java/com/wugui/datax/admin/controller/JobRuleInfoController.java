package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.service.JobRuleInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-16 16:38:34
 */
@RestController
@RequestMapping("api/jobruleinfo")
public class JobRuleInfoController {

    @Resource
    private JobRuleInfoService jobRuleInfoService;

    @GetMapping("/info/{id}}")
    @ApiOperation("获取详情")
    public ReturnT<Map<String,Object>> info(@PathVariable(value = "id") Integer id){
        Map<String,Object> map = jobRuleInfoService.selectByJobRuleId(id);
        return new ReturnT<>(map);
    }

}
