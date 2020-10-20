package com.wugui.datax.admin.datashare.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datashare.service.DataCatalogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "信息资源目录处理器",tags={"信息资源操作接口"})
@RestController
@RequestMapping("api/dataCatalog")
public class DataCatalogControlller {
    private static final Logger logger= LoggerFactory.getLogger(DataCatalogControlller.class);

    @Autowired
    private DataCatalogService dataCatalogService;

    @ApiOperation(value = "根据所在服务器名称查询表")
    @RequestMapping(value = "/getCatalogByServerName",method = RequestMethod.POST)
    public ReturnT<Object> getCatalogByServerName(@RequestParam("serverName") String serverName) {
        return new ReturnT<>(dataCatalogService.getCatalogByServerName(serverName));
    }

    @ApiOperation(value = "根据信息资源名称查询数据项")
    @RequestMapping(value = "/getItemByInfoName",method =RequestMethod.POST)
    public ReturnT<Object> getItemByInfoName(@RequestParam String infoName) {
        return new  ReturnT<>(dataCatalogService.getItemByInfoName(infoName));
    }
}
