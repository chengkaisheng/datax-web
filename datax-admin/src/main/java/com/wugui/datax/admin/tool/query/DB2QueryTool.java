package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.entity.Chart;
import com.wugui.datax.admin.entity.ColumnMsg;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.meta.*;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.DBUtilErrorCode;
import com.wugui.datax.admin.util.DataXException;
import com.wugui.datax.admin.util.JdbcUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;


public class DB2QueryTool{

    private static final Logger logger = LoggerFactory.getLogger(DB2QueryTool.class);

    private static Connection connection = null;

    private DatabaseInterface sqlBuilder;

    public DB2QueryTool(JobDatasource jobDatasource) {
        sqlBuilder = DB2DatabaseMeta.getInstance();
        if (LocalCacheUtil.get(jobDatasource.getDatasourceName()) == null) {
            getDataSource(jobDatasource);
        }else {
            connection = (Connection) LocalCacheUtil.get(jobDatasource.getDatasourceName());
            if (connection == null) {
                LocalCacheUtil.remove(jobDatasource.getDatasourceName());
                getDataSource(jobDatasource);
            }
        }

        LocalCacheUtil.set(jobDatasource.getDatasourceName(), connection, 4 * 60 * 60 * 1000);
    }

    private void getDataSource(JobDatasource jobDatasource) {
        //加载驱动
        try{
            Class.forName(jobDatasource.getJdbcDriverClass());
            connection = DriverManager.getConnection(jobDatasource.getJdbcUrl(),AESUtil.decrypt(jobDatasource.getJdbcUsername()),AESUtil.decrypt(jobDatasource.getJdbcPassword()));
            logger.info("建立连接");
        }catch (Exception e){
            throw new DataXException(DBUtilErrorCode.IMPALA_CONN_ERROR," 具体错误信息为："+e);
        }
    }

    public Boolean dataSourceTest(String databaseName) {
        return connection != null ? true : false;
    }

