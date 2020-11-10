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
    public String getSQLQueryTables(String... tableSchema) {
        return "show tables in "+tableSchema[0];
    }
}
