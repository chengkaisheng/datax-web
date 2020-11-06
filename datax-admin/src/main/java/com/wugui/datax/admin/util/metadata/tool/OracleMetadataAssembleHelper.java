package com.wugui.datax.admin.util.metadata.tool;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.query.OracleQueryTool;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-04-16:37
 */
public class OracleMetadataAssembleHelper extends BaseMetadataAssembleHelper {
    public OracleMetadataAssembleHelper(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
        queryTool = new OracleQueryTool(jobDatasource);
    }


    @Override
    public Map<String, Map<String, Object>> getDatabaseInfo(String databaseName) throws IOException, SQLException {
        Map<String, Object> schemaMetadata = queryTool.getSchemaMetadata(databaseName);
        Map<String,Map<String,Object>> databaseInfoMap = new HashMap<>();
        databaseInfoMap.put(databaseName, schemaMetadata);
        return databaseInfoMap;
    }

    @Override
    public Map<String, Map<String, Object>> getTablesInfo(String databaseName, List<String> tableNames) throws IOException, SQLException {
        Map<String,Map<String,Object>> tableInfoMap = new HashMap<>();
        List<Map<String, Object>> tablesMetadata = queryTool.getTablesMetadata(databaseName);
        tableNames.forEach((table)->{
            for (Map<String, Object> tableMetadata : tablesMetadata) {
                if (StringUtils.equals(table, String.valueOf(tableMetadata.get("table_name")))){
                    tableInfoMap.put(table, tableMetadata);
                }
            }
        });
        return tableInfoMap;
    }

    @Override
    public Map<String, Map<String, Object>> getColumnsInfo(String database, String tablename, List<String> columns) throws IOException {
        Map<String,Map<String,Object>> columnInfoMap = new HashMap<>();
        List<Map<String, Object>> columnsMetadata = queryTool.getColumnsMetadata(database, tablename);
        columns.forEach((column)->{
            for (Map<String, Object> columnMetadata : columnsMetadata) {
                if (StringUtils.equals(column, String.valueOf(columnMetadata.get("column_name")))){
                    columnInfoMap.put(column, columnMetadata);
                }
            }
        });
        return columnInfoMap;
    }

    @Override
    public Map<String, Map<String, Object>> getIndexesInfo(String databaseName, String tableName, List<String> indexes) throws IOException {
        Map<String,Map<String,Object>> indexesInfoMap = new HashMap<>();
        List<Map<String, Object>> indexessMetadata = queryTool.getIndexesMetadata(databaseName, tableName);
        indexes.forEach((index)->{
            for (Map<String, Object> indexMetadata : indexessMetadata) {
                if (StringUtils.equals(index, String.valueOf(indexMetadata.get("index_name")))){
                    indexesInfoMap.put(index, indexMetadata);
                }
            }
        });
        return indexesInfoMap;
    }
}
