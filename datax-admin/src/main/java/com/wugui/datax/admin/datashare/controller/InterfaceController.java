package com.wugui.datax.admin.datashare.controller;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datashare.service.InterfaceExamSevice;
import com.wugui.datax.admin.datashare.service.InterfaceInfoSevice;
import com.wugui.datax.admin.datashare.vo.InterfaceExamParam;
import com.wugui.datax.admin.datashare.vo.InterfaceParam;
import com.wugui.datax.admin.datashare.vo.InterfaceQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(value = "接口处理器",tags={"接口信息操作接口"})
@RestController
@RequestMapping("api/interface")
public class InterfaceController {

    @Autowired
    private InterfaceInfoSevice interfaceInfoSevice;

    @Autowired
    private InterfaceExamSevice examSevice;

    @ApiOperation(value = "条件分页查询接口信息")
    @RequestMapping(value = "/getInterfaceInfoPages",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceInfoPages(@RequestBody InterfaceQuery query){
        return new ReturnT<>(interfaceInfoSevice.getInterfaceInfoPages(query));
    }

    @ApiOperation(value = "注册接口信息")
    @RequestMapping(value = "/insertInterface",method = RequestMethod.POST)
    public ReturnT<Object> insertInterface(@RequestBody InterfaceParam param, HttpServletRequest request){

        return new ReturnT<>(interfaceInfoSevice.insertInterface(param,request));
    }

    @ApiOperation(value = "查看接口详情")
    @RequestMapping(value = "/getInterfaceDetails",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceDetails(@RequestParam String id) {
        return new ReturnT<>(interfaceInfoSevice.getInterfaceDetails(id));
    }

    @ApiOperation(value = "查看接口审批详情")
    @RequestMapping(value = "/getInterfaceExamById",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceExamById(@RequestParam String id) {
        return new ReturnT<>(examSevice.getInterfaceExamById(id));
    }




    @ApiOperation(value = "条件查询接口审核信息")
    @RequestMapping(value = "/getInterfaceExamPage",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceExamPage(@RequestBody InterfaceQuery query) {
        return new ReturnT<>(examSevice.getInterfaceExamPage(query));
    }

    @ApiOperation(value = "注册接口审批")
    @RequestMapping(value = "/approvalInterface",method = RequestMethod.POST)
    ReturnT<Object> approvalInterface(@RequestBody InterfaceExamParam param){
        return new ReturnT<>(examSevice.approvalInterface(param));
    }





}
