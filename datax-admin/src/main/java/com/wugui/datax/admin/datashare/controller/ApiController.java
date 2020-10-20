package com.wugui.datax.admin.datashare.controller;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datashare.service.ApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(value = "实现接口服务", tags = {"实现接口服务"})
@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    private ApiService apiService;

    @ApiOperation(value = "实现接口服务")
    @RequestMapping(value = "/gateway/{interCode}", method = RequestMethod.POST)
    public ReturnT<Object> gateway(@PathVariable String interCode, HttpServletRequest request) {

        return new  ReturnT<>( apiService.getDatas(interCode,request));
    }


}
