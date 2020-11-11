package com.wugui.datax.admin.tool.meta;

public class ImpalaDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface{

    private volatile static ImpalaDatabaseMeta single;

    public static DatabaseInterface getInstance() {
        if(single == null){
            synchronized (ImpalaDatabaseMeta.class) {
                if(single == null){
                    single = new ImpalaDatabaseMeta();
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
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize, String columnName) {
        return String.format("select * from %s order by %s asc limit %s offset (%s-1)*%s",tableName,columnName,pageSize,pageNumber,pageSize);
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return "DESCRIBE "+tableName;
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return "SHOW TABLE STATS "+tableName;
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return String.format("select top 1 count(*) num,%s from %s group by %s ORDER BY num desc"
                ,name,tableName,name);
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "show tables in "+tableSchema[0];
    }
}
