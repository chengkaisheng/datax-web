package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class DB2QueryTool extends BaseQueryTool implements QueryToolInterface{

    private static final Logger logger = LoggerFactory.getLogger(DB2QueryTool.class);

//    private static Connection connection = null;

    public DB2QueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

//    public Boolean dataSourceTest(String databaseName) {
//        return connection != null ? true : false;
//    }
    public Object getTableColumns(String tableName,String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "SELECT a.name columnName,a.coltype columnType,COALESCE(d.remarks, '') tbDesc FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME " +
                        "where d.type='T'  AND c.TABSCHEMA='"+databaseName+"'";
            }else {
                querySql = "SELECT C.TABNAME ,a.name columnName,a.coltype columnType,COALESCE(a.remarks, '') COLUMN_COMMENT,c.TABSCHEMA,COALESCE(d.remarks, '') TABLE_COMMENT  FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME  " +
                        "where d.type='T' AND c.TABSCHEMA='"+databaseName+"' AND  d.name='"+tableName+"' ";
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("TABLENAME",rs.getString(1).trim());
                map.put("COLUMN_NAME", rs.getString(2).trim());
                map.put("DATA_TYPE", rs.getString(3).trim());
                map.put("COLUMN_COMMENT", rs.getString(4).trim());
                map.put("TABSCHEMA", rs.getString(5).trim());
                map.put("TABLE_COMMENT", rs.getString(6).trim());
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

    public Object getPKColumns(String tableName,String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "SELECT a.name columnName,a.coltype columnType,COALESCE(d.remarks, '') tbDesc FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME " +
                        "where d.type='T'  AND c.TABSCHEMA='"+databaseName+"'";
            }else {
                querySql = "select A.CONSTNAME,A.TABSCHEMA,A.TABNAME,B.COLNAME  from syscat.tabconst A ,SYSCAT.KEYCOLUSE B WHERE A.CONSTNAME = B.CONSTNAME AND A.TYPE='P'"+
                            " and A.TABSCHEMA='"+databaseName+"'"+
                            " and A.TABNAME='"+tableName+"'";
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("TABLENAME",rs.getString(3).trim());
                map.put("COLUMN_NAME", rs.getString(4).trim());
                map.put("CONSTNAME", rs.getString(1).trim());
                map.put("TABSCHEMA", rs.getString(2).trim());
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

    public Object getFKColumns(String tableName, String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "SELECT a.name columnName,a.coltype columnType,COALESCE(d.remarks, '') tbDesc FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME " +
                        "where d.type='T'  AND c.TABSCHEMA='"+databaseName+"'";
            }else {
                querySql = "select tabname,constname,fk_colnames,reftabname,pk_colnames,TABSCHEMA from syscat.references  where "+
                        "TABSCHEMA='"+databaseName+"' and tabname='"+tableName+"'";
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("TABLENAME",rs.getString(1).trim());
                map.put("FK_COLUMN_NAME", rs.getString(3).trim());
                map.put("CONSTNAME", rs.getString(2).trim());
                map.put("TABSCHEMA", rs.getString(6).trim());
                map.put("reftabname", rs.getString(4).trim());
                map.put("pk_colnames", rs.getString(5).trim());
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

    public Object getUKColumns(String tableName,String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "SELECT a.name columnName,a.coltype columnType,COALESCE(d.remarks, '') tbDesc FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME " +
                        "where d.type='T'  AND c.TABSCHEMA='"+databaseName+"'";
            }else {
                querySql = "select A.TABSCHEMA,A.TABNAME  ,A.CONSTNAME ,B.COLNAME from syscat.tabconst A ,SYSCAT.KEYCOLUSE B WHERE A.CONSTNAME = B.CONSTNAME AND A.TYPE='U'  and "+
                        "A.TABSCHEMA='"+databaseName+"' and A.tabname='"+tableName+"'";
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("TABSCHEMA",rs.getString(1).trim());
                map.put("TABLENAME", rs.getString(2).trim());
                map.put("COLUMN_NAME", rs.getString(4).trim());
                map.put("CONSTNAME", rs.getString(3).trim());
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


    public Object getCKColumns(String tableName,String databaseName) {
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            String querySql;
            if(StringUtils.isEmpty(databaseName)){
                querySql = "SELECT a.name columnName,a.coltype columnType,COALESCE(d.remarks, '') tbDesc FROM sysibm.syscolumns a " +
                        "INNER JOIN sysibm.systables d on a.tbname=d.name " +
                        "INNER  JOIN syscat.TABLES c ON a.tbname=c.TABNAME " +
                        "where d.type='T'  AND c.TABSCHEMA='"+databaseName+"'";
            }else {
                querySql = "select TABSCHEMA ,TABNAME ,CONSTNAME ,TEXT from syscat.checks WHERE "+
                        "TABSCHEMA='"+databaseName+"' and TABNAME='"+tableName+"'";
            }
            //获取查询指定表所有字段的sql语句
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("TABSCHEMA",rs.getString(1).trim());
                map.put("TABLENAME", rs.getString(2).trim());
                map.put("CONSTNAME", rs.getString(3).trim());
                map.put("TEXT", rs.getString(4).trim());
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

}
