package com.wugui.datax.admin.util.metadata.tool;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-18:11
 */
public interface CloudBeaverHelperInterface {

    String initConnection(JobDatasource jobDatasource) throws HttpException, IOException;

    Map<String, Map<String,String>> getDatabaseInfo(String coonId, String databaseName) throws IOException;

    Map<String,Map<String,String>> getColumnsInfo(String coonId, String database, String tablename, List<String> columns) throws IOException;

    Map<String, Map<String, String>> getIndexesInfo(String connId, String databaseName, String tableName, List<String> indexes) throws IOException;

}
