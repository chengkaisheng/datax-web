package com.wugui.datax.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.mapper.TDatabaseInfoMapper;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Search;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.meta.ImpalaDatabaseMeta;
import com.wugui.datax.admin.tool.query.*;
import com.wugui.datax.admin.util.JdbcConstants;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.wugui.datax.admin.entity.ColumnMsg;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;
import java.util.*;

/**
 * datasource query
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName JdbcDatasourceQueryServiceImpl
 * @Version 1.0
 * @since 2019/7/31 20:51
 */
@Service
public class DatasourceQueryServiceImpl implements DatasourceQueryService {

    @Autowired
    private JobDatasourceService jobDatasourceService;

    @Override
    public List<String> getDBs(Long id) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        return new MongoDBQueryTool(datasource).getDBNames();
    }


    @Override
    public List<String> getTables(Long id, String tableSchema) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return new HBaseQueryTool(datasource).getTableNames();
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(datasource).getCollectionNames(datasource.getDatabaseName());
        }
        else {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            if(StringUtils.isBlank(tableSchema)){
                return qTool.getTableNames();
            }else{
                return qTool.getTableNames(tableSchema);
            }
        }
    }

    @Override
    public List<String> getTableSchema(Long id) {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
        return qTool.getTableSchema();
    }

    @Override
    public List<String> getCollectionNames(long id, String dbName) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        return new MongoDBQueryTool(datasource).getCollectionNames(dbName);
    }


    @Override
    public List<String> getColumns(Long id, String tableName) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return new HBaseQueryTool(datasource).getColumns(tableName);
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(datasource).getColumns(tableName);
        }
        else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getColumnNames(tableName, datasource.getDatasource());
        }
    }

    @Override
    public Object getTableColumns(Long id, String schema, String tableName) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }
        if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            return new HBaseQueryTool(datasource).getColumns(tableName);
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            return new MongoDBQueryTool(datasource).getColumns(tableName);
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getTableColumns(tableName, datasource.getDatasource(),schema);
        }
    }

    @Override
    public List<String> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        //获取数据源对象
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        //queryTool组装
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        return queryTool.getColumnsByQuerySql(querySql);
    }

    //获取表的记录数
    public Long getRows(Long datasourceId,String tableName) throws Exception {
        String columnName=getColumns(datasourceId,tableName).get(0);
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return 0L;
        }
        if (JdbcConstants.MONGODB.equals(jdbcDatasource.getDatasource())) {
            return new MongoDBQueryTool(jdbcDatasource).getRows(tableName);
        }else if(JdbcConstants.DB2.equals(jdbcDatasource.getDatasource())){
            return new DB2QueryTool(jdbcDatasource).getRows(tableName,columnName);
        }else if(JdbcConstants.HIVE.equals(jdbcDatasource.getDatasource())){
            columnName=columnName.substring(columnName.indexOf(':')+1,columnName.lastIndexOf(':'));
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        return queryTool.getRows(tableName,columnName);
    }

    //获取表的所有数据
    public Map<String,Object> listAll(Long datasourceId,String tableName,Integer pageNumber,Integer pageSize) throws IOException {
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        List<String> columns = this.getColumns(datasourceId, tableName);
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return Maps.newHashMap();
        }
        if (JdbcConstants.MONGODB.equals(jdbcDatasource.getDatasource())) {
            return new MongoDBQueryTool(jdbcDatasource).listAll(columns,tableName);
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        Map<String,Object> maps = queryTool.listAll(columns,tableName,pageNumber,pageSize);
        return maps;
    }

    //获取表所有字段的统计值
    public List<ColumnMsg> getColumnSchema(Long datasourceId, String tableName) throws IOException {
        String tableSchema="";
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return Lists.newArrayList();
        }
        if (JdbcConstants.MONGODB.equals(jdbcDatasource.getDatasource())) {
            return new MongoDBQueryTool(jdbcDatasource).getColumnSchema(getColumns(datasourceId, tableName),tableName);
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        if(queryTool.getClass()==MySQLQueryTool.class){
            tableSchema=queryTool.getDBName();
        }
        return queryTool.getColumnSchema(tableName,tableSchema);
    }

    //根据数据源id和表名获取表数据大小
    public String getTableSize(Long datasourceId, String tableName) throws Exception {
        JobDatasource jdbcDatasource = jobDatasourceService.getById(datasourceId);
        if (ObjectUtil.isNull(jdbcDatasource)) {
            return null;
        }if (JdbcConstants.MONGODB.equals(jdbcDatasource.getDatasource())) {
            return new MongoDBQueryTool(jdbcDatasource).getTableSize(tableName);
        }
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(jdbcDatasource);
        String tableSchema="";
        if(queryTool.getClass()==MySQLQueryTool.class){
            tableSchema=queryTool.getDBName();
        }else if(queryTool.getClass()==DB2QueryTool.class){
            tableSchema=queryTool.getDBSchema();
        }
        return queryTool.getTableSize(tableName,tableSchema);
    }

    @Override
    public List<TableInfo> getTableInfos(Long id, String schema) throws IOException, SQLException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(id);
        List<String> tableNames = new ArrayList<>();
        List<Map<String,Object>> tableInfoMap = new ArrayList<>();
        List<TableInfo> tableInfos = new ArrayList<>();
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }if (JdbcConstants.HBASE.equals(datasource.getDatasource())) {
            tableNames =  new HBaseQueryTool(datasource).getTableNames();
            tableNames.forEach((tableName)->{
                tableInfos.add(new TableInfo(tableName));
            });
        } else if (JdbcConstants.MONGODB.equals(datasource.getDatasource())) {
            tableNames =  new MongoDBQueryTool(datasource).getCollectionNames(datasource.getDatabaseName());
            tableNames.forEach((tableName)->{
                tableInfos.add(new TableInfo(tableName));
            });
        }
        else if (JdbcConstants.HIVE.equals(datasource.getDatasource())) {
            if(StringUtils.isEmpty(schema)) {
                tableNames = new HiveQueryTool(datasource).getTableNames();
            }
            else {
                tableNames = new HiveQueryTool(datasource).getTableNames(schema);
            }
            tableNames.forEach((tableName)->{
                tableInfos.add(new TableInfo(tableName));
            });
        }
        else if (JdbcConstants.IMPALA.equals(datasource.getDatasource())) {
            if(StringUtils.isEmpty(schema)) {
                tableNames = new ImpalaQueryTool(datasource).getTableNames();
            }
            else {
                tableNames = new ImpalaQueryTool(datasource).getTableNames(schema);
            }
            tableNames.forEach((tableName)->{
                tableInfos.add(new TableInfo(tableName));
            });
        }else {
            BaseQueryTool qTool = QueryToolFactory.getByDbType(datasource);
            if(StringUtils.isBlank(schema)){
                tableInfoMap = qTool.getTablesInfo();
            }else{
                tableInfoMap = qTool.getTablesInfo(schema);
            }
            for (Map<String, Object> map : tableInfoMap) {
                TableInfo tableInfoTemp = new TableInfo();
                for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
                    if("table_name".equals(stringObjectEntry.getKey())){
                        tableInfoTemp.setName((String) stringObjectEntry.getValue());
                    }else if("table_comment".equals(stringObjectEntry.getKey())){
                        tableInfoTemp.setComment(stringObjectEntry.getValue()==null? "": (String) stringObjectEntry.getValue());
                    }else if("data_length".equals(stringObjectEntry.getKey())){
                        int i = Integer.parseInt(String.valueOf(stringObjectEntry.getValue()));
                        tableInfoTemp.setDataLength(i/1024 + "K");
                    }
                }
                tableInfos.add(tableInfoTemp);
            }
        }
        return tableInfos;
    }

    @Override
    public Map<String, String> getHivePathDefault(Long datasourceId, String schema, String tableName) {
        JobDatasource datasource = jobDatasourceService.getById(datasourceId);
        BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
        return queryTool.getHivePathDefault(schema, tableName);
    }
}
