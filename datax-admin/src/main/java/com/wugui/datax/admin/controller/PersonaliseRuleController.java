package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.PersonaliseRule;
import com.wugui.datax.admin.entity.UniversalRule;
import com.wugui.datax.admin.service.PersonaliseRuleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;


/**
 * 个性化规则表
 *
 */
@RestController
@RequestMapping("/api/personalise")
public class PersonaliseRuleController extends BaseController{

    @Resource
    private PersonaliseRuleService personaliseRuleService;

    @GetMapping("/pagePersonalise")
    @ApiOperation("个性化规则列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String name,Integer type,Integer joinType){
        Map<String,Object> map = personaliseRuleService.getPersonaliseList(current,size,name,type,joinType);
        return new ReturnT<>(map);
    }

    @PostMapping("/add")
    @ApiOperation("添加")
    public ReturnT<String> add(@RequestBody PersonaliseRule personaliseRule, HttpServletRequest request){
        personaliseRule.setCreateUserId(getCurrentUserId(request));
        personaliseRule.setCreateTime(new Date());
        personaliseRuleService.add(personaliseRule);
        return ReturnT.SUCCESS;
    }

    @GetMapping("/info")
    @ApiOperation("个性化规则详情")
    public ReturnT<Map<String,Object>> info(@PathVariable(value = "id")Integer id){
        Map<String,Object> map = personaliseRuleService.info(id);
        return new ReturnT<>(map);
    }

    @PostMapping("/update")
    @ApiOperation("修改个性化规则")
    public ReturnT<String> update(@RequestBody PersonaliseRule personaliseRule){
        personaliseRuleService.update(personaliseRule);
        return ReturnT.SUCCESS;
    }

    @PostMapping("/delete")
    @ApiOperation("删除个性化规则")
    public ReturnT<String> delete(@PathVariable(value = "id")Integer id){
        personaliseRuleService.delete(id);
        return ReturnT.SUCCESS;
    }

}
