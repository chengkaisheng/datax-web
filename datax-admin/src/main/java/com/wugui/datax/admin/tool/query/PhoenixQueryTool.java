package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.DBUtilErrorCode;
import com.wugui.datax.admin.util.DataXException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.*;

public class PhoenixQueryTool {

    private static final Logger logger = LoggerFactory.getLogger(PhoenixQueryTool.class);

    private static Connection connection = null;

    public PhoenixQueryTool(JobDatasource jobDatasource) throws IOException {
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
            connection = DriverManager.getConnection(jobDatasource.getJdbcUrl(),jobDatasource.getJdbcUsername(),jobDatasource.getJdbcPassword());
            logger.info("建立连接");
        }catch (Exception e){
            throw new DataXException(DBUtilErrorCode.IMPALA_CONN_ERROR," 具体错误信息为："+e);
        }
    }

    public Boolean dataSourceTest(String databaseName) {
        return connection != null ? true : false;
    }
}
