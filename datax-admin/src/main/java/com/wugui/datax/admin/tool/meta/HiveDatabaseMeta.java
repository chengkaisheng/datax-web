package com.wugui.datax.admin.tool.meta;

import java.util.List;

/**
 * hive元数据信息
 *
 * @author jingwk
 * @ClassName HiveDatabaseMeta
 * @Version 2.0
 * @since 2020/01/05 15:45
 */
public class HiveDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {
    private volatile static HiveDatabaseMeta single;

    public static HiveDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (HiveDatabaseMeta.class) {
                if (single == null) {
                    single = new HiveDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryTables() {
        return "show tables";
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "show databases";
    }

    public String getRows(String tableName,String columnName) {
        return String.format("SELECT COUNT(%s) FROM %s",columnName,tableName);
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize, String columnName) {
        return String.format("select * from (\n" +
                "select t.*,row_number() over(ORDER BY %s ASC) as r\n" +
                "from %s t) ret\n" +
                "where r between %s AND %s",columnName,tableName,(pageNumber-1)*pageSize+1,pageNumber*pageSize);
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return "desc extended "+tableName;
    }

    @Override
    public String getNumberStatistics(String name, String tableName) {
        return "select count(distinct "+name+") from "+tableName;
    }

    @Override
    public String getStringStatistics(String name, String tableName) {
        return "select count(distinct "+name+") from "+tableName;
    }

    @Override
    public String getMissing(String name, String tableName) {
        return "select count("+name+") from "+tableName+" where "+name+" is null or "+name+"=''";
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return String.format("select count(%s) num,%s from %s group by %s ORDER BY num desc limit 1"
                ,name,name,tableName,name);
    }
    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "show tables in "+tableSchema[0];
    }

    @Override
    public String getSqlQueryData(String writerTable, List<String> writerColumns){
        String sql = null;
        StringBuffer sb = new StringBuffer("SELECT ");
        for (String str : writerColumns){
            sb.append(str.split("\\:")[1]+",");
        }

        if(sb.length() > 0){
            sql = sb.substring(0,sb.length()-1);
        }
        sql = sql + " FROM " + writerTable;

        return sql;
    }
}
