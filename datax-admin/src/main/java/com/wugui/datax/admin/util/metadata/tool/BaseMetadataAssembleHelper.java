package com.wugui.datax.admin.util.metadata.tool;


import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-16:52
 */
public abstract class BaseMetadataAssembleHelper implements MetadataAssembleHelperInterface {

    protected JobDatasource jobDatasource;

    protected BaseQueryTool queryTool;

    public BaseMetadataAssembleHelper(JobDatasource jobDatasource){
        this.jobDatasource = jobDatasource;
    }

    public  String initConnection() throws HttpException, IOException {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getDatabaseInfo(String databaseName) throws IOException, SQLException {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getTablesInfo(String databaseName, List<String> tableNames) throws IOException, SQLException {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getColumnsInfo(String database, String tablename, List<String> columns) throws IOException {
        return null;
    }

    @Override
    public Map<String, Map<String, Object>> getIndexesInfo(String databaseName, String tableName, List<String> indexes) throws IOException {
        return null;
    }

    protected Map<String, String> parse(String response){
        return null;
    }
}
