package com.wugui.datax.admin.tool.meta;
/**
 * Oracle数据库 meta信息查询
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName MySQLDatabaseMeta
 * @Version 1.0
 * @since 2019/7/17 15:48
 */
public class OracleDatabaseMeta extends BaseDatabaseMeta implements DatabaseInterface {

    private volatile static OracleDatabaseMeta single;

    public static OracleDatabaseMeta getInstance() {
        if (single == null) {
            synchronized (OracleDatabaseMeta.class) {
                if (single == null) {
                    single = new OracleDatabaseMeta();
                }
            }
        }
        return single;
    }


    @Override
    public String getSQLQueryComment(String schemaName, String tableName, String columnName) {
        return String.format("select B.comments \n" +
                "  from user_tab_columns A, user_col_comments B\n" +
                " where a.COLUMN_NAME = b.column_name\n" +
                "   and A.Table_Name = B.Table_Name\n" +
                "   and A.Table_Name = upper('%s')\n" +
                "   AND A.column_name  = '%s'", tableName, columnName);
    }

    @Override
    public String getSQLQueryPrimaryKey() {
        return "select cu.column_name from user_cons_columns cu, user_constraints au where cu.constraint_name = au.constraint_name and au.owner = ? and au.constraint_type = 'P' and au.table_name = ?";
    }

    @Override
    public String getSQLQueryTablesNameComments() {
        return "select table_name,comments from user_tab_comments";
    }

    @Override
    public String getSQLQueryTableNameComment() {
        return "select table_name,comments from user_tab_comments where table_name = ?";
    }

    @Override
    public String getSQLQueryTables(String... tableSchema) {
        return "select table_name from dba_tables where owner='" + tableSchema[0] + "'";
    }

    @Override
    public String getSQLQueryTableSchema(String... args) {
        return "select username from sys.dba_users";
    }


    @Override
    public String getSQLQueryTables() {
        return "select table_name from user_tab_comments";
    }

    @Override
    public String getSQLQueryColumns(String... args) {
        return "select table_name,comments from user_tab_comments where table_name = ?";
    }

    @Override
    public String getDBName(){
        return "select name from v$database";
    }

    @Override
    public String getColumnSchema(String tableName, String tableSchema) {
        return "select a.column_name, u.comments,a.data_type from (select column_name,data_type from all_tab_cols WHERE table_name = '"+tableName+"') a left join user_col_comments u on a.COLUMN_NAME=u.COLUMN_NAME where u.TABLE_NAME='"+tableName+"'";
    }

    @Override
    public String getStringStatistics(String name, String tableName) {
        return String.format("select count(%s) from(SELECT DISTINCT %s from %s)",name,name,tableName);
    }

    @Override
    public String getDateStatistics(String name, String tableName) {
        return String.format("select minv+floor((b.%s - a.minv)/step)*step as start,(floor((b.%s - a.minv)/step)+1)*step+minv as end,count(1) from %s b left join (select min(a.%s) minv, (max(a.%s) - min(a.%s))/3 step from %s a) a on 1 = 1 group by floor((b.%s - a.minv)/step),minv,step order by start asc",
                name,name,tableName,name,name,name,tableName,name);
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize,String columnName) {
        return String.format("select * from (select ROWNUM AS rowno,t.* from %s t) where rowno > (%s-1)*%s and rowno<=%s*%s",tableName,pageNumber,pageSize,pageNumber,pageSize);
    }
}
