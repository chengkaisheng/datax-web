package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * mysql数据库使用的查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:31
 */
public class MySQLQueryTool extends BaseQueryTool implements QueryToolInterface {

    public MySQLQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    @Override
    public Map<String, Object> getSchemaMetadata(String schema) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String,Object> map= new HashMap<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getSchemaMetadata(schema);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                map.put("catalog_name", rs.getString(1));
                map.put("schema_name", rs.getString(2));
                map.put("default_character_set_name", rs.getString(3));
                map.put("default_collation_name", rs.getString(4));
                map.put("sql_path", rs.getString(5));
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return map;
    }

    @Override
    public Map<String, Object> getTableMetadata(String schema, String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String,Object> map= new HashMap<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getTableMetadata(schema, tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                map.put("table_name", rs.getString(3));
                map.put("table_type", rs.getString(4));
                map.put("engine", rs.getString(5));
                map.put("row_format", rs.getString(7));
                map.put("table_rows", rs.getLong(8));
                map.put("avg_row_length", rs.getLong(9));
                map.put("data_length", rs.getLong(10));
                map.put("max_data_length", rs.getLong(11));
                map.put("index_length", rs.getLong(12));
                map.put("data_free", rs.getLong(13));
                map.put("auto_increment", rs.getLong(14));
                map.put("create_time", rs.getDate(15));
                map.put("table_collation", rs.getString(18));
                map.put("table_comment", rs.getString(21));
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return map;
    }

    @Override
    public List<Map<String,Object>> getTablesMetadata(String schema) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> tablesMetadata = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getTablesMetadata(schema);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("table_name", rs.getString(3));
                map.put("table_type", rs.getString(4));
                map.put("engine", rs.getString(5));
                map.put("row_format", rs.getString(7));
                map.put("table_rows", rs.getLong(8));
                map.put("avg_row_length", rs.getLong(9));
                map.put("data_length", rs.getLong(10));
                map.put("max_data_length", rs.getLong(11));
                map.put("index_length", rs.getLong(12));
                map.put("data_free", rs.getLong(13));
                map.put("auto_increment", rs.getLong(14));
                map.put("create_time", rs.getDate(15));
                map.put("table_collation", rs.getString(18));
                map.put("table_comment", rs.getString(21));
                tablesMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tablesMetadata;
    }

    @Override
    public Map<String, Object> getColumnMetadata(String schema, String tableName, String columnName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String,Object> map= new HashMap<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getColumnMetadata(schema, tableName, columnName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                map.put("column_name", rs.getString(4));
                map.put("ordinal_position", rs.getLong(5));
                map.put("column_default", rs.getString(6));
                map.put("is_nullable", rs.getString(7));
                map.put("data_type", rs.getString(8));
                map.put("character_maximum_length", rs.getLong(9));
                map.put("character_octet_length", rs.getLong(10));
                map.put("numeric_precision", rs.getLong(11));
                map.put("numeric_scale", rs.getLong(12));
                map.put("datetime_precision", rs.getLong(13));
                map.put("character_set_name", rs.getString(14));
                map.put("collation_name", rs.getString(15));
                map.put("column_type", rs.getString(16));
                map.put("privileges", rs.getString(19));
                map.put("column_comment", rs.getString(20));
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return map;
    }

    @Override
    public List<Map<String,Object>> getColumnsMetadata(String schema, String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> columnsMetadata = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getColumnsMetadata(schema, tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("column_name", rs.getString(4));
                map.put("ordinal_position", rs.getLong(5));
                map.put("column_default", rs.getString(6));
                map.put("is_nullable", rs.getString(7));
                map.put("data_type", rs.getString(8));
                map.put("character_maximum_length", rs.getLong(9));
                map.put("character_octet_length", rs.getLong(10));
                map.put("numeric_precision", rs.getLong(11));
                map.put("numeric_scale", rs.getLong(12));
                map.put("datetime_precision", rs.getLong(13));
                map.put("character_set_name", rs.getString(14));
                map.put("collation_name", rs.getString(15));
                map.put("column_type", rs.getString(16));
                map.put("privileges", rs.getString(19));
                map.put("column_comment", rs.getString(20));
                columnsMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return columnsMetadata;
    }

    @Override
    public Map<String, Object> getIndexMetadata(String schema, String tableName, String indexName) {
        return super.getIndexMetadata(schema, tableName, indexName);
    }

    @Override
    public List<Map<String, Object>> getIndexesMetadata(String schema, String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        List<Map<String,Object>> indexesMetadata = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getIndexesMetadata(schema, tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("index_name", rs.getString(1));
                map.put("stat_name", rs.getString(2));
                map.put("stat_value", rs.getString(3));
                map.put("sample_size", rs.getString(4));
                map.put("stat_description", rs.getString(5));
                indexesMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return indexesMetadata;
    }
}
