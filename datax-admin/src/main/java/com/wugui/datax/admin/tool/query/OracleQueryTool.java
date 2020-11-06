package com.wugui.datax.admin.tool.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wugui.datax.admin.datashare.tools.ResultToJsonUtil.resultSetToJSON;

/**
 * Oracle数据库使用的查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:31
 */
public class OracleQueryTool extends BaseQueryTool implements QueryToolInterface {

    public OracleQueryTool(JobDatasource jobDatasource) throws SQLException {
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
                map.put("username", rs.getString(1));
                map.put("createTime", rs.getString(2));
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
        return super.getTableMetadata(schema, tableName);
    }

    @Override
    public List<Map<String, Object>> getTablesMetadata(String schema) {
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
                map.put("table_name", rs.getString(1));
                map.put("row_count", rs.getString(2));
                map.put("table_comment", rs.getString(3));
                map.put("data_length", rs.getString(4));
                map.put("avg_row_len", rs.getString(5));
                map.put("avg_space", rs.getString(6));
                tablesMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTablesMetadata Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tablesMetadata;
    }

    @Override
    public Map<String, Object> getColumnMetadata(String schema, String tableName, String columnName) {
        return super.getColumnMetadata(schema, tableName, columnName);
    }

    @Override
    public List<Map<String, Object>> getColumnsMetadata(String schema, String tableName) {
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
                map.put("column_name", rs.getString(1));
                map.put("column_comment", rs.getString(2));
                map.put("ordinal_position", rs.getInt(3));
                map.put("owner", rs.getString(4));
                map.put("default", rs.getString(5));
                map.put("data_length", rs.getInt(6));
                map.put("data_precision", rs.getString(7));
                map.put("data_scale", rs.getString(8));
                map.put("data_type", rs.getString(9));
                columnsMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getColumnsMetadata Exception] --> "
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
        List<Map<String,Object>> columnsMetadata = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getIndexesMetadata(schema, tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("owner", rs.getString(1));
                map.put("index_name", rs.getString(2));
                /*map.put("index_type", rs.getString(3));*/
                map.put("uniqueness", true);
                map.put("compression", rs.getString(5));
                map.put("status", rs.getString(6));
                columnsMetadata.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getIndexesMetadata Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return columnsMetadata;
    }

    @Override
    public List<Map<String, Object>> getTablesInfo(String schema) {
        Statement stmt = null;
        ResultSet rs = null;

        List<Map<String,Object>> list = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getSQLQueryTablesNameComments(schema);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("table_name", rs.getString(1));
                map.put("table_comment", rs.getString(2));
                map.put("data_length", rs.getString(3));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTablesInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return list;
    }

    @Override
    public Object getTableColumns(String tableName, String datasource, String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            if(StringUtils.isEmpty(databaseName)){
                return "用户名不能为空";
            }
            //获取查询指定表所有字段的sql语句
            String querySql = "SELECT b.column_name columnName \n" +
                    ",b.data_type dataType \n" +
                    ",a.comments comments \n" +
                    "FROM all_col_comments a \n" +
                    ",all_tab_columns b \n" +
                    "WHERE a.table_name = b.table_name and a.OWNER = b.OWNER and a.Column_name = b.Column_name and\n" +
                    "a.table_name = '"+tableName.toUpperCase()+"' and a.OWNER='"+databaseName.toUpperCase()+"'";
            logger.info("querySql: {}", querySql);
            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("COLUMN_NAME", rs.getString(1));
                map.put("DATA_TYPE", rs.getString(2));
                map.put("COLUMN_COMMENT", rs.getString(3)==null?"":rs.getString(3));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        result.put("datas", list);
        return result;
    }
}
