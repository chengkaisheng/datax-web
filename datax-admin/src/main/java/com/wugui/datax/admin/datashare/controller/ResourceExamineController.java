package com.wugui.datax.admin.datashare.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datashare.service.ResourceExamineService;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.ResourceExamParam;
import com.wugui.datax.admin.datashare.vo.ResourceExamQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(value = "共享申请审核处理器",tags={"共享申请审核操作接口"})
@RestController
@RequestMapping("api/resExamine")
public class ResourceExamineController {
    private static final Logger log= LoggerFactory.getLogger(ResourceExamineController.class);

    @Autowired
    private ResourceExamineService resourceExamineService;

    @ApiOperation(value = "条件分页查询资源申请")
    @RequestMapping(value = "/getResourceExamPages",method = RequestMethod.POST)
    public ReturnT<Object> getResourceExamPages(@RequestBody ResourceExamQuery query){
        if(query.getPageNum()==null|| query.getPageSize()==null){
            log.error("分页条件不能为空");
            return new  ReturnT<>(ReturnT.FAIL_CODE,"分页条件不能为空") ;
        }
        return new ReturnT<>(resourceExamineService.getTResourceExaminePages(query));
    }

    @ApiOperation(value = "审批资源申请")
    @RequestMapping(value = "/approveResource",method = RequestMethod.POST)
    public ReturnT<Object> approveResource(@RequestBody ResourceExamParam param) {
        return new ReturnT<>(resourceExamineService.approveResource(param));
    }


    @ApiOperation(value = "我的资源申请里查看审批详情")
    @RequestMapping(value = "/getResourceExamineByResId",method = RequestMethod.POST)
    ReturnT<Object> getResourceExamineByResId(@RequestParam String resId){
        return new ReturnT<>(resourceExamineService.getResourceExamineByResId(resId));
    }

}
