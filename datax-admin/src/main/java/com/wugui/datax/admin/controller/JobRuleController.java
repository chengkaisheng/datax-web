package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobRule;
import com.wugui.datax.admin.service.JobRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.StringReader;
import java.util.Map;


/**
 * 任务规则关系表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-13 18:14:52
 */
@RestController
@RequestMapping("api/jobrule")
@Api(tags = "规则配置模块")
public class JobRuleController extends BaseController{

    @Resource
    private JobRuleService jobRuleService;

    @GetMapping("/pageList")
    @ApiOperation("规则配置列表")
    public ReturnT<Map<String,Object>> pageList(@RequestParam(required = false,defaultValue = "1") int current,
                                                @RequestParam(required = false,defaultValue = "10") int size){
        Map<String,Object> map = jobRuleService.pageList(current,size);
        return new ReturnT<>(map);
    }

    @GetMapping("/info/{id}")
    @ApiOperation("规则配置详情")
    public ReturnT<Map<String,Object>> info(@PathVariable(value = "id") Integer id){
        Map<String,Object> map = jobRuleService.getJobRuleInfo(id);
        return new ReturnT<>(map);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除")
    public ReturnT<String> delete(@PathVariable(value = "id") Integer id){
        jobRuleService.removeById(id);
        return ReturnT.SUCCESS;
    }

    @PostMapping("/update")
    @ApiOperation("修改")
    public ReturnT<String> update(@RequestBody JobRule jobRule){
        jobRuleService.updateById(jobRule);
        return ReturnT.SUCCESS;
    }

}
