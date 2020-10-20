package com.wugui.datax.admin.datashare.service.impl;


import com.wugui.datax.admin.datashare.entity.TDataCatalog;
import com.wugui.datax.admin.datashare.entity.TDataCatalogExample;
import com.wugui.datax.admin.datashare.entity.TDataItem;
import com.wugui.datax.admin.datashare.entity.TDataItemExample;
import com.wugui.datax.admin.datashare.mapper.TDataCatalogMapper;
import com.wugui.datax.admin.datashare.mapper.TDataItemMapper;
import com.wugui.datax.admin.datashare.service.DataCatalogService;
import com.wugui.datax.admin.datashare.tools.JwtToken;
import com.wugui.datax.admin.datashare.tools.Result;
import com.wugui.datax.admin.datashare.vo.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataCatalogServiceImpl implements DataCatalogService {
    @Autowired
    private TDataCatalogMapper dataCatalogMapper;
    @Autowired
    private TDataItemMapper dataItemMapper;

    private static final Logger logger= LoggerFactory.getLogger(DataCatalogServiceImpl.class);

    @Override
    public Object getCatalogByServerName(String serverName) {
        TDataCatalogExample example=new TDataCatalogExample(TDataCatalog.class);
        TDataCatalogExample.Criteria criteria=example.createCriteria();
        criteria.andDataServerNameEqualTo(serverName);
        try {
//            Token t= JwtToken.unsign(token,Token.class);//解析token
//            criteria.andDataCompanyLike("%"+t.getOrganize().getName()+"%");
            return new Result<>(true,dataCatalogMapper.selectByExample(example));
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(false,"参数异常");
        }

    }

    @Override
    public Object getItemByInfoName(String infoName) {
        TDataItemExample example=new TDataItemExample(TDataItem.class);
        TDataItemExample.Criteria criteria=example.createCriteria();
        criteria.andInfoNameEqualTo(infoName);
        return dataItemMapper.selectByExample(example);
    }

}
