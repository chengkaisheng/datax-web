package com.wugui.datax.admin.util.metadata.tool;

import com.wugui.datax.admin.util.JdbcConstants;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-14:55
 */
public class MetadataType {

    public static Map<String,String> getMetaDataTypeName(String datasource){
        Map<String,String> map = new HashMap<>();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        } else if (JdbcConstants.ORACLE.equals(datasource)) {
            map.put("instance", "oracle_instance");
            map.put("db", "oracle_db");
            map.put("table", "oracle_table");
            map.put("column", "oracle_column");
            map.put("index", "oracle_index");
            return map;
        } else if (JdbcConstants.POSTGRESQL.equals(datasource) || JdbcConstants.GREENPLUM.equals(datasource)) {
            map.put("instance", "postgresql_instance");
            map.put("db", "postgresql_db");
            map.put("table", "postgresql_table");
            map.put("column", "postgresql_column");
            map.put("index", "postgresql_index");
            return map;
        } else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }else if (JdbcConstants.HIVE.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        } else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }else if (JdbcConstants.HBASE20XSQL.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }

    public static Map<String,String> getMetaDataUrlName(String datasource){
        Map<String,String> map = new HashMap<>();
        if (JdbcConstants.MYSQL.equals(datasource)) {
            map.put("db", "org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog");
            map.put("table", "org.jkiss.dbeaver.ext.mysql.model.MySQLTable");
            map.put("column", "org.jkiss.dbeaver.ext.mysql.model.MySQLTableColumn");
            map.put("index", "org.jkiss.dbeaver.ext.mysql.model.MySQLTableIndex");
            map.put("foreignKey", "org.jkiss.dbeaver.ext.mysql.model.MySQLTableForeignKey");
            return map;
        } else if (JdbcConstants.ORACLE.equals(datasource)) {
            map.put("instance", "oracle_instance");
            map.put("db", "oracle_db");
            map.put("table", "oracle_table");
            map.put("column", "oracle_column");
            map.put("index", "oracle_index");
            return map;
        } else if (JdbcConstants.POSTGRESQL.equals(datasource) || JdbcConstants.GREENPLUM.equals(datasource)) {
            map.put("instance", "postgresql_instance");
            map.put("db", "postgresql_db");
            map.put("table", "postgresql_table");
            map.put("column", "postgresql_column");
            map.put("index", "postgresql_index");
            return map;
        } else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }else if (JdbcConstants.HIVE.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        } else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }else if (JdbcConstants.HBASE20XSQL.equals(datasource)) {
            map.put("instance", "mysql_instance");
            map.put("db", "mysql_db");
            map.put("table", "mysql_table");
            map.put("column", "mysql_column");
            map.put("index", "mysql_index");
            return map;
        }
        throw new UnsupportedOperationException("找不到该类型: ".concat(datasource));
    }
}
