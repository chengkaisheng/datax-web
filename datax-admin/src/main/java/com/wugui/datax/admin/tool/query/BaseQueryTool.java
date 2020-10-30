package com.wugui.datax.admin.tool.query;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.wugui.datatx.core.util.Constants;
import com.wugui.datax.admin.core.util.LocalCacheUtil;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Search;
import com.wugui.datax.admin.tool.database.ColumnInfo;
import com.wugui.datax.admin.tool.database.DasColumn;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.meta.DatabaseInterface;
import com.wugui.datax.admin.tool.meta.DatabaseMetaFactory;
import com.wugui.datax.admin.tool.meta.PostgresqlDatabaseMeta;
import com.wugui.datax.admin.tool.meta.SqlServerDatabaseMeta;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.JdbcUtils;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.wugui.datax.admin.entity.Chart;
import com.wugui.datax.admin.entity.ColumnMsg;
import javax.sql.DataSource;
import java.sql.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;
import java.util.*;

import static com.wugui.datax.admin.datashare.tools.ResultToJsonUtil.resultSetToJSON;
import com.wugui.datax.admin.datashare.tools.ConnectUtil;
import com.wugui.datax.admin.datashare.tools.ResultToJsonUtil;
/**
 * 抽象查询工具
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName BaseQueryTool
 * @Version 1.0
 * @since 2019/7/18 9:22
 */
public abstract class BaseQueryTool implements QueryToolInterface {

    protected static final Logger logger = LoggerFactory.getLogger(BaseQueryTool.class);
    /**
     * 用于获取查询语句
     */
    private DatabaseInterface sqlBuilder;

    private DataSource datasource;

    private Connection connection;
    /**
     * 当前数据库名
     */
    private String currentSchema;
    private String currentDatabase;

    /**
     * 构造方法
     *
     * @param jobDatasource
     */
    BaseQueryTool(JobDatasource jobDatasource) throws SQLException {
        if (LocalCacheUtil.get(jobDatasource.getDatasourceName()) == null) {
            getDataSource(jobDatasource);
        } else {
            this.connection = (Connection) LocalCacheUtil.get(jobDatasource.getDatasourceName());
            if (!this.connection.isValid(500)) {
                LocalCacheUtil.remove(jobDatasource.getDatasourceName());
                getDataSource(jobDatasource);
            }
        }
        sqlBuilder = DatabaseMetaFactory.getByDbType(jobDatasource.getDatasource());
        currentSchema = getSchema(jobDatasource.getJdbcUsername());
        currentDatabase = jobDatasource.getDatasource();
        LocalCacheUtil.set(jobDatasource.getDatasourceName(), this.connection, 4 * 60 * 60 * 1000);
    }

    private void getDataSource(JobDatasource jobDatasource) throws SQLException {
        String userName = AESUtil.decrypt(jobDatasource.getJdbcUsername());

        //这里默认使用 hikari 数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(userName);
        dataSource.setPassword(AESUtil.decrypt(jobDatasource.getJdbcPassword()));
        dataSource.setJdbcUrl(jobDatasource.getJdbcUrl());
        dataSource.setDriverClassName(jobDatasource.getJdbcDriverClass());
        dataSource.setMaximumPoolSize(1);
        dataSource.setMinimumIdle(0);
        dataSource.setConnectionTimeout(30000);
        this.datasource = dataSource;
        this.connection = this.datasource.getConnection();
    }

