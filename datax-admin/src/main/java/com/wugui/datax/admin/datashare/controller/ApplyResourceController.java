package com.wugui.datax.admin.datashare.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.controller.BaseController;
import com.wugui.datax.admin.datashare.service.ApplyResourceServie;
import com.wugui.datax.admin.datashare.service.InterfaceInfoSevice;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import com.wugui.datax.admin.datashare.vo.ResourceParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Api(value = "共享模块处理器",tags={"共享模块操作接口"})
@RestController
@RequestMapping("api/applyResource")
public class ApplyResourceController extends BaseController {
    private static final Logger log= LoggerFactory.getLogger(ApplyResourceController.class);

    @Autowired
    private ApplyResourceServie applyResourceServie;

    @Autowired
    private InterfaceInfoSevice interfaceInfoSevice;

    @ApiOperation(value = "条件分页查询资源列表")
    @RequestMapping(value = "/getResourceList",method = RequestMethod.POST)
    public ReturnT<Object> getResourceList( ApplyResourceQuery applyResourceQuery){
        if(applyResourceQuery.getPageNum()==null|| applyResourceQuery.getPageSize()==null){
            log.error("分页条件不能为空");
            return new  ReturnT<>(ReturnT.FAIL_CODE, "分页条件不能为空") ;
        }
        return new  ReturnT<>( applyResourceServie.getResourceList(applyResourceQuery));
    }

    @ApiOperation(value = "条件分页查询接口列表")
    @RequestMapping(value = "/getInterfaceResult",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceResult(@RequestBody ApplyResourceQuery applyResourceQuery) {
        return new  ReturnT<>(interfaceInfoSevice.getInterfaceResult(applyResourceQuery));
    }

    @ApiOperation(value = "提交申请信息")
    @RequestMapping(value = "/applyAnduploadFile",method = RequestMethod.POST)
    public ReturnT<Object> applyAnduploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, ResourceParam resourceParam) throws IOException {

        if (StringUtils.isBlank(file.getOriginalFilename())) {
            log.info("上传文件不能为空");
            return new  ReturnT<>(ReturnT.FAIL_CODE, "上传图片不能为空，请选择图片");
        }

        return new  ReturnT<>(applyResourceServie.applyAnduploadFile(file,request,resourceParam));
    }


    @ApiOperation(value = "查询申请的我的资源")
    @RequestMapping(value = "/getMyApplyResourcePages",method = RequestMethod.POST)
    public ReturnT<Object> getMyApplyResourcePages(@RequestBody ApplyResourceQuery applyResourceQuery){
        if(applyResourceQuery.getPageNum()==null|| applyResourceQuery.getPageSize()==null){
            log.error("分页条件不能为空");
            return  new  ReturnT<>(ReturnT.FAIL_CODE,"分页条件不能为空") ;
        }
        return new  ReturnT<>(applyResourceServie.getMyApplyResource(applyResourceQuery));
    }


    @ApiOperation(value = "查看已申请的资源接口信息")
    @RequestMapping(value = "/getInterfaceByinfoId",method = RequestMethod.POST)
    public ReturnT<Object> getInterfaceByinfoId(@RequestParam("id") String id){
        if(StringUtils.isBlank(id)){
            log.error("查询Id不能为空");
            return  new  ReturnT<>(ReturnT.FAIL_CODE,"查询Id不能为空") ;
        }
        return new  ReturnT<>(interfaceInfoSevice.getInterfaceByinfoId(id));
    }


}
