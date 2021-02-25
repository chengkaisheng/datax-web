package com.wugui.datax.admin.tool.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wugui.datax.admin.datashare.tools.ResultToJsonUtil.resultSetToJSON;

/**
 * hive
 *
 * @author wenkaijing
 * @version 2.0
 * @since 2020/01/05
 */
public class HiveQueryTool extends BaseQueryTool implements QueryToolInterface {
    public HiveQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    @Override
    public Object getTableColumns(String tableName, String datasource,String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "desc "+ tableName;
            }else {
                querySql = "desc "+databaseName+"."+tableName;
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("COLUMN_NAME", rs.getString(1));
                map.put("DATA_TYPE", rs.getString(2));
                map.put("COLUMN_COMMENT", rs.getString(3));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        result.put("datas", list);
        return result;
    }

    public String getLocation(String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
         StringBuilder str=new StringBuilder();
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = "show create table "+tableName;
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();
            while (rs.next()){
                str.append(rs.getString("createtab_stmt")).append("\n");
            }

        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return str.toString();
    }

    public int executeUpdate(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        int count=0;
        try {
            logger.info("querySql: {}", sql);

            //获取所有字段
            stmt = connection.createStatement();
            count = stmt.executeUpdate(sql);

        } catch (SQLException e) {
            logger.error("[createTable Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return count;
    }

    public void refreshTable() {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = "invalidate metadata";
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            stmt.executeUpdate(querySql);
        } catch (SQLException e) {
            logger.error("[refreshTable Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
    }
}
