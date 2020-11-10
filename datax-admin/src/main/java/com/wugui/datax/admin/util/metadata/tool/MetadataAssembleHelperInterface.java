package com.wugui.datax.admin.util.metadata.tool;

import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-18:11
 */
public interface MetadataAssembleHelperInterface {

    String initConnection() throws HttpException, IOException;

    Map<String, Map<String,Object>> getDatabaseInfo(String databaseName) throws IOException, SQLException;

    Map<String, Map<String,Object>> getTablesInfo(String databaseName, List<String> tableNames) throws IOException, SQLException;

    Map<String,Map<String,Object>> getColumnsInfo(String database, String tablename, List<String> columns) throws IOException;

    Map<String, Map<String, Object>> getIndexesInfo(String databaseName, String tableName, List<String> indexes) throws IOException;

}
