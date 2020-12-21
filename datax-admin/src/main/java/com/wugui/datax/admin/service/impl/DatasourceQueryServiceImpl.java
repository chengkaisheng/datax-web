package com.wugui.datax.admin.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.mapper.TDatabaseInfoMapper;
import com.wugui.datax.admin.entity.*;
import com.wugui.datax.admin.mapper.DimDDLMapper;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.database.TableInfo;
import com.wugui.datax.admin.tool.meta.ImpalaDatabaseMeta;
import com.wugui.datax.admin.tool.query.*;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.UUIDUtils;
import io.swagger.models.auth.In;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    @Resource
    private DimDDLMapper dimDDLMapper;

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
        }else if (JdbcConstants.DB2.equals(datasource.getDatasource())) {
            try {
                return new DB2QueryTool(datasource).getTableColumns(tableName,datasource.getDatasource(),schema);
            } catch (SQLException e) {
                e.printStackTrace();
                Map<String,String> map=new HashMap<>();
                map.put("data",e.getMessage());
                map.put("success","数据提取失败");
                map.put("code","500");
                return map;
            }
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getTableColumns(tableName, datasource.getDatasource(),schema);
        }
    }

    @Override
    public Object dbToHive(HiveParameter hiveParameter) throws IOException {
        //获取数据源对象
        JobDatasource datasource = jobDatasourceService.getById(hiveParameter.getDatasourceId());
        //queryTool组装
        if (ObjectUtil.isNull(datasource)) {
            return Lists.newArrayList();
        }

        if (JdbcConstants.DB2.equals(datasource.getDatasource())) {
            List<TableInfo> tableInfos = null;
            List<String> hiveSql=new ArrayList<>();
            try {
                tableInfos = getTableInfos(hiveParameter.getDatasourceId(), hiveParameter.getSchema());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(tableInfos.size()>0){
                Set<TableInfo> tableInfoSet = new HashSet<>(tableInfos);
                tableInfos.clear();
                tableInfos.addAll(tableInfoSet);
                for (TableInfo tableInfo:tableInfos){
                    hiveParameter.setTableName(tableInfo.getName());
                    String sql=this.getDB2Sql(datasource,hiveParameter).toString();
                    hiveSql.add(sql);
                }
            }
            return hiveSql;
        } else {
            BaseQueryTool queryTool = QueryToolFactory.getByDbType(datasource);
            return queryTool.getTableColumns(hiveParameter.getTableName(), datasource.getDatasource(),hiveParameter.getSchema());
        }
    }

    public Object getDB2Sql(JobDatasource datasource,HiveParameter hiveParameter){
        try {
            Map<String, List<Map<String,String>>> wkResult=null;//主键，外键，唯一键表信息存放位置
            List<Map<String,String>> wkList=null;
            List<Map<String,String>> randomDate=new ArrayList<>();//表时间字段存放，作用于随机取值
            List<Map<String,String>> columRandom=new ArrayList<>();//表除时间字段存放，作用于随机取值
            StringBuilder str=new StringBuilder();//主sql拼接
            StringBuilder sorted=new StringBuilder();//桶内排序SQL拼接
            StringBuilder patition=new StringBuilder();//分区SQL拼接
            boolean isSorted=false;//判断是否进行排序
            boolean isPatition=false;//判断是否进行分区
            Random random = new Random();
            patition.append("PARTITIONED BY (");
            str.append("CREATE ");
            if(hiveParameter.isTemporary()){
                str.append("TEMPORARY ");
            }
            if(hiveParameter.isExternal()){
                str.append("EXTERNAL ");
            }
            str.append(" TABLE IF NOT EXISTS ");
            DB2QueryTool db2QueryTool=new DB2QueryTool(datasource);
            Map<String, List<Map<String,String>>> result=(Map<String, List<Map<String,String>>>) db2QueryTool.getTableColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
            List<Map<String,String>> list=result.get("datas");
            sorted.append("(");
            if(UUIDUtils.notEmpty(hiveParameter.getDbNameType())){//获取数据库名
                str.append(hiveParameter.getDbNamePattern().replace("%s",list.get(0).get("TABSCHEMA"))).append(".");
            }
            if(UUIDUtils.notEmpty(hiveParameter.getTableNameType())){//获取表名
                str.append(hiveParameter.getTableNamePattern().replace("%s",list.get(0).get("TABLENAME")));
            }
            str.append("(").append("\r");
            for (Map<String,String> map:list){
                if(map.get("DATA_TYPE").equals("DATE")){//获取所有为DATE类型的字段
                    Map map1=new HashMap();
                    map1.put("COLUMN_NAME", map.get("COLUMN_NAME"));
                    map1.put("DATA_TYPE", map.get("DATA_TYPE"));
                    randomDate.add(map1);
                }else {//获取所有非时间字段
                    Map map1=new HashMap();
                    map1.put("COLUMN_NAME", map.get("COLUMN_NAME"));
                    map1.put("DATA_TYPE", map.get("DATA_TYPE"));
                    columRandom.add(map1);
                }
            }
            //获取随机数
            int n = (int)(Math.random()*columRandom.size());
            int p = (int)(Math.random()*randomDate.size());
            if("random".equals(hiveParameter.getPartitionKey())){//分区为random
                patition.append(columRandom.get(n).get("COLUMN_NAME")).append(" ");
                DimDDL dimDDL= dimDDLMapper.select("db2","hive",columRandom.get(n).get("DATA_TYPE").trim());
                patition.append(dimDDL.getTargetType());
                isPatition=true;
            }
            if("randomDate".equals(hiveParameter.getPartitionKey())&&randomDate.size()>0){//分区为randomDate
                patition.append(randomDate.get(p).get("COLUMN_NAME")).append(" ");
                DimDDL dimDDL= dimDDLMapper.select("db2","hive",randomDate.get(p).get("DATA_TYPE").trim());
                patition.append(dimDDL.getTargetType());
                isPatition=true;
            }
            for (Map<String,String> map:list){
                //分区为随机时，当前列名不能与分区的列名相同
                if("random".equals(hiveParameter.getPartitionKey())&& !map.get("COLUMN_NAME").equals(columRandom.get(n).get("COLUMN_NAME"))) {
                    str.append(map.get("COLUMN_NAME")).append(" ");
                    DimDDL dimDDL= dimDDLMapper.select("db2","hive",map.get("DATA_TYPE").trim());
                    if(dimDDL.getTargetType().equals("CHAR")){
                        str.append(dimDDL.getTargetType()).append("(255)");
                    }else if(dimDDL.getTargetType().equals("VARCHAR")) {
                        str.append(dimDDL.getTargetType()).append("(65530)");
                    }else {
                        str.append(dimDDL.getTargetType());
                    }

                    if(hiveParameter.isComment()){
                        if(UUIDUtils.notEmpty(map.get("COLUMN_COMMENT"))){
                            str.append(" comment '"+map.get("COLUMN_COMMENT")+"'");
                        }

                    }
                    str.append(",\r");
                }
                //当分区为时间字段且当前表存在时间字段，即randomDate>0时,当前列名不能与随机的时间字段相同
                if("randomDate".equals(hiveParameter.getPartitionKey())&& randomDate.size()>0&&!map.get("COLUMN_NAME").equals(randomDate.get(p).get("COLUMN_NAME"))){
                    str.append(map.get("COLUMN_NAME")).append(" ");
                    DimDDL dimDDL= dimDDLMapper.select("db2","hive",map.get("DATA_TYPE").trim());
                    if(dimDDL.getTargetType().equals("CHAR")){
                        str.append(dimDDL.getTargetType()).append("(255)");
                    }else if(dimDDL.getTargetType().equals("VARCHAR")) {
                        str.append(dimDDL.getTargetType()).append("(65530)");
                    }else {
                        str.append(dimDDL.getTargetType());
                    }

                    if(hiveParameter.isComment()){
                        if(UUIDUtils.notEmpty(map.get("COLUMN_COMMENT"))){
                            str.append(" comment '"+map.get("COLUMN_COMMENT")+"'");
                        }

                    }
                    str.append(",\r");
                }
                if("randomDate".equals(hiveParameter.getPartitionKey())&& randomDate.size()<=0) {
                    //当分区为时间字段且当前表不存在时间字段，即randomDate<0
                    str.append(map.get("COLUMN_NAME")).append(" ");
                    DimDDL dimDDL= dimDDLMapper.select("db2","hive",map.get("DATA_TYPE").trim());
                    if(dimDDL.getTargetType().equals("CHAR")){
                        str.append(dimDDL.getTargetType()).append("(255)");
                    }else if(dimDDL.getTargetType().equals("VARCHAR")) {
                        str.append(dimDDL.getTargetType()).append("(65530)");
                    }else {
                        str.append(dimDDL.getTargetType());
                    }

                    if(hiveParameter.isComment()){
                        if(UUIDUtils.notEmpty(map.get("COLUMN_COMMENT"))){
                            str.append(" comment '"+map.get("COLUMN_COMMENT")+"'");
                        }

                    }
                    str.append(",\r");
                }
                //不进行分区操作
                if("none".equals(hiveParameter.getPartitionKey())){
                    str.append(map.get("COLUMN_NAME")).append(" ");
                    DimDDL dimDDL= dimDDLMapper.select("db2","hive",map.get("DATA_TYPE").trim());
                    if(dimDDL.getTargetType().equals("CHAR")){
                        str.append(dimDDL.getTargetType()).append("(255)");
                    }else if(dimDDL.getTargetType().equals("VARCHAR")) {
                        str.append(dimDDL.getTargetType()).append("(65530)");
                    }else {
                        str.append(dimDDL.getTargetType());
                    }

                    if(hiveParameter.isComment()){
                        if(UUIDUtils.notEmpty(map.get("COLUMN_COMMENT"))){
                            str.append(" comment '"+map.get("COLUMN_COMMENT")+"'");
                        }

                    }
                    str.append(",\r");
                }
                //桶内排序为时间字段
                if(map.get("DATA_TYPE").equals("DATE")&&"date".equals(hiveParameter.getBucketSortKey())&&!isSorted){
                    //桶内排序为date，分区为randomDate时，排序字段不能与分区字段相同
                    if("randomDate".equals(hiveParameter.getPartitionKey())&&randomDate.size()>0&&!map.get("COLUMN_NAME").equals(randomDate.get(p).get("COLUMN_NAME"))){
                        if(UUIDUtils.notEmpty(hiveParameter.getBucketSortOrder())){
                            sorted.append(map.get("COLUMN_NAME")).append(" ").append(hiveParameter.getBucketSortOrder());
                        }else {
                            sorted.append(map.get("COLUMN_NAME"));
                        }
                        sorted.append(",");
                        isSorted=true;
                    }
                    //桶内排序为date，分区为random时，排序字段不能与分区字段相同
                    if("random".equals(hiveParameter.getPartitionKey())&&randomDate.size()>0&&!map.get("COLUMN_NAME").equals(columRandom.get(n).get("COLUMN_NAME"))){
                        if(UUIDUtils.notEmpty(hiveParameter.getBucketSortOrder())){
                            sorted.append(map.get("COLUMN_NAME")).append(" ").append(hiveParameter.getBucketSortOrder());
                        }else {
                            sorted.append(map.get("COLUMN_NAME"));
                        }
                        sorted.append(",");
                        isSorted=true;
                    }
                }
                //桶内容排序为random
                if("random".equals(hiveParameter.getBucketSortKey())&&!isSorted){
                    //桶内排序为random，分区为random时，排序字段不能与分区字段相同
                    if("random".equals(hiveParameter.getPartitionKey())&&!map.get("COLUMN_NAME").equals(columRandom.get(n).get("COLUMN_NAME"))){
                        if(UUIDUtils.notEmpty(hiveParameter.getBucketSortOrder())){
                            sorted.append(map.get("COLUMN_NAME")).append(" ").append(hiveParameter.getBucketSortOrder());
                        }else {
                            sorted.append(map.get("COLUMN_NAME"));
                        }
                        sorted.append(",");
                        isSorted=true;
                    }
                    //桶内排序为random，分区为randomDate且存在时间字段时，排序字段不能与分区字段相同
                    if("randomDate".equals(hiveParameter.getPartitionKey())&&randomDate.size()>0&&!map.get("COLUMN_NAME").equals(randomDate.get(p).get("COLUMN_NAME"))){
                        if(UUIDUtils.notEmpty(hiveParameter.getBucketSortOrder())){
                            sorted.append(map.get("COLUMN_NAME")).append(" ").append(hiveParameter.getBucketSortOrder());
                        }else {
                            sorted.append(map.get("COLUMN_NAME"));
                        }
                        sorted.append(",");
                        isSorted=true;
                    }

                }
            }
            if(isSorted){//是否拼接桶内排序，是的话将最后一个逗号去除
                sorted=sorted.deleteCharAt(sorted.length()-1);
            }
            patition.append(")\r");
            sorted.append(") ");
            //查询db2主键信息并生成主键sql
            if(hiveParameter.isPk()&& UUIDUtils.StringToInteger(hiveParameter.getVersion())>=210){
                wkResult=(Map<String, List<Map<String,String>>>) db2QueryTool.getPKColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
                if(wkResult.get("datas").size()>0){
                    wkList=wkResult.get("datas");
                    str.append(" PRIMARY KEY (");
                    for (Map<String,String> map:wkList){
                        str.append(map.get("COLUMN_NAME")).append(",");
                    }
                    str=str.deleteCharAt(str.length()-1);
                    str.append(") ");
                    str.append(" DISABLE NOVALIDATE RELY,");
                }
            }
            //查询db2外键信息并生成外键sql
            if(hiveParameter.isFk()&&  UUIDUtils.StringToInteger(hiveParameter.getVersion())>=210){
                wkResult=(Map<String, List<Map<String,String>>>) db2QueryTool.getFKColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
                if(wkResult.get("datas").size()>0){
                    wkList=wkResult.get("datas");
                    for (Map<String,String> map:wkList){
                        str.append(" CONSTRAINT ");
                        str.append(map.get("TABLENAME")).append(" foreign key (");
                        str.append(map.get("FK_COLUMN_NAME")).append(") ").append("references ");
                        str.append(map.get("reftabname")).append("(").append(map.get("pk_colnames")).append(")");
                        str.append(" disable novalidate,\r");
                    }
                }
            }
            //查询db2唯一键信息并生成唯一键sql
            if(hiveParameter.isUk()&& UUIDUtils.StringToInteger(hiveParameter.getVersion())>=300){
                wkResult=(Map<String, List<Map<String,String>>>) db2QueryTool.getUKColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
                if(wkResult.get("datas").size()>0){
                    wkList=wkResult.get("datas");
                    str.append("CONSTRAINT ").append(wkList.get(0).get("CONSTNAME")).append(" UNIQUE (");
                    for (Map<String,String> map:wkList){
                        str.append(map.get("COLUMN_NAME")).append(",");
                    }
                    str=str.deleteCharAt(str.length()-1);
                    str.append(") ");
                    str.append(" DISABLE NOVALIDATE RELY,");
                }
            }
            //查询db2检查约束信息并生成检查约束sql
            if(hiveParameter.isCk()&& UUIDUtils.StringToInteger(hiveParameter.getVersion())>=300){
                wkResult=(Map<String, List<Map<String,String>>>) db2QueryTool.getCKColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
                if(wkResult.get("datas").size()>0){
                    wkList=wkResult.get("datas");
                    for (Map<String,String> map:wkList){
                        str.append("CONSTRAINT ").append(map.get("CONSTNAME")).append(" CHECK (").append(map.get("TEXT")).append("),\r");
                    }
                }
            }
            str=str.deleteCharAt(str.length()-1);
            str=str.deleteCharAt(str.length()-1);
            str.append("\r)").append("\r");
            if(hiveParameter.isTableComment()){
                if(UUIDUtils.notEmpty(list.get(0).get("TABLE_COMMENT"))){
                    str.append("comment ").append("'").append(list.get(0).get("TABLE_COMMENT")).append("'").append("\r");
                }

            }
            //设置分区，临时表不能进行分区
            if(isPatition&&!hiveParameter.isTemporary()){
                str.append(patition.toString());
            }
            //查询db2主键信息并生成hive分桶语句,分桶字段为主键
            if("primaryKey".equals(hiveParameter.getBucketKey())){
                wkResult=(Map<String, List<Map<String,String>>>) db2QueryTool.getPKColumns(hiveParameter.getTableName(),datasource.getDatasource(),hiveParameter.getSchema());
                if(wkResult.get("datas").size()>0){
                    wkList=wkResult.get("datas");
                    str.append(" CLUSTERED BY (");
                    for (Map<String,String> map:wkList){
                        str.append(map.get("COLUMN_NAME")).append(",");
                    }
                    str=str.deleteCharAt(str.length()-1);
                    str.append(") ");
                    //桶内排序--默认时间字段 desc
                    if(isSorted){
                        str.append(" SORTED BY ").append(sorted.toString());
                    }
                    str.append("INTO ");
                    if(hiveParameter.getBucketNum()>0){
                        str.append(hiveParameter.getBucketNum()).append(" BUCKETS ");
                    }else {
                        str.append("5 BUCKETS ");
                    }
                    str.append("\r");
                }
            }
            //分桶为随机
            if("random".equals(hiveParameter.getBucketKey())){
                int num = random.nextInt(columRandom.size());
                str.append(" CLUSTERED BY (");
                str.append(columRandom.get(num).get("COLUMN_NAME"));
                str.append(") ");
                //桶内排序--默认时间字段 desc
                if(isSorted){
                    str.append(" SORTED BY ").append(sorted.toString());
                }
                str.append("INTO ");
                if(hiveParameter.getBucketNum()>0){
                    str.append(hiveParameter.getBucketNum()).append(" BUCKETS ");
                }else {
                    str.append("5 BUCKETS ");
                }
                str.append("\r");
            }
            //设置数据分隔符，ROW FORMAT为DELIMITED
            if("DELIMITED".equals(hiveParameter.getRowformat())){
                str.append("ROW FORMAT ").append(hiveParameter.getRowformat());
                if(UUIDUtils.notEmpty(hiveParameter.getFieldTerm())){
                    str.append(" FIELDS TERMINATED BY '").append(hiveParameter.getFieldTerm()).append("'\r");
                }
                if(UUIDUtils.notEmpty(hiveParameter.getRowformatCollectTerm())){
                    str.append(" COLLECTION ITEMS TERMINATED BY '").append(hiveParameter.getRowformatCollectTerm()).append("'\r");
                }
                if(UUIDUtils.notEmpty(hiveParameter.getRowformatMapKeyTerm())){
                    str.append(" MAP KEYS TERMINATED BY '").append(hiveParameter.getRowformatMapKeyTerm()).append("'\r");
                }
                if(UUIDUtils.notEmpty(hiveParameter.getRowformatLineTerm())){
                    str.append(" lines terminated by '").append(hiveParameter.getRowformatLineTerm()).append("'\r");
                }
                if(UUIDUtils.notEmpty(hiveParameter.getRowformatNullDefinndAs())){
                    str.append(" NULL DEFINED AS '").append(hiveParameter.getRowformatNullDefinndAs()).append("'\r");
                }
            }
            //ROW FORMAT为SERDE，设置类名
            if("SERDE".equals(hiveParameter.getRowformat())){
                str.append("ROW FORMAT ").append(hiveParameter.getRowformat()).append(" '").append(hiveParameter.getRowformatSerdeName()).append("'\r");
            }
            //设置文件格式
            if(UUIDUtils.notEmpty(hiveParameter.getStoredAs())){
                str.append("STORED AS ").append(hiveParameter.getStoredAs()).append("\r");
            }
            //设置hdfs文件位置
            if(UUIDUtils.notEmpty(hiveParameter.getLocation())){
                str.append("LOCATION '").append(hiveParameter.getLocation()).append("'\r");
            }
            str.append("\r");
            return str.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return e.getMessage();
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
        }
        else if (JdbcConstants.DB2.equals(datasource.getDatasource())) {
            schema=schema.replace("+","");
            if(StringUtils.isEmpty(schema)) {
                tableNames = new DB2QueryTool(datasource).getTableNames();
            }
            else {
                tableNames = new DB2QueryTool(datasource).getTableNames(schema);
            }
            tableNames.forEach((tableName)->{
                tableInfos.add(new TableInfo(tableName));
            });
        }
        else {
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
