package com.wugui.datax.admin.controller;

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
public class UniversalRuleController {

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

        universalRuleService.add(universalRule,request);
        return ReturnT.SUCCESS;
    }

}
