package com.wugui.datax.admin.tool.meta;

/**
 * meta信息interface
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:45
 */
public abstract class BaseDatabaseMeta implements DatabaseInterface {

    @Override
    public String getSQLQueryFields(String tableName) {
        return "SELECT * FROM " + tableName + " where 1=0";
    }

    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,table_comment from information_schema.tables where table_schema=?";
    }

    @Override
    public String getSQLQueryTableNameComment() {
        return "select table_name,table_comment from information_schema.tables where table_schema=? and table_name = ?";
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return null;
    }

    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return null;
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return null;
    }

    @Override
    public String getMaxId(String tableName, String primaryKey) {
        return String.format("select max(%s) from %s",primaryKey,tableName);
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return null;
    }

    @Override
    public String getSQLQueryTables() {
        return null;
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return null;
    }

    @Override
    public String getRows(String tableName) {
        return "select count(*) from "+tableName;
    }

    @Override
    public String getListAll(String tableName,Integer pageNumber,Integer pageSize){
        return "select * from "+tableName+" limit "+pageNumber+","+pageSize;
    }

    @Override
    public String getColumnSchema(String tableName,String tableSchema){
        return "select column_name,column_comment,data_type from information_schema.columns where table_name='"+tableName+"' and table_schema='"+tableSchema+"'";
    }

    @Override
    public String getDBName(){
        return "select database()";
    }

    @Override
    public String getNumberStatistics(String name,String tableName){
        return String.format("select minv+floor((b.%s - a.minv)/step)*step as start,(floor((b.%s - a.minv)/step)+1)*step+minv as end,count(1) from %s b left join (select min(a.%s) minv, (max(a.%s) - min(a.%s))/3 step from %s a) a on 1 = 1 group by floor((b.%s - a.minv)/step),minv,step order by start asc"
                ,name,name,tableName,name,name,name,tableName,name);
    }

    @Override
    public String getStringStatistics(String name, String tableName){
        return String.format("select count(%s) from(SELECT DISTINCT %s from %s) as ooxx",name,name,tableName);
    }

    @Override
    public String getDateStatistics(String name, String tableName) {
        return "select from_unixtime(UNIX_TIMESTAMP(minv)+floor((UNIX_TIMESTAMP(b."+name+") - UNIX_TIMESTAMP(a.minv))/step)*step,'%Y-%m-%d %H:%i:%S'),from_unixtime((floor((UNIX_TIMESTAMP(b."+name+") - UNIX_TIMESTAMP(a.minv))/step)+1)*step+UNIX_TIMESTAMP(minv),'%Y-%m-%d %H:%i:%S'),count(1) from "+tableName+" b left join (select UNIX_TIMESTAMP(min(a."+name+")) minv, (UNIX_TIMESTAMP(max(a."+name+")) - UNIX_TIMESTAMP(min(a."+name+")))/3 step from "+tableName+" a) a on 1 = 1 group by floor((UNIX_TIMESTAMP(b."+name+") - UNIX_TIMESTAMP(a.minv))/step),minv,step";
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return String.format("SELECT DATA_LENGTH FROM information_schema.`TABLES` where TABLE_SCHEMA='%s' AND TABLE_NAME='%s'",tableSchema,tableName);
    }
}
