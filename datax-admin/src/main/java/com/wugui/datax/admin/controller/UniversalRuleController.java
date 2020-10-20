package com.wugui.datax.admin.controller;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.UniversalRule;
import com.wugui.datax.admin.service.UniversalRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;


/**
 * 通用规则表
 *
 */
@RestController
@RequestMapping("/api/universal")
@Api(tags = "通用规则接口")
public class UniversalRuleController extends BaseController {

    @Resource
    private UniversalRuleService universalRuleService;

    @GetMapping("/pageUniversal")
    @ApiOperation("通用规则列表")
    public ReturnT<Map<String, Object>> pageList(@RequestParam(required = false, defaultValue = "1") int current,
                                                 @RequestParam(required = false, defaultValue = "10") int size,
                                                 String name){
        Map<String,Object> map = universalRuleService.getUniversalList(current,size,name);
        return new ReturnT<>(map);
    }

    @PostMapping("/add")
    @ApiOperation("添加")
    public ReturnT<String> add(@RequestBody UniversalRule universalRule, HttpServletRequest request){
        universalRule.setCreateUserId(getCurrentUserId(request));
        universalRuleService.add(universalRule);
        return ReturnT.SUCCESS;
    }

    @PostMapping("/delete/{id}")
    @ApiOperation("删除通用规则")
    public ReturnT<String> delete(@PathVariable(value = "id")Integer id){
        universalRuleService.delete(id);
        return ReturnT.SUCCESS;
    }

    @PostMapping("/update")
    @ApiOperation("修改通用规则")
    public ReturnT<String> update(@RequestBody UniversalRule universalRule){
        universalRuleService.update(universalRule);
        return ReturnT.SUCCESS;
    }

    @GetMapping("/info/{id}")
    @ApiOperation("通用规则详情")
    public ReturnT<Map<String,Object>> info(@PathVariable(value = "id")Integer id){
        Map<String,Object> map = universalRuleService.info(id);
        return new ReturnT<>(map);
    }

    @GetMapping("/universalName/{type}")
    @ApiOperation("根据规则大类查询规则小类")
    public ReturnT<Map<String,Object>> universalName(@PathVariable(value = "type")Integer type){
        Map<String,Object> map = universalRuleService.getUniversalNameByType(type);
        return new ReturnT<>(map);
    }

    @GetMapping("/univerToPerson")
    @ApiOperation("查询关联的通用规则和个性化规则")
    public ReturnT<Map<String,Object>> universalToPersonalise(){
        Map<String,Object> map = universalRuleService.getUniverToPerson();
        return new ReturnT<>(map);
    }

    @GetMapping("/check")
    @ApiOperation("检查code是否重复")
    public Map<String,Object> check(String code){
        Map<String,Object> map = universalRuleService.check(code);
        return map;
    }

}
