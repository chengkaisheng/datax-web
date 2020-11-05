package com.wugui.datax.admin.util.metadata.tool;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.query.BaseQueryTool;
import com.wugui.datax.admin.tool.query.OracleQueryTool;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.RdbmsException;

import java.sql.SQLException;

/**
 * 工具类，获取单例实体
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName QueryToolFactory
 * @Version 1.0
 * @since 2019/7/18 9:36
 */
public class MetadataAssembleHelperFactory {

    public static BaseMetadataAssembleHelper getCloudBeaverHelper(JobDatasource jobDatasource) {
        //获取dbType
        String datasource = jobDatasource.getDatasource();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            return getMySQLCloudBeaverHelper(jobDatasource);
        } else if (JdbcConstants.ORACLE.equals(datasource)) {
            return getOracleMetadataQueryInstance(jobDatasource);
        } else if (JdbcConstants.POSTGRESQL.equals(datasource) || JdbcConstants.GREENPLUM.equals(datasource)) {
            return getPostgresqlCloudBeaverHelper(jobDatasource);
        } /*else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
            return getSqlserverMetadataQueryInstance(jobDatasource);
        }*//*else if (JdbcConstants.HIVE.equals(datasource)) {
            return getHiveMetadataQueryInstance(jobDatasource);
        } */else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
            return getClickHouseCloudBeaverHelper(jobDatasource);
        }/*else if (JdbcConstants.HBASE20XSQL.equals(datasource)) {
            return getHbase20XsqlMetadataQueryInstance(jobDatasource);
        }*/
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }

    private static BaseMetadataAssembleHelper getMySQLCloudBeaverHelper(JobDatasource jdbcDatasource) {
        try {
            return new MysqlMetadataAssembleHelper(jdbcDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.MYSQL,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseMetadataAssembleHelper getOracleMetadataQueryInstance(JobDatasource jdbcDatasource) {
        try {
            return new OracleMetadataAssembleHelper(jdbcDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.ORACLE,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    private static BaseMetadataAssembleHelper getPostgresqlCloudBeaverHelper(JobDatasource jdbcDatasource) {
        try {
            return new PostgresqlMetadataAssembleHelper(jdbcDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.POSTGRESQL,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }

    /*private static BaseQueryTool getSqlserverMetadataQueryInstance(JobDatasource jdbcDatasource) {
        try {
            return new SqlServerQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.SQL_SERVER,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }*/

    /*private static BaseQueryTool getHiveMetadataQueryInstance(JobDatasource jdbcDatasource) {
        try {
            return new HiveQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.HIVE,
                    e,jdbcDatasource.getJdbcUsername(),jdbcDatasource.getDatasourceName());
        }
    }*/
    private static BaseMetadataAssembleHelper getClickHouseCloudBeaverHelper(JobDatasource jdbcDatasource) {
        try {
            return new ClickHouseMetadataAssembleHelper(jdbcDatasource);
        } catch (Exception e) {
            throw RdbmsException.asConnException(JdbcConstants.CLICKHOUSE,
                    e, jdbcDatasource.getJdbcUsername(), jdbcDatasource.getDatasourceName());
        }
    }

    /*private static Hbase20XsqlQueryTool getHbase20XsqlMetadataQueryInstance(JobDatasource jdbcDatasource) {
        try {
            return new Hbase20XsqlQueryTool(jdbcDatasource);
        } catch (SQLException e) {
            throw RdbmsException.asConnException(JdbcConstants.HBASE20XSQL,
                    e, jdbcDatasource.getJdbcUsername(), jdbcDatasource.getDatasourceName());
        }
    }*/
}
