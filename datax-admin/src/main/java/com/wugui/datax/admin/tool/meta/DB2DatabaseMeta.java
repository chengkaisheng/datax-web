package com.wugui.datax.admin.tool.meta;

public class DB2DatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static DB2DatabaseMeta single;

    public static DB2DatabaseMeta getInstance() {
        if (single == null) {
            synchronized (DB2DatabaseMeta.class) {
                if (single == null) {
                    single = new DB2DatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize, String columnName) {
        return String.format("select * from (\n" +
                "select t.*,row_number() over(ORDER BY %s ASC) as r\n" +
                "from %s t)\n" +
                "where r between %s AND %s",columnName,tableName,(pageNumber-1)*pageSize+1,pageNumber*pageSize);
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return "SELECT DATA_OBJECT_P_SIZE FROM TABLE (SYSPROC.ADMIN_GET_TAB_INFO('"+tableSchema+"', '"+tableName+"'))";
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return "select NAME,REMARKS,COLTYPE from sysibm.SYSCOLUMNS where tbname = '"+tableName+"'";
    }

    @Override
    public String getMissing(String name, String tableName) {
        return "select count(*) from "+tableName+" where "+name+" is null";
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return String.format("select count(*) num,%s from %s group by %s ORDER BY num desc fetch first 1 rows only"
                ,name,tableName,name);
    }
}
