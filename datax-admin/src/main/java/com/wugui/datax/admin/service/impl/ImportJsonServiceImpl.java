package com.wugui.datax.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.wugui.datax.admin.dto.ImportJsonDto;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.ImportJsonService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.datax.DataxJsonHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/1/29 11:29
 */
@Service
public class ImportJsonServiceImpl implements ImportJsonService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JobDatasourceService jobJdbcDatasourceService;
    @Override
    public String buildJobJson(ImportJsonDto dto) {
        DataxJsonHelper dataxJsonHelper = new DataxJsonHelper();
        // reader
        JobDatasource readerDatasource = jobJdbcDatasourceService.getById(dto.getReaderDatasourceId());
        // reader plugin init
        dataxJsonHelper.initImportReader(dto, readerDatasource);
        //writer
        JobDatasource writerDatasource = jobJdbcDatasourceService.getById(dto.getWriterDatasourceId());
        //writer plugin init
        dataxJsonHelper.initImportWriter(dto, writerDatasource);

        return JSON.toJSONString(dataxJsonHelper.buildJob());
    }
}