    //根据connection获取schema
    private String getSchema(String jdbcUsername) {
        String res = null;
        try {
            res = connection.getCatalog();
        } catch (SQLException e) {
            try {
                res = connection.getSchema();
            } catch (SQLException e1) {
                logger.error("[SQLException getSchema Exception] --> "
                        + "the exception message is:" + e1.getMessage());
            }
            logger.error("[getSchema Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        // 如果res是null，则将用户名当作 schema
        if (StrUtil.isBlank(res) && StringUtils.isNotBlank(jdbcUsername)) {
            res = jdbcUsername.toUpperCase();
        }
        return res;
    }

    @Override
    public TableInfo buildTableInfo(String tableName) {
        //获取表信息
        List<Map<String, Object>> tableInfos = this.getTableInfo(tableName);
        if (tableInfos.isEmpty()) {
            throw new NullPointerException("查询出错! ");
        }

        TableInfo tableInfo = new TableInfo();
        //表名，注释
        List tValues = new ArrayList(tableInfos.get(0).values());

        tableInfo.setName(StrUtil.toString(tValues.get(0)));
        tableInfo.setComment(StrUtil.toString(tValues.get(1)));


        //获取所有字段
        List<ColumnInfo> fullColumn = getColumns(tableName);
        tableInfo.setColumns(fullColumn);

        //获取主键列
        List<String> primaryKeys = getPrimaryKeys(tableName);
        logger.info("主键列为：{}", primaryKeys);

        //设置ifPrimaryKey标志
        fullColumn.forEach(e -> {
            if (primaryKeys.contains(e.getName())) {
                e.setIfPrimaryKey(true);
            } else {
                e.setIfPrimaryKey(false);
            }
        });
        return tableInfo;
    }

    //无论怎么查，返回结果都应该只有表名和表注释，遍历map拿value值即可
    @Override
    public List<Map<String, Object>> getTableInfo(String tableName) {
        String sqlQueryTableNameComment = sqlBuilder.getSQLQueryTableNameComment();
        logger.info(sqlQueryTableNameComment);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTableNameComment, ImmutableList.of(currentSchema, tableName));
        } catch (SQLException e) {
            logger.error("[getTableInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getTablesInfo(String schema) {
        String sqlQueryTableNameComment = sqlBuilder.getSQLQueryTablesNameComments(schema);
        logger.info(sqlQueryTableNameComment);
        List<Map<String, Object>> res = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQueryTableNameComment);
            while (resultSet.next()){
                Map<String,Object> map = new HashMap<>();
                map.put("table_name", resultSet.getString(1));
                map.put("table_comment", resultSet.getString(2));
                res.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getTablesInfo() {
        String sqlQueryTableNameComment = sqlBuilder.getSQLQueryTablesNameComments();
        logger.info(sqlQueryTableNameComment);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTableNameComment, ImmutableList.of(currentSchema));
        } catch (SQLException e) {
            logger.error("[getTableInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<Map<String, Object>> getTables() {
        String sqlQueryTables = sqlBuilder.getSQLQueryTables();
        logger.info(sqlQueryTables);
        List<Map<String, Object>> res = null;
        try {
            res = JdbcUtils.executeQuery(connection, sqlQueryTables, ImmutableList.of(currentSchema));
        } catch (SQLException e) {
            logger.error("[getTables Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<ColumnInfo> getColumns(String tableName) {

        List<ColumnInfo> fullColumn = Lists.newArrayList();
        //获取指定表的所有字段
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(querySql);
            ResultSetMetaData metaData = resultSet.getMetaData();

            List<DasColumn> dasColumns = buildDasColumn(tableName, metaData);
            statement.close();

            //构建 fullColumn
            fullColumn = buildFullColumn(dasColumns);

        } catch (SQLException e) {
            logger.error("[getColumns Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return fullColumn;
    }

    private List<ColumnInfo> buildFullColumn(List<DasColumn> dasColumns) {
        List<ColumnInfo> res = Lists.newArrayList();
        dasColumns.forEach(e -> {
            ColumnInfo columnInfo = new ColumnInfo();
            columnInfo.setName(e.getColumnName());
            columnInfo.setComment(e.getColumnComment());
            columnInfo.setType(e.getColumnTypeName());
            columnInfo.setIfPrimaryKey(e.isIsprimaryKey());
            columnInfo.setIsnull(e.getIsNull());
            res.add(columnInfo);
        });
        return res;
    }

    //构建DasColumn对象
    private List<DasColumn> buildDasColumn(String tableName, ResultSetMetaData metaData) {
        List<DasColumn> res = Lists.newArrayList();
        try {
            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                DasColumn dasColumn = new DasColumn();
                dasColumn.setColumnClassName(metaData.getColumnClassName(i));
                dasColumn.setColumnTypeName(metaData.getColumnTypeName(i));
                dasColumn.setColumnName(metaData.getColumnName(i));
                dasColumn.setIsNull(metaData.isNullable(i));

                res.add(dasColumn);
            }

            Statement statement = connection.createStatement();

            if (currentDatabase.equals(JdbcConstants.MYSQL) || currentDatabase.equals(JdbcConstants.ORACLE)) {
                DatabaseMetaData databaseMetaData = connection.getMetaData();

                ResultSet resultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);

                while (resultSet.next()) {
                    String name = resultSet.getString("COLUMN_NAME");
                    res.forEach(e -> {
                        if (e.getColumnName().equals(name)) {
                            e.setIsprimaryKey(true);

                        } else {
                            e.setIsprimaryKey(false);
                        }
                    });
                }

                res.forEach(e -> {
                    String sqlQueryComment = sqlBuilder.getSQLQueryComment(currentSchema, tableName, e.getColumnName());
                    //查询字段注释
                    try {
                        ResultSet resultSetComment = statement.executeQuery(sqlQueryComment);
                        while (resultSetComment.next()) {
                            e.setColumnComment(resultSetComment.getString(1));
                        }
                        JdbcUtils.close(resultSetComment);
                    } catch (SQLException e1) {
                        logger.error("[buildDasColumn executeQuery Exception] --> "
                                + "the exception message is:" + e1.getMessage());
                    }
                });
            }

            JdbcUtils.close(statement);
        } catch (SQLException e) {
            logger.error("[buildDasColumn Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    //获取指定表的主键，可能是多个，所以用list
    private List<String> getPrimaryKeys(String tableName) {
        List<String> res = Lists.newArrayList();
        String sqlQueryPrimaryKey = sqlBuilder.getSQLQueryPrimaryKey();
        try {
            List<Map<String, Object>> pkColumns = JdbcUtils.executeQuery(connection, sqlQueryPrimaryKey, ImmutableList.of(currentSchema, tableName));
            //返回主键名称即可
            pkColumns.forEach(e -> res.add((String) new ArrayList<>(e.values()).get(0)));
        } catch (SQLException e) {
            logger.error("[getPrimaryKeys Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return res;
    }

    @Override
    public List<String> getColumnNames(String tableName, String datasource) {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            //获取查询指定表所有字段的sql语句
            String querySql = sqlBuilder.getSQLQueryFields(tableName);
            logger.info("querySql: {}", querySql);

            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnName(i);
                if (JdbcConstants.HIVE.equals(datasource)) {
                    if (columnName.contains(Constants.SPLIT_POINT)) {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName.substring(columnName.indexOf(Constants.SPLIT_POINT) + 1) + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    } else {
                        res.add(i - 1 + Constants.SPLIT_SCOLON + columnName + Constants.SPLIT_SCOLON + metaData.getColumnTypeName(i));
                    }
                } else {
                    res.add(columnName);
                }

            }
        } catch (SQLException e) {
            logger.error("[getColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public Object getTableColumns(String tableName, String datasource,String databaseName) {

        Map res = new HashMap();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            String strsql="select database()";
            stmt = connection.createStatement();
            rs = stmt.executeQuery(strsql);
            JSON dataBaseNameJson= resultSetToJSON(rs);
            JSONArray jsonArray = JSONArray.parseArray(dataBaseNameJson.toJSONString());
            if(jsonArray.size()>0){
                JSONObject job = jsonArray.getJSONObject(0);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                databaseName=job.get("database()").toString();  // 得到 每个对象中的属性值
            }

            //获取查询指定表所有字段的sql语句
            String querySql = "Select COLUMN_NAME, DATA_TYPE,COLUMN_COMMENT from INFORMATION_SCHEMA.COLUMNS Where " +
                    "table_name ='" + tableName+"' and TABLE_SCHEMA='"+databaseName+"'";
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);
            JSON json= resultSetToJSON(rs);
            res.put("datas",json);
            res.put("success","true");
            res.put("error","");
        } catch (SQLException e) {
            logger.error("[getTableColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
            res.put("error","[getTableColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public List<String> getTableNames(String tableSchema) {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables(tableSchema);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                tables.add(tableName);
            }
            tables.sort(Comparator.naturalOrder());
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    @Override
    public List<String> getTableNames() {
        List<String> tables = new ArrayList<String>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTables();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                tables.add(tableName);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tables;
    }

    public Boolean dataSourceTest() {
        try {
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.getDatabaseProductName().length() > 0) {
                return true;
            }
        } catch (SQLException e) {
            logger.error("[dataSourceTest Exception] --> "
                    + "the exception message is:" + e.getMessage());
        }
        return false;
    }


    protected String getSQLQueryTables(String tableSchema) {
        return sqlBuilder.getSQLQueryTables(tableSchema);
    }

    /**
     * 不需要其他参数的可不重写
     *
     * @return
     */
    protected String getSQLQueryTables() {
        return sqlBuilder.getSQLQueryTables();
    }

    @Override
    public List<String> getColumnsByQuerySql(String querySql) throws SQLException {

        List<String> res = Lists.newArrayList();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            querySql = querySql.replace(";", "");
            //拼装sql语句，在后面加上 where 1=0 即可
            String sql = querySql.concat(" where 1=0");
            //判断是否已有where，如果是，则加 and 1=0
            //从最后一个 ) 开始找 where，或者整个语句找
            if (querySql.contains(")")) {
                if (querySql.substring(querySql.indexOf(")")).contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            } else {
                if (querySql.contains("where")) {
                    sql = querySql.concat(" and 1=0");
                }
            }
            //获取所有字段
            stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
            ResultSetMetaData metaData = rs.getMetaData();

            int columnCount = metaData.getColumnCount();
            for (int i = 1; i <= columnCount; i++) {
                res.add(metaData.getColumnName(i));
            }
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return res;
    }

    @Override
    public long getMaxIdVal(String tableName, String primaryKey) {
        Statement stmt = null;
        ResultSet rs = null;
        long maxVal = 0;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLMaxID(tableName, primaryKey);
            rs = stmt.executeQuery(sql);
            rs.next();
            maxVal = rs.getLong(1);
        } catch (SQLException e) {
            logger.error("[getMaxIdVal Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }


        return maxVal;
    }

    private String getSQLMaxID(String tableName, String primaryKey) {
        return sqlBuilder.getMaxId(tableName, primaryKey);
    }

    public void executeCreateTableSql(String querySql) {
        if (StringUtils.isBlank(querySql)) {
            return;
        }
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(querySql);
        } catch (SQLException e) {
            logger.error("[executeCreateTableSql Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(stmt);
        }
    }

    public List<String> getTableSchema() {
        List<String> schemas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = getSQLQueryTableSchema();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tableName = rs.getString(1);
                schemas.add(tableName);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return schemas;
    }

    protected String getSQLQueryTableSchema() {
        return sqlBuilder.getSQLQueryTableSchema();
    }

    public Map querySucessExamples(String out,String tableName){
        Map<String, Object> map = new HashMap<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String sql = "select "+out+" from " + tableName+" limit 1";
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            JSON json=resultSetToJSON(rs);
            logger.info(json.toJSONString());
            map.put("code", "00");
            map.put("msg", "成功");
            map.put("datas", json);
            map.put("dataCount", 1);
            ConnectUtil.CloseConn(connection, ps, rs);//关闭连接
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            ConnectUtil.CloseConn(connection, ps, rs);//关闭连接
        }
        return map;
    }

    public Long getRows(String tableName) {
        Long rows=0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getRows(tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                rows = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return rows;
    }

    public Map<String,Object> listAll(List<String> columns, String tableName,Integer pageNumber,Integer pageSize) {
        List<List<Map<String,Object>>> datas = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getListAll(tableName, pageNumber, pageSize,columns.get(0));
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                List<Map<String,Object>> colList=new ArrayList<>();
                for (int i=1;i<=columns.size();i++) {
                    Map<String,Object> map=new HashMap<>();
                    Object value = rs.getObject(i);
                    map.put(columns.get(i-1),value);
                    colList.add(map);
                }
                datas.add(colList);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        Map<String,Object> ret=new HashMap(){{
                this.put("datas",datas);
                this.put("total",getRows(tableName));
        }};
        return ret;
    }

    public String getDBName(){
        String dbName=null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getDBName();
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                dbName = rs.getString(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return dbName;
    }

    public List<ColumnMsg> getColumnSchema(String tableName, String tableSchema){
        List<ColumnMsg> columnMsgs = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getColumnSchema(tableName,tableSchema);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                ColumnMsg columnMsg=new ColumnMsg();
                String name = rs.getString(1);
                String comment=rs.getString(2);
                String type=rs.getString(3);
                columnMsgs.add(columnMsg);
                columnMsg.setName(name);
                columnMsg.setComment(comment);
                String showType=this.judgeShowType(type);
                columnMsg.setIndicator(this.getIndicator(name,tableName,showType));
                logger.info("*************************showType: "+showType);
                if("dateType".equals(showType)){
                    columnMsg.setType("date");
                    columnMsg.setStatistics(this.getDateChart(name,tableName));
                }else if("numberType".equals(showType)){
                    columnMsg.setType("number");
                    columnMsg.setStatistics(this.getChart(name,tableName));
                }else {
                    columnMsg.setType("string");
                    columnMsg.setStatistics(this.getUnique(name,tableName));
                }

            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return columnMsgs;
    }

    public Map<String,Map<String,Object>> getIndicator(String fieldName,String tableName,String showType){
        Long missing=0L;
        Long num=0L;
        Object fieldVal=null;
        Object maximum=null;
        Object minimum=null;
        Map<String,Map<String,Object>> map=new HashMap<>();
        Map<String,Object> validMap=new HashMap<>();
        Map<String,Object> missingMap=new HashMap<>();
        Map<String,Object> uniqueMap=new HashMap<>();
        Map<String,Object> mostCommonMap=new HashMap<>();
        Map<String,Object> maximumMap=new HashMap<>();
        Map<String,Object> minimumMap=new HashMap<>();
        Statement stmt = null;
        ResultSet rs = null;
        Long total=getRows(tableName);
        try {
            stmt = connection.createStatement();
            //获取valid数量
            String sql = sqlBuilder.getMissing(fieldName,tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                missing = rs.getLong(1);
                missingMap.put("value",missing);
                missingMap.put("rate",getRate(missing,total));
                validMap.put("value",total-missing);
                validMap.put("rate",getRate(total-missing,total));
            }
            sql=sqlBuilder.getMostCommon(fieldName,tableName);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                num = rs.getLong(1);
                fieldVal=rs.getObject(2);
                mostCommonMap.put("value",fieldVal);
                mostCommonMap.put("rate",getRate(num,total));
            }
            Long unique=getUnique(fieldName,tableName);
            uniqueMap.put("value",unique);
            map.put("valid",validMap);
            map.put("misssing",missingMap);
            map.put("unique",uniqueMap);
            map.put("mostCommon",mostCommonMap);
            if("numberType".equals(showType)){
                sql=sqlBuilder.getMaxMin(fieldName,tableName);
                rs = stmt.executeQuery(sql);
                if(rs.next()){
                    maximum = rs.getObject(1);
                    minimum=rs.getObject(2);
                    maximumMap.put("value",maximum);
                    minimumMap.put("value",minimum);
                }
                map.put("maximum",maximumMap);
                map.put("minimum",minimumMap);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return map;
    }

    private String getRate(Long a,Long b){
        if(b==0){
            logger.info("表记录数为0，除数不能为0");
            throw  new RuntimeException("表记录数为0，除数不能为0");
        }
        if (a==0){
            return "0%";
        }
        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        String result = numberFormat.format((float)a/(float)b*100);
        return result + "%";
    }

    private String getDateChart(String name,String tableName) {
        Date dt=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getDateStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                Date temp=rs.getTimestamp(1);
                if(temp!=null){
                    dt=temp;
                }

            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        logger.info("date-------->"+sdf.format(dt));
        return sdf.format(dt);
        //return dt.toString();
    }

    /*private List<Chart<Date>> getDateChart(String name,String tableName) {
        List<Chart<Date>> charts = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getDateStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Chart<Date> chart=new Chart();
                Date min = rs.getDate(1);
                Date max=rs.getDate(2);
                Long number=rs.getLong(3);
                charts.add(chart);
                chart.setMin(min);
                chart.setMax(max);
                chart.setNumber(number);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return charts;
    }*/

    private Long getUnique(String name,String tableName) {
        Long ret = 0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getStringStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                ret = rs.getLong(1);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return ret;
    }

    private List<Chart<Float>> getChart(String name,String tableName) {
        List<Chart<Float>> charts = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getNumberStatistics(name,tableName);
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Chart<Float> chart=new Chart();
                Float min = rs.getFloat(1);
                Float max=rs.getFloat(2);
                Long number=rs.getLong(3);
                charts.add(chart);
                chart.setMin(min);
                chart.setMax(max);
                chart.setNumber(number);
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return charts;
    }

    private String judgeShowType(String type) {
        type=type.toUpperCase();
        //加入oracle特有的字段类型：VARCHAR2，NUMBER
        String[] stringType={"CHAR","VARCHAR","TINYBLOB","TINYTEXT","BLOB","TEXT","MEDIUMBLOB","MEDIUMTEXT","LONGBLOB","LONGTEXT","VARCHAR2"};
        String[] dateType={"DATE","TIME","YEAR","DATETIME","TIMESTAMP"};
        String[] numberType={"TINYINT","SMALLINT","MEDIUMINT","INT","INTEGER","BIGINT","FLOAT","DOUBLE","DECIMAL","NUMBER"};
        if (Arrays.asList(stringType).contains(type.toUpperCase())){
            return "stringType";
        }else if(Arrays.asList(dateType).contains(type.toUpperCase())){
            return "dateType";
        }else if(Arrays.asList(numberType).contains(type.toUpperCase())){
            return "numberType";
        }
        return "stringType";
    }

    public String getTableSize(String tableName, String tableSchema){
        Long tableSize =0L;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getTableSize(tableName,tableSchema);
            rs = stmt.executeQuery(sql);
            if(rs.next()){
                if(sqlBuilder.getClass()==SqlServerDatabaseMeta.class){
                    return  rs.getString(4);
                }else if(sqlBuilder.getClass()== PostgresqlDatabaseMeta.class){
                    return  rs.getString(1);
                }
                tableSize=rs.getLong(1)/1024;
            }
        } catch (SQLException e) {
            logger.error("[getTableNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return tableSize+"KB";
    }
}
