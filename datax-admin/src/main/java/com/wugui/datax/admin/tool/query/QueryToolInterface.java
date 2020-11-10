package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.ColumnMsg;
import com.wugui.datax.admin.entity.Search;
import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.TableInfo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 基础查询接口
 *
 * @author zhouhongfa@gz-yibo.com
 * @version 1.0
 * @since 2019/7/18
 */
public interface QueryToolInterface {
    /**
     * 构建 tableInfo对象
     *
     * @param tableName 表名
     * @return
     */
    TableInfo buildTableInfo(String tableName);

    /**
     * 获取指定表信息
     *
     * @return
     */
    List<Map<String, Object>> getTableInfo(String tableName);

    /**
     * 获取指定schema下的所有表信息
     *
     * @return
     */
    List<Map<String, Object>> getTablesInfo(String schema);


    List<Map<String, Object>> getTablesInfo();

    /**
     * 获取当前schema下的所有表
     *
     * @return
     */
    List<Map<String, Object>> getTables();

    /**
     * 根据表名获取所有字段
     *
     * @param tableName
     * @return2
     */
    List<ColumnInfo> getColumns(String tableName);



    /**
     * 根据表名和获取所有字段名称（不包括表名）
     *
     * @param tableName
     * @return2
     */
    List<String> getColumnNames(String tableName,String datasource);

    /**
     * 根据表名和获取所有字段的名称，类型，comment（不包括表名）
     *
     * @param tableName
     * @return2
     */
    Object getTableColumns(String tableName,String datasource,String databaseName);


    /**
     * 获取所有可用表名
     *
     * @return2
     */
    List<String> getTableNames(String schema);

    /**
     * 获取所有可用表名
     *
     * @return2
     */
    List<String> getTableNames();

    /**
     * 通过查询sql获取columns
     * @param querySql
     * @return
     */
    List<String> getColumnsByQuerySql(String querySql) throws SQLException;

    /**
     * 获取当前表maxId
     * @param tableName
     * @param primaryKey
     * @return
     */
    long getMaxIdVal(String tableName,String primaryKey);

    List<ColumnMsg> getColumnSchema(String tableName, String tableSchema);

    String getDBName();

    String getTableSize(String tableName, String tableSchema);

    Map<String,Object> getSchemaMetadata(String schema);

    Map<String,Object> getTableMetadata(String schema, String tableName);

    List<Map<String,Object>> getTablesMetadata(String schema);

    Map<String,Object> getColumnMetadata(String schema,String tableName, String columnName);

    List<Map<String,Object>> getColumnsMetadata(String schema,String tableName);

    Map<String,Object> getIndexMetadata(String schema, String tableName, String indexName);

    List<Map<String,Object>> getIndexesMetadata(String schema, String tableName);

    List<String> getIndexName(String schema, String tableName);

    Boolean dataSourceTest(String databaseName);

    Map<String, String> getHivePathDefault(String schema, String tableName);
}
