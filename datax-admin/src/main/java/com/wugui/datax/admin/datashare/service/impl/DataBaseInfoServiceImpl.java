package com.wugui.datax.admin.datashare.service.impl;

import com.github.pagehelper.PageHelper;

import com.wugui.datax.admin.datashare.entity.TDataCatalog;
import com.wugui.datax.admin.datashare.entity.TDataCatalogExample;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfoExample;
import com.wugui.datax.admin.datashare.enums.ShareEnum;
import com.wugui.datax.admin.datashare.mapper.TDataCatalogMapper;
import com.wugui.datax.admin.datashare.mapper.TDatabaseInfoMapper;
import com.wugui.datax.admin.datashare.service.DataBaseInfoServise;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.tools.UUIDUtils;
import com.wugui.datax.admin.datashare.vo.DataBaseInfoQuery;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DataBaseInfoServiceImpl implements DataBaseInfoServise {
    @Autowired
    TDatabaseInfoMapper databaseInfoMapper;
    @Autowired
    TDataCatalogMapper dataCatalogMapper;
    private static final Logger logger= LoggerFactory.getLogger(DataBaseInfoServiceImpl.class);

    /**
     * 新增服务器
     * @param databaseInfo
     * @return
     */
    @Override
    public Object insertDataBaseInfo(TDatabaseInfo databaseInfo) {
        Result result= (Result) checkNonempty(databaseInfo);
        if(!result.getSuccess()){
            return new Result(result.getSuccess(),result.getMessage());
        }
        TDatabaseInfoExample example=new TDatabaseInfoExample(TDatabaseInfo.class);
        TDatabaseInfoExample.Criteria criteria=example.createCriteria();
        criteria.andDataServerNameEqualTo(databaseInfo.getDataServerName());
        List<TDatabaseInfo> databaseInfos=databaseInfoMapper.selectByExample(example);
        if (databaseInfos.size()>0){
            return new Result<>(false, "该服务器名称已存在，请勿重复添加!");
        }
        String infoId= UUIDUtils.getUUID();
        databaseInfo.setId(infoId);
        databaseInfo.setCreateTime(new Date());
        try {
            databaseInfoMapper.insertSelective(databaseInfo);
            return new Result(true, ShareEnum.Add_SUCCESS.getDescribe());
        }catch (Exception e){
            return new Result(false,ShareEnum.Add_Fail.getDescribe());
        }
    }

    /**
     * 修改服务器
     * @param databaseInfo
     * @return
     */
    @Override
    public Object updateDataBaseInfo(TDatabaseInfo databaseInfo) {
        if("".equals(databaseInfo.getId()) ){
            return new Result(false,"缺少主键");//返回空字段描述
        }
        TDatabaseInfoExample example=new TDatabaseInfoExample(TDatabaseInfo.class);
        TDatabaseInfoExample.Criteria criteria=example.createCriteria();
        criteria.andIdEqualTo(databaseInfo.getId());
        try {
            //修改信息资源
            databaseInfoMapper.updateByExampleSelective(databaseInfo,example);
            return new Result(true,ShareEnum.UPDATE_SUCCESS.getDescribe());

        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,ShareEnum.UPDATE_Fail.getDescribe());
        }
    }

    /**
     * 分页查询服务器信息
     * @param dataBaseInfoQuery
     * @return
     */
    @Override
    public Object getDataBaseInfoPages(DataBaseInfoQuery dataBaseInfoQuery) {
        TDatabaseInfoExample example=new TDatabaseInfoExample(TDatabaseInfo.class);
        TDatabaseInfoExample.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotBlank(dataBaseInfoQuery.getDataServerName())){//数据源名称
            criteria.andDataServerNameLike("%"+dataBaseInfoQuery.getDataServerName()+"%");
        }
        PageHelper.startPage(dataBaseInfoQuery.getPageNum(),dataBaseInfoQuery.getPageSize());
        List<TDatabaseInfo> databaseInfos=databaseInfoMapper.selectByExample(example);
        int total=databaseInfoMapper.countByExample(example);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("databaseInfoList",databaseInfos);
        return map;
    }

    @Override
    public List<TDatabaseInfo> getDataBaseInfoServerName() {
        //查询所有不重复的服务器名称
        return databaseInfoMapper.getDataBaseInfoServerName();
    }

    /**
     * 删除服务器，已使用的无法删除
     * @param databaseInfo
     * @return
     */
    @Override
    public Object deleteDataBaseInfoServer(TDatabaseInfo databaseInfo) {
        int n=0;
        TDatabaseInfoExample example=new TDatabaseInfoExample(TDatabaseInfo.class);
        TDatabaseInfoExample.Criteria criteria=example.createCriteria();
        if(StringUtils.isNotBlank(databaseInfo.getId())){
            criteria.andIdEqualTo(databaseInfo.getId());
        }
        List<TDatabaseInfo> databaseInfos=databaseInfoMapper.selectByExample(example);
        if(databaseInfos.size()>0){
            TDataCatalogExample dataCatalogExample=new TDataCatalogExample(TDataCatalog.class);
            TDataCatalogExample.Criteria dataCatalogCriteria=dataCatalogExample.createCriteria();
            dataCatalogCriteria.andDataServerNameEqualTo(databaseInfos.get(0).getDataServerName());
            List<TDataCatalog> dataCatalogs=dataCatalogMapper.selectByExample(dataCatalogExample);
            if(dataCatalogs.size()>0){
                return new Result(false,"此服务器已使用，无法删除");
            }else {
                n=databaseInfoMapper.deleteByPrimaryKey(databaseInfo.getId());
            }
        }else {
            return new Result(false,"没有查询到相关的服务器");
        }
        if(n>0){
            return new Result<>(true, ShareEnum.DETELE_SUCCESS.getDescribe());
        }else {
            return new Result<>(false, ShareEnum.DETELE_FAIL.getDescribe());
        }
    }

    /**
     * 新增，修改条件非空检查
     * @param databaseInfo
     * @return
     */
    public Object checkNonempty(TDatabaseInfo databaseInfo){
        if(StringUtils.isBlank(databaseInfo.getDatabaseUrl())){
            logger.error("服务器url不能为空");
            return new Result(false,"服务器url不能为空");
        }
        if(StringUtils.isBlank(databaseInfo.getDatabaseDriver())){
            logger.error("服务器驱动不能为空");
            return new Result(false,"服务器驱动不能为空");
        }
        if(StringUtils.isBlank(databaseInfo.getUserName())){
            logger.error("服务器用户名不能为空");
            return new Result(false,"服务器用户名不能为空");
        }
        if(StringUtils.isBlank(databaseInfo.getPassword())){
            logger.error("服务器密码不能为空");
            return new Result(false,"服务器密码不能为空");
        }
        if(StringUtils.isBlank(databaseInfo.getDataServerName())){
            logger.error("服务器名称不能为空");
            return new Result(false,"服务器名称不能为空");
        }
        if(StringUtils.isBlank(databaseInfo.getDataSource())){
            logger.error("数据源不能为空");
            return new Result(false,"数据源不能为空");
        }
        return new Result(true,"条件正常");
    }
}
