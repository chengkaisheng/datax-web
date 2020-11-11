package com.wugui.datax.admin.tool.meta;

/**
 * Postgresql数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName PostgresqlDatabaseMeta
 * @Version 1.0
 * @since 2019/8/2 11:02
 */
public class PostgresqlDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static PostgresqlDatabaseMeta single;

    public static PostgresqlDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (PostgresqlDatabaseMeta.class) {
                if (single == null) {
                    single = new PostgresqlDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return "select column_name from information_schema.columns where table_schema='public' and table_name='tb_cis_patient_info' and is_identity = 'YES'";
    }

    @Override
    public String getSQLQueryTables() {
        return "select relname as tabname from pg_class c \n" +
                "where  relkind = 'r' and relname not like 'pg_%' and relname not like 'sql_%' group by relname order by relname limit 500";
    }


    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "SELECT concat_ws('.',\"table_schema\",\"table_name\") FROM information_schema.tables \n" +
                "where (\"table_name\" not like 'pg_%' AND \"table_name\" not like 'sql_%') \n" +
                "and table_type='BASE TABLE' and table_schema='" + tableSchema[0] + "'";
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "select table_schema FROM information_schema.tables where \"table_name\" not like 'pg_%' or \"table_name\" not like 'sql_%' group by table_schema;";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "SELECT a.attname as name \n" +
                "FROM pg_class as c,pg_attribute as a where c.relname = ? and a.attrelid = c.oid and a.attnum>0";
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return null;
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize, String columnName) {
        return String.format("select * from %s limit %s offset (%s-1)*%s"
                ,tableName,pageSize,pageNumber,pageSize);
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return String.format("SELECT pa.attname as name,\n" +
                "col_description(pa.attrelid, pa.attnum) as comment,\n" +
                "\tformat_type(pa.atttypid, pa.atttypmod) as type\n" +
                " from pg_class pc,pg_attribute pa \n" +
                "where pa.attrelid = pc.oid and pc.relname = '%s' and pa.attnum>0",tableName);
    }

    @Override
    public String getSQLQueryTablesNameComments(String schema) {
        return "select relname as table_name,cast(obj_description(relfilenode,'pg_class') as varchar) as table_comment from pg_class c\n" +
                "where relname in (select tablename from pg_tables where schemaname='"+schema+"' and position('_2' in tablename)=0 )";
    }

    @Override
    public String getMissing(String name, String tableName) {
        return "select count(*) from "+tableName+" where "+name+" is null";
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return String.format("select count(*) num,%s from %s group by %s ORDER BY num desc limit 1 OFFSET 0"
                ,name,tableName,name);
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return "select pg_size_pretty(pg_relation_size(relid)) from pg_stat_user_tables where schemaname='public' and relname='"+tableName+"'";
    }
}
