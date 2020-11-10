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
        return "select table_name from USER_TABLES where TABLESPACE_NAME='" + tableSchema[0].toUpperCase() + "'";
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
        /*return String.format("select minv+floor((b.%s - a.minv)/step)*step as start,(floor((b.%s - a.minv)/step)+1)*step+minv as end,count(1) from %s b left join (select min(a.%s) minv, (max(a.%s) - min(a.%s))/3 step from %s a) a on 1 = 1 group by floor((b.%s - a.minv)/step),minv,step order by start asc",
                name,name,tableName,name,name,name,tableName,name);*/
        return "select max("+name+") from "+tableName;
    }

    @Override
    public String getTableSize(String tableName, String tableSchema) {
        return "select round(sum(BYTES)/1024,2) from user_segments where segment_name='"+tableName+"'";
    }

    @Override
    public String getListAll(String tableName, Integer pageNumber, Integer pageSize,String columnName) {
        return String.format("select b.* from (select rn,ri from (select ROWNUM AS rn,rowid ri from %s t) where rn > (%s-1)*%s and rn<=%s*%s) a,%s b where a.ri=b.rowid"
                ,tableName,pageNumber,pageSize,pageNumber,pageSize,tableName);
    }

    @Override
    public String getMostCommon(String name, String tableName) {
        return "select * from (select count(*) num,"+name+" from "+tableName+" group by "+name+" ORDER BY num desc) where rownum=1";
    }

    @Override
    public String getSchemaMetadata(String schema) {
        return "SELECT t.USERNAME , t.CREATED FROM USER_USERS t WHERE t.USERNAME = '"+schema+"'";
    }

    @Override
    public String getTableMetadata(String schema, String tableName) {
        return "SELECT a.TABLE_NAME , a.NUM_ROWS , b.COMMENTS ,c.BYTES , a.AVG_ROW_LEN , a.AVG_SPACE " +
                "FROM USER_TABLES a  " +
                "INNER JOIN USER_TAB_COMMENTS b " +
                "ON a.TABLE_NAME = b.TABLE_NAME  " +
                "INNER JOIN USER_SEGMENTS c " +
                "ON a.TABLE_NAME = c.SEGMENT_NAME WHERE a.TABLESPACE_NAME = '"+schema+"' AND a.TABLE_NAME='"+tableName+"'";
    }

    @Override
    public String getTablesMetadata(String schema) {
        return "SELECT a.TABLE_NAME , a.NUM_ROWS , b.COMMENTS ,c.BYTES , a.AVG_ROW_LEN , a.AVG_SPACE FROM USER_TABLES a INNER JOIN USER_TAB_COMMENTS b ON a.TABLE_NAME = b.TABLE_NAME  INNER JOIN USER_SEGMENTS c ON a.TABLE_NAME = c.SEGMENT_NAME WHERE a.TABLESPACE_NAME = '"+schema.toUpperCase()+"'";
    }

    @Override
    public String getColumnMetadata(String schema, String tableName, String column) {
        return super.getColumnMetadata(schema, tableName, column);
    }

    @Override
    public String getColumnsMetadata(String schema, String tableName) {
        return "SELECT a.COLUMN_NAME ,a.COMMENTS , b.COLUMN_ID ,a.OWNER  , b.DATA_DEFAULT ,b.DATA_LENGTH , b.DATA_PRECISION , b.DATA_SCALE , b.DATA_TYPE " +
                "FROM all_col_comments a " +
                ",all_tab_columns b " +
                "WHERE a.table_name = b.table_name and a.OWNER = b.OWNER and a.Column_name = b.Column_name and  " +
                "a.table_name = '"+tableName.toUpperCase()+"' and a.OWNER='"+schema.toUpperCase()+"'";
    }

    @Override
    public String getIndexesMetadata(String schema, String tableName) {
        return "SELECT t.OWNER, t.INDEX_NAME , t.INDEX_TYPE , t.UNIQUENESS , t.COMPRESSION , t.STATUS  FROM ALL_INDEXES t WHERE t.OWNER = '"+schema.toUpperCase()+"' AND  t.TABLE_NAME = '"+tableName.toUpperCase()+"'";
    }

    @Override
    public String getIndexMetadata(String schema, String tableName, String index) {
        return "SELECT t.OWNER, t.INDEX_NAME , t.INDEX_TYPE , t.UNIQUENESS , t.COMPRESSION , t.STATUS  FROM ALL_INDEXES t WHERE t.OWNER = '"+schema.toUpperCase()+"' AND  t.TABLE_NAME = '"+tableName.toUpperCase()+"'";
    }

    @Override
    public String getSQLQueryTablesNameComments(String schema) {
        return "SELECT b.tableName , b.comments , d.BYTES  FROM (select t.table_name tableName, f.comments comments " +
                "  from user_tables t " +
                " inner join user_tab_comments f " +
                "    on t.table_name = f.table_name WHERE t.tablespace_name='"+schema.toUpperCase()+"') b INNER JOIN user_segments d ON b.tableName=d.SEGMENT_NAME";
    }

    @Override
    public String getIndexName(String schema, String tableName) {
        return "SELECT t.INDEX_NAME FROM user_indexes t WHERE t.TABLE_OWNER = '"+schema.toUpperCase()+"' AND t.TABLE_NAME = '"+tableName.toUpperCase()+"'";
    }
}
