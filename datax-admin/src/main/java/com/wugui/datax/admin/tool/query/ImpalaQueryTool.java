package com.wugui.datax.admin.tool.query;

import com.google.common.collect.Lists;
import com.wugui.datatx.core.util.Constants;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.JdbcUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-10-11:03
 */
public class ImpalaQueryTool extends BaseQueryTool implements QueryToolInterface {
    /**
     * 构造方法
     *
     * @param jobDatasource
     */
    public ImpalaQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    @Override
    public Boolean dataSourceTest(String databaseName) {
        return connection != null ? true:false;
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
                querySql = "describe "+ tableName;
            }else {
                querySql = "describe "+databaseName+"."+tableName;
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


    public List<String> getImpalaColumnNames(String tableName, String datasource) {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                if (JdbcConstants.IMPALA.equals(datasource)) {
                    if (columnName.contains(Constants.SPLIT_POINT)) {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName.substring(columnName.indexOf(Constants.SPLIT_POINT) + 1) + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    } else {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    }
                } else {
                    res.add(columnName);
                }

            }
        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    public String getLocation(String tableName) {
        Statement stmt = null;
        ResultSet rs = null;
        String str=null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = "show create table "+tableName;
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            while (rs.next()){
               str=rs.getString(1);
            }

        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return str;
    }

    public int executeUpdate(String sql) {
        Statement stmt = null;
        ResultSet rs = null;
        String str=null;
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
        StringBuilder str=new StringBuilder();
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = "invalidate metadata;";
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
    }
}
