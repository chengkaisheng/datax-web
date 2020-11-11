package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class DB2QueryTool extends BaseQueryTool implements QueryToolInterface{

    private static final Logger logger = LoggerFactory.getLogger(DB2QueryTool.class);

    private static Connection connection = null;

    public DB2QueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    public Boolean dataSourceTest(String databaseName) {
        return connection != null ? true : false;
    }

}
