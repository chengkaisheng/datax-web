package com.wugui.datax.admin.tool.meta;

/**
 * SqlServer数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName SqlServerDatabaseMeta
 * @Version 1.0
 * @since 2019/8/2 15:45
 */
public class SqlServerDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {
    private volatile static SqlServerDatabaseMeta single;

    public static SqlServerDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (SqlServerDatabaseMeta.class) {
                if (single == null) {
                    single = new SqlServerDatabaseMeta();
                }
            }
        }
        return single;
    }

    @Override
    public String getSQLQueryTables() {
        return "SELECT Name FROM SysObjects Where XType='U' ORDER BY Name";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "select schema_name(schema_id)+'.'+object_name(object_id) from sys.objects \n" +
                "where type ='U' \n" +
                "and schema_name(schema_id) ='" + tableSchema[0] + "'";

    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "select distinct schema_name(schema_id) from sys.objects where type ='U';";
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize,String columnName) {
        return String.format("select top %s * \n" +
                "from %s \n" +
                "where %s>=\n" +
                "(select max(%s) \n" +
                "from (select top ((%s-1)*%s+1) %s\n" +
                "from %s \n" +
                "order by  %s asc) temp_max_ids) \n" +
                "order by %s;",pageSize,tableName,columnName,columnName,pageNumber,pageSize
                ,columnName,tableName,columnName,columnName);
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return String.format("select\n" +
                "\tcol.name as columnName,\n" +
                "\tCAST(ep.value  AS NVARCHAR(128)) AS comment,\n" +
                "\tt.name as dataType\n" +
                "from sys.objects obj \n" +
                "\tinner join sys.columns col on obj.object_id=col.object_id\n" +
                "\tleft join sys.types t on t.user_type_id=col.user_type_id\n" +
                "\tleft join sys.extended_properties ep on ep.major_id=obj.object_id\n" +
                "\tand ep.minor_id=col.column_id\n" +
                "\tand ep.name='MS_Description'\n" +
                "where obj.name = '%s'",tableName);
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return "exec sp_spaceused '"+tableName+"'";
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return "select top 1 count(*) num,"+name+" from "+tableName+" group by "+name+" ORDER BY num desc";
    }
}
