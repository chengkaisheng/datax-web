package com.wugui.datax.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.service.MetadataImportService;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.HBaseQueryTool;

import com.wugui.datax.admin.tool.query.QueryToolFactory;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.metadata.query.BaseMetaDataQuery;
import com.wugui.datax.admin.util.metadata.query.MetadataQueryFactory;
import org.apache.atlas.AtlasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-05-16:52
 */
@Service
public class MetadataImportServiceImpl implements MetadataImportService {
    @Autowired
    JobDatasourceService jobDatasourceService;

    @Autowired
    DatasourceQueryService datasourceQueryService;

    @Override
    public void importMetadata(Long datasourceId) throws IOException, SQLException, AtlasException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return;
        }
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return;
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return;
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            String schema;
            if(JdbcConstants.ORACLE.equals(datasource.getDatasource())){
                schema = AESUtil.decrypt(datasource.getJdbcUsername());
            }else {
                schema = queryTool.getDBName();
            }
            datasource.setDatabaseName(schema);
            BaseMetaDataQuery metaDataQuery = MetadataQueryFactory.getMetadataQuery(datasource);
            //导入instance元数据,返回guid
            Map<String, String> instanceMetadata = metaDataQuery.setInstanceMetadata();
            //导入database元数据,返回guid
            Map<String, String> dbMetadata = metaDataQuery.setDbMetadata(instanceMetadata.get(datasource.getDatasource()));
            List<String> tables = queryTool.getTableNames(schema);
            //导入table元数据,返回guid
            Map<String, String> tableMetadata = metaDataQuery.setTableMetadata(schema, tables, dbMetadata.get(schema));
            for (String table : tables) {
                //导入列的元数据
                List<String> columns = datasourceQueryService.getColumns(datasourceId, table);
                metaDataQuery.setColumnMetadata(schema, table, columns, tableMetadata.get(table));
                //导入索引的元数据
                List<String> indexName = queryTool.getIndexName(schema, table);
                metaDataQuery.setIndexMetadata(schema, table, indexName, tableMetadata.get(table));
            }
        }
        return;
    }
}