    public List<String> getCollectionNames(String databaseName) {
        List<String> list = new ArrayList<>();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "select TABNAME from SYSCAT.TABLES where TABSCHEMA NOT LIKE  'SYS%' and TYPE='T' ORDER BY TABSCHEMA, TABNAME";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(resultSet.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<String> getColumns(String tableName) {
        List<String> list = new ArrayList<String>();
        Statement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM " + tableName + " where 1=0";
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int count = metaData.getColumnCount();

            for(int i = 1 ; i <= count; i++){
                list.add(metaData.getColumnName(i));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Long getRows(String tableName) {
        Long rows=0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getRows(tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                rows = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return rows;
    }

    public Map<String, Object> listAll(List<String> columns, String tableName,Integer pageNumber,Integer pageSize) {
        List<List<Map<String,Object>>> datas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getListAll(tableName, pageNumber, pageSize,columns.get(0));
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                List<Map<String,Object>> colList=new ArrayList<>();
                for (int i=1;i<=columns.size();i++) {
                    Map<String,Object> map=new HashMap<>();
                    Object value = rs.getObject(i);
                    map.put(columns.get(i-1),value);
                    colList.add(map);
                }
                datas.add(colList);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        Map<String,Object> ret=new HashMap(){{
            this.put("datas",datas);
            this.put("total",getRows(tableName));
        }};
        return ret;
    }

    public String getTableSize(String tableName, String tableSchema){
        Long tableSize =0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getTableSize(tableName,tableSchema);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                tableSize=rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tableSize+"KB";
    }

    public List<ColumnMsg> getColumnSchema(String tableName, String tableSchema){
        List<ColumnMsg> columnMsgs = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getColumnSchema(tableName,tableSchema);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ColumnMsg columnMsg=new ColumnMsg();
                String name = rs.getString(1);
                String comment=rs.getString(2);
                String type=rs.getString(3);
                columnMsgs.add(columnMsg);
                columnMsg.setName(name);
                columnMsg.setComment(comment);
                String showType=this.judgeShowType(type);
                columnMsg.setIndicator(this.getIndicator(name,tableName,showType));
                logger.info(name+"************"+type+"*************showType: "+showType);
                if("dateType".equals(showType)){
                    columnMsg.setType("date");
                    columnMsg.setStatistics(this.getDateChart(name,tableName));
                }else if("numberType".equals(showType)){
                    columnMsg.setType("number");
                    columnMsg.setStatistics(this.getChart(name,tableName));
                }else {
                    columnMsg.setType("string");
                    columnMsg.setStatistics(this.getUnique(name,tableName));
                }

            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return columnMsgs;
    }

    public Map<String,Map<String,Object>> getIndicator(String fieldName,String tableName,String showType){
        Long missing=0L;
        Long num=0L;
        Object fieldVal=null;
        Object maximum=null;
        Object minimum=null;
        Map<String,Map<String,Object>> map=new HashMap<>();
        Map<String,Object> validMap=new HashMap<>();
        Map<String,Object> missingMap=new HashMap<>();
        Map<String,Object> uniqueMap=new HashMap<>();
        Map<String,Object> mostCommonMap=new HashMap<>();
        Map<String,Object> maximumMap=new HashMap<>();
        Map<String,Object> minimumMap=new HashMap<>();
        Statement stmt = null;
        ResultSet rs = null;
        Long total=getRows(tableName);
        try {
            stmt = connection.createStatement();
            String sql = sqlBuilder.getMissing(fieldName,tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                missing = rs.getLong(1);
                missingMap.put("value",missing);
                missingMap.put("rate",getRate(missing,total));
                validMap.put("value",total-missing);
                validMap.put("rate",getRate(total-missing,total));
            }
            sql=sqlBuilder.getMostCommon(fieldName,tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                num = rs.getLong(1);
                fieldVal=rs.getObject(2);
                mostCommonMap.put("value",fieldVal);
                mostCommonMap.put("rate",getRate(num,total));
            }
            Long unique=getUnique(fieldName,tableName);
            uniqueMap.put("value",unique);
            map.put("valid",validMap);
            map.put("misssing",missingMap);
            map.put("unique",uniqueMap);
            map.put("mostCommon",mostCommonMap);
            if("numberType".equals(showType)){
                sql=sqlBuilder.getMaxMin(fieldName,tableName);
                rs = stmt.executeQuery(sql);
                if(rs.next()){
                    maximum = rs.getObject(1);
                    minimum=rs.getObject(2);
                    maximumMap.put("value",maximum);
                    minimumMap.put("value",minimum);
                }
                map.put("maximum",maximumMap);
                map.put("minimum",minimumMap);
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

    private String getRate(Long a,Long b){
        if(b==0){
            logger.info("表记录数为0，除数不能为0");
            throw  new RuntimeException("表记录数为0，除数不能为0");
        }
        if (a==0){
            return "0%";
        }
        NumberFormat numberFormat = NumberFormat.getInstance();
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)a/(float)b*100);
        return result + "%";
    }

    private String getDateChart(String name,String tableName) {
        java.util.Date dt=new java.util.Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getDateStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Date temp=rs.getTimestamp(1);
                if(temp!=null){
                    dt=temp;
                }

            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        logger.info("date-------->"+sdf.format(dt));
        return sdf.format(dt);
        //return dt.toString();
    }

    private Long getUnique(String name,String tableName) {
        Long ret = 0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getStringStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ret = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return ret;
    }

    private List<Chart<Float>> getChart(String name, String tableName) {
        List<Chart<Float>> charts = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getNumberStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Chart<Float> chart=new Chart();
                Float min = rs.getFloat(1);
                Float max=rs.getFloat(2);
                Long number=rs.getLong(3);
                charts.add(chart);
                chart.setMin(min);
                chart.setMax(max);
                chart.setNumber(number);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return charts;
    }

    private String judgeShowType(String type) {
        type=type.toUpperCase();
        //加入oracle特有的字段类型：VARCHAR2，NUMBER
        String[] stringType={"CHAR","VARCHAR","TINYBLOB","TINYTEXT","BLOB","TEXT","MEDIUMBLOB","MEDIUMTEXT","LONGBLOB","LONGTEXT","VARCHAR2"};
        String[] dateType={"DATE","TIME","YEAR","DATETIME","TIMESTAMP"};
        String[] numberType={"TINYINT","SMALLINT","MEDIUMINT","INT","INTEGER","BIGINT","FLOAT","DOUBLE","DECIMAL","NUMBER"};
        if (Arrays.asList(stringType).contains(type.trim().toUpperCase())){
            return "stringType";
        }else if(Arrays.asList(dateType).contains(type.trim().toUpperCase())){
            return "dateType";
        }else if(Arrays.asList(numberType).contains(type.trim().toUpperCase())){
            return "numberType";
        }
        return "stringType";
    }
}
