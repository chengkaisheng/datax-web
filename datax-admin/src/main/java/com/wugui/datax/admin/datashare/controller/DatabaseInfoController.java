package com.wugui.datax.admin.datashare.controller;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.service.ApiService;
import com.wugui.datax.admin.datashare.service.DataBaseInfoServise;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.DataBaseInfoQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "所在服务器处理器",tags={"服务器操作接口"})
@RestController
@RequestMapping("api/databaseInfo")
public class DatabaseInfoController {
    private static final Logger logger= LoggerFactory.getLogger(DatabaseInfoController.class);
    @Autowired
    DataBaseInfoServise dataBaseInfoServise;

    @Autowired
    ApiService apiService;

    @ApiOperation(value = "新增服务器")
    @RequestMapping(value = "/insertDatebaseInfo",method= RequestMethod.POST)
    public ReturnT<Object> insertDatabaseInfo(@RequestBody TDatabaseInfo databaseInfo){
        return new  ReturnT<>(dataBaseInfoServise.insertDataBaseInfo(databaseInfo));
    }

    @ApiOperation(value = "修改服务器")
    @RequestMapping(value = "/updateDataBaseInfo",method= RequestMethod.POST)
    public ReturnT<Object> updateDataBaseInfo(@RequestBody TDatabaseInfo databaseInfo){
        return new  ReturnT<>(dataBaseInfoServise.updateDataBaseInfo(databaseInfo));
    }

    @ApiOperation(value = "多条件查询服务器信息")
    @RequestMapping(value = "/getDataBaseInfoPages",method= RequestMethod.POST)
    public ReturnT<Object> getDataBaseInfoPages(@RequestBody DataBaseInfoQuery dataBaseInfoQuery){
        if(dataBaseInfoQuery.getPageNum()==null||dataBaseInfoQuery.getPageSize()==null){
            logger.error("分页条件不能为空");
            return new  ReturnT<>(ReturnT.FAIL_CODE,"分页条件不能为空") ;
        }
        return new  ReturnT<>(dataBaseInfoServise.getDataBaseInfoPages(dataBaseInfoQuery));
    }
    @ApiOperation(value = "查询所在服务器名称")
    @RequestMapping(value = "/getDataBaseInfoServerName",method = RequestMethod.POST)
    public ReturnT<Object> getDataBaseInfoServerName() {
        return new  ReturnT<>(dataBaseInfoServise.getDataBaseInfoServerName());
    }

    @ApiOperation(value = "删除服务器")
    @RequestMapping(value = "/deleteDataBaseInfoServer",method = RequestMethod.POST)
    public ReturnT<Object> deleteDataBaseInfoServer(@RequestBody TDatabaseInfo databaseInfo) {
        return new  ReturnT<>(dataBaseInfoServise.deleteDataBaseInfoServer(databaseInfo));
    }

    @ApiOperation(value = "测试连接服务器")
    @RequestMapping(value = "/testConnectServer",method = RequestMethod.POST)
    public ReturnT<Object> testConnectServer(@RequestBody DataBaseInfoQuery dataBaseInfoQuery) {
        return new  ReturnT<>(apiService.deleteDataBaseInfoServer(dataBaseInfoQuery));
    }
}
