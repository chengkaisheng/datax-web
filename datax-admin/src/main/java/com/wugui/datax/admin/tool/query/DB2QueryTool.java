package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.DBUtilErrorCode;
import com.wugui.datax.admin.util.DataXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DB2QueryTool{

    private static final Logger logger = LoggerFactory.getLogger(DB2QueryTool.class);

    private static Connection connection = null;

    public DB2QueryTool(JobDatasource jobDatasource) {

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
}
