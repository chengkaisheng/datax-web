package com.wugui.datax.admin.tool.meta;

import java.util.List;

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
        return "select table_name,table_comment,data_length from information_schema.tables where table_schema=?";
    }

    @Override
    public String getSQLQueryTablesNameComments(String schema) {
        return null;
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
    public String getRows(String tableName,String columnName) {
        return "select count(*) from "+tableName;
    }

    @Override
    public String getListAll(String tableName,Integer pageNumber,Integer pageSize,String columnName){
        return "select * from "+tableName+" limit "+(pageNumber-1)*pageSize+","+pageSize;
    }

    @Override
    public String getColumnSchema(String tableName,String tableSchema){
        return "select column_name,column_comment,data_type from information_schema.columns where table_name='"+tableName+"' and table_schema='"+tableSchema+"'";
    }

    @Override
    public String getDBName(){
        return "select database()";
    }

    //SQLServer可能存在除数为0错误，step字段
    @Override
    public String getNumberStatistics(String name,String tableName){
        return String.format("select minv+floor((b.%s - a.minv)/step)*step as start_bound,(floor((b.%s - a.minv)/step)+1)*step+minv as end_bound,count(1) from %s b left join (select min(a.%s) minv, (max(a.%s) - min(a.%s))/8 step from %s a) a on 1 = 1 group by floor((b.%s - a.minv)/step),minv,step order by start_bound asc"
                ,name,name,tableName,name,name,name,tableName,name);
    }

    @Override
    public String getStringStatistics(String name, String tableName){
        return String.format("select count(%s) from(SELECT DISTINCT %s from %s) as ooxx",name,name,tableName);
    }

    @Override
    public String getDateStatistics(String name, String tableName) {
        /*
            select from_unixtime(minv+floor((UNIX_TIMESTAMP(b.dt) - a.minv)/step)*step,'%Y-%m-%d %H:%i:%S'),
            from_unixtime((floor((UNIX_TIMESTAMP(b.dt) - a.minv)/step)+1)*step+minv,'%Y-%m-%d %H:%i:%S'),
            count(1) from pv_day_3 b left join (select UNIX_TIMESTAMP(min(a.dt)) minv,
            (UNIX_TIMESTAMP(max(a.dt)) - UNIX_TIMESTAMP(min(a.dt)))/8 step from pv_day_3 a) a on 1 = 1 group by
            floor((UNIX_TIMESTAMP(b.dt) - a.minv)/step),minv,step
         */
        return "select max("+name+") from "+tableName;
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return String.format("SELECT DATA_LENGTH FROM information_schema.`TABLES` where TABLE_SCHEMA='%s' AND TABLE_NAME='%s'",tableSchema,tableName);
    }

    @Override
    public String getMissing(String name, String tableName) {
        return "select count(*) from "+tableName+" where "+name+" is null or "+name+"=''";
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return String.format("select count(*) num,%s from %s group by %s ORDER BY num desc limit 0,1"
                ,name,tableName,name);
    }

    @Override
    public String getMaxMin(String fieldName, String tableName) {
        return "select max("+fieldName+"),min("+fieldName+") from "+tableName;
    }

    @Override
    public String getDBSchema() {
        return null;
    }

    @Override
    public String getSchemaMetadata(String schema) {
        return null;
    }

    @Override
    public String getTableMetadata(String schema, String tableName) {
        return null;
    }

    @Override
    public String getTablesMetadata(String schema) {
        return null;
    }

    @Override
    public String getColumnMetadata(String schema, String tableName, String column) {
        return null;
    }

    @Override
    public String getColumnsMetadata(String schema, String tableName) {
        return null;
    }

    @Override
    public String getIndexesMetadata(String schema, String tableName) {
        return null;
    }

    @Override
    public String getIndexMetadata(String schema, String tableName, String index) {
        return null;
    }

    @Override
    public String getIndexName(String schema, String tableName) {
        return null;
    }

    @Override
    public String getSqlQueryData(String writerTable, List<String> writerColumns){
        String sql = null;
        StringBuffer sb = new StringBuffer("SELECT ");
        for (String str : writerColumns){
            sb.append(str+",");
        }

        if(sb.length() > 0){
            sql = sb.substring(0,sb.length()-1);
        }
        sql = sql + " FROM " + writerTable;

        return sql;
    }
}
