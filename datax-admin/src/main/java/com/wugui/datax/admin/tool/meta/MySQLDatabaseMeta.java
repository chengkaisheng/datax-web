package com.wugui.datax.admin.tool.meta;

/**
 * MySQL数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:48
 */
public class MySQLDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static MySQLDatabaseMeta single;

    public static MySQLDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (MySQLDatabaseMeta.class) {
                if (single == null) {
                    single = new MySQLDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("SELECT COLUMN_COMMENT FROM information_schema.COLUMNS where TABLE_SCHEMA = '%s' and TABLE_NAME = '%s' and COLUMN_NAME = '%s'", schemaName, tableName, columnName);
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return "select column_name from information_schema.columns where table_schema=? and table_name=? and column_key = 'PRI'";
    }

    @Override
    public String getSQLQueryTables() {
        return "show tables";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select column_name from information_schema.columns where table_schema=? and table_name="+args[0];
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "show databases";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "select table_name from information_schema.tables where table_schema='"+tableSchema[0]+"'";
    }

    @Override
    public String getSQLQueryTablesNameComments(String schema) {
        return "select table_name ,table_comment,data_length from information_schema.tables where table_schema='"+schema+"'";
    }

    @Override
    public String getSchemaMetadata(String schema) {
        return "select * from information_schema.schemata where schema_name='" + schema + "'";
    }

    //查询某一张表的元数据
    @Override
    public String getTableMetadata(String schema, String tableName) {
        return "select * from information_schema.tables where schema_name='"+schema+"'and table_name='"+tableName+"'";
    }

    //查询一个库下的所有表的元数据
    @Override
    public String getTablesMetadata(String schema) {
        return "select * from information_schema.tables where schema_name='"+schema+"'";
    }


    @Override
    public String getColumnMetadata(String schema, String tableName, String column) {
        return "select * from information_schema.columns where schema='"+schema+"' and tableName='"+tableName+"' and column_name='" + column + "'";
    }

    @Override
    public String getColumnsMetadata(String schema, String tableName) {
        return "select * from information_schema.columns where schema='"+schema+"' and tableName='"+tableName+"'";
    }

    @Override
    public String getIndexesMetadata(String schema, String tableName) {
        return "SELECT a.index_name , a.stat_name , a.stat_value , a.sample_size , stat_description " +
                "FROM mysql.`innodb_index_stats` a " +
                "WHERE a.`database_name` = '"+schema+"' and table_name = '"+tableName+"';";
    }

    @Override
    public String getIndexMetadata(String schema, String tableName, String index) {
        return null;
    }

    @Override
    public String getIndexName(String schema, String tableName) {
        return "SELECT a.stat_name \n" +
                "FROM mysql.`innodb_index_stats` a \n" +
                "WHERE a.`database_name` = '"+schema+"' AND a.table_name = '"+tableName+"';";
    }
}
