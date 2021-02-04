package com.wugui.datax.admin.tool.datax;


import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wugui.datatx.core.util.Constants;
import com.wugui.datax.admin.dto.*;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.tool.datax.reader.*;
import com.wugui.datax.admin.tool.datax.writer.*;
import com.wugui.datax.admin.tool.pojo.DataxHbasePojo;
import com.wugui.datax.admin.tool.pojo.DataxHivePojo;
import com.wugui.datax.admin.tool.pojo.DataxMongoDBPojo;
import com.wugui.datax.admin.tool.pojo.DataxRdbmsPojo;
import com.wugui.datax.admin.tool.query.HiveQueryTool;
import com.wugui.datax.admin.tool.query.ImpalaQueryTool;
import com.wugui.datax.admin.util.DateFormatUtils;
import com.wugui.datax.admin.util.JdbcConstants;
import com.wugui.datax.admin.util.UUIDUtils;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.*;

import static com.wugui.datax.admin.util.JdbcConstants.*;

/**
 * 构建 com.wugui.datax json的工具类
 *
 * @author jingwk
 * @ClassName DataxJsonHelper
 * @Version 2.1.1
 * @since 2020/03/14 08:24
 */
@Data
public class DataxJsonHelper implements DataxJsonInterface {

    /**
     * 读取的表，根据datax示例，支持多个表（先不考虑，后面再去实现， 这里先用list保存吧）
     * <p>
     * 目的表的表名称。支持写入一个或者多个表。当配置为多张表时，必须确保所有表结构保持一致
     */
    private List<String> readerTables;
    /**
     * 读取的字段列表
     */
    private List<String> readerColumns;
    /**
     * reader jdbc 数据源
     */
    private JobDatasource readerDatasource;
    /**
     * writer jdbc 数据源
     */
    private JobDatasource writerDatasource;
    /**
     * 写入的表
     */
    private List<String> writerTables;
    /**
     * 写入的字段列表
     */
    private List<String> writerColumns;

    private Map<String, Object> buildReader;

    private Map<String, Object> buildWriter;

    private BaseDataxPlugin readerPlugin;

    private BaseDataxPlugin writerPlugin;

    private HiveReaderDto hiveReaderDto;

    private HiveWriterDto hiveWriterDto;

    private HbaseReaderDto hbaseReaderDto;

    private HbaseWriterDto hbaseWriterDto;

    private RdbmsReaderDto rdbmsReaderDto;

    private RdbmsWriterDto rdbmsWriterDto;

    private MongoDBReaderDto mongoDBReaderDto;

    private MongoDBWriterDto mongoDBWriterDto;

    private ReaderSync readerSync;

    private WriterPartition writerPartition;

    private String impalaHdfs;

    private String hiveHdfs;

    //用于保存额外参数
    private Map<String, Object> extraParams = Maps.newHashMap();

    public void initReader(DataXJsonBuildDto dataxJsonDto, JobDatasource readerDatasource) {

        this.readerDatasource = readerDatasource;
        this.readerTables = dataxJsonDto.getReaderTables();
        this.readerColumns = dataxJsonDto.getReaderColumns();
        this.hiveReaderDto = dataxJsonDto.getHiveReader();
        this.rdbmsReaderDto = dataxJsonDto.getRdbmsReader();
        this.hbaseReaderDto = dataxJsonDto.getHbaseReader();
        // reader 插件
        String datasource = readerDatasource.getDatasource();

        this.readerColumns = convertKeywordsColumns(datasource, this.readerColumns);
        if (MYSQL.equals(datasource)) {
            readerPlugin = new MysqlReader();
            buildReader = buildReader();
        } else if (ORACLE.equals(datasource)) {
            readerPlugin = new OracleReader();
            buildReader = buildReader();
        } else if (SQL_SERVER.equals(datasource)) {
            readerPlugin = new SqlServerReader();
            buildReader = buildReader();
        } else if (POSTGRESQL.equals(datasource) || GREENPLUM.equals(datasource)) {
            readerPlugin = new PostgresqlReader();
            buildReader = buildReader();
        } else if (CLICKHOUSE.equals(datasource)) {
            readerPlugin = new ClickHouseReader();
            buildReader = buildReader();
        } else if (HIVE.equals(datasource)) {
            readerPlugin = new HiveReader();
            buildReader = buildHiveReader();
        } else if (HBASE.equals(datasource)) {
            readerPlugin = new HBaseReader();
            buildReader = buildHBaseReader();
        } else if (MONGODB.equals(datasource)) {
            readerPlugin = new MongoDBReader();
            buildReader = buildMongoDBReader();
        }else if(IMPALA.equals(datasource) || DB2.equals(datasource)){
            String username = readerDatasource.getJdbcUsername();
            String password = readerDatasource.getJdbcPassword();
            readerDatasource.setJdbcUsername((username != null && !"".equals(username)) ? username : "default");
            readerDatasource.setJdbcPassword((password != null && !"".equals(password)) ? password : "default");
            readerPlugin = new RdbmsReader();
            buildReader = buildReader();
        }
    }

    public void initQualityReader(DataXJsonBuildDto dataxJsonDto, JobDatasource readerDatasource) {

        this.readerDatasource = readerDatasource;
        this.readerTables = dataxJsonDto.getReaderTables();
        this.readerColumns = dataxJsonDto.getReaderColumns();
        this.hiveReaderDto = dataxJsonDto.getHiveReader();
        this.rdbmsReaderDto = dataxJsonDto.getRdbmsReader();
        this.hbaseReaderDto = dataxJsonDto.getHbaseReader();
        // reader 插件
        String datasource = readerDatasource.getDatasource();

        this.readerColumns = convertKeywordsColumns(datasource, this.readerColumns);
        if (MYSQL.equals(datasource)) {
            readerPlugin = new MysqlReader();
            buildReader = buildReader();
        } else if (ORACLE.equals(datasource)) {
            readerPlugin = new OracleReader();
            buildReader = buildReader();
        } else if (SQL_SERVER.equals(datasource)) {
            readerPlugin = new SqlServerReader();
            buildReader = buildReader();
        } else if (POSTGRESQL.equals(datasource) || GREENPLUM.equals(datasource)) {
            readerPlugin = new PostgresqlReader();
            buildReader = buildReader();
        } else if (CLICKHOUSE.equals(datasource)) {
            readerPlugin = new ClickHouseReader();
            buildReader = buildReader();
        } else if (HIVE.equals(datasource) || IMPALA.equals(datasource) || DB2.equals(datasource)) {
            String username = readerDatasource.getJdbcUsername();
            String password = readerDatasource.getJdbcPassword();
            readerDatasource.setJdbcUsername((username != null && !"".equals(username)) ? username : "default");
            readerDatasource.setJdbcPassword((password != null && !"".equals(password)) ? password : "default");
            readerPlugin = new RdbmsReader();
            buildReader = buildReader();
        } else if (HBASE.equals(datasource)) {
            readerPlugin = new HBaseReader();
            buildReader = buildHBaseReader();
        } else if (MONGODB.equals(datasource)) {
            readerPlugin = new MongoDBReader();
            buildReader = buildMongoDBReader();
        }
    }

    public void initImportReader(ImportJsonDto importJsonDto, JobDatasource readerDatasource) {

        this.readerDatasource = readerDatasource;
        this.readerTables = importJsonDto.getReaderTables();
        this.readerColumns = importJsonDto.getReaderColumns();
        this.hiveReaderDto = importJsonDto.getHiveReader();
        this.rdbmsReaderDto = importJsonDto.getRdbmsReader();
        this.hbaseReaderDto = importJsonDto.getHbaseReader();
        this.readerSync=importJsonDto.getReaderSync();
        this.writerPartition=importJsonDto.getWriterPartition();
        // reader 插件
//        this.importJsonBuildDto=new ImportJsonBuildDto();
//        this.importJsonBuildDto.setIncExtract("id");
        String datasource = readerDatasource.getDatasource();

        this.readerColumns = convertKeywordsColumns(datasource, this.readerColumns);
        if (MYSQL.equals(datasource)) {
            readerPlugin = new MysqlReader();
            buildReader = buildReader();
        } else if (ORACLE.equals(datasource)) {
            readerPlugin = new OracleReader();
            buildReader = buildReader();
        } else if (SQL_SERVER.equals(datasource)) {
            readerPlugin = new SqlServerReader();
            buildReader = buildReader();
        } else if (POSTGRESQL.equals(datasource) || GREENPLUM.equals(datasource)) {
            readerPlugin = new PostgresqlReader();
            buildReader = buildReader();
        } else if (CLICKHOUSE.equals(datasource)) {
            readerPlugin = new ClickHouseReader();
            buildReader = buildReader();
        } else if (HIVE.equals(datasource) || IMPALA.equals(datasource) || DB2.equals(datasource)) {
            String username = readerDatasource.getJdbcUsername();
            String password = readerDatasource.getJdbcPassword();
            readerDatasource.setJdbcUsername((username != null && !"".equals(username)) ? username : "default");
            readerDatasource.setJdbcPassword((password != null && !"".equals(password)) ? password : "default");
            readerPlugin = new RdbmsReader();
            buildReader = buildReader();
        } else if (HBASE.equals(datasource)) {
            readerPlugin = new HBaseReader();
            buildReader = buildHBaseReader();
        } else if (MONGODB.equals(datasource)) {
            readerPlugin = new MongoDBReader();
            buildReader = buildMongoDBReader();
        }
    }

    public void initWriter(DataXJsonBuildDto dataxJsonDto, JobDatasource readerDatasource) {
        this.writerDatasource = readerDatasource;
        this.writerTables = dataxJsonDto.getWriterTables();
        this.writerColumns = dataxJsonDto.getWriterColumns();
        this.hiveWriterDto = dataxJsonDto.getHiveWriter();
        this.rdbmsWriterDto = dataxJsonDto.getRdbmsWriter();
        this.hbaseWriterDto = dataxJsonDto.getHbaseWriter();
        this.mongoDBWriterDto = dataxJsonDto.getMongoDBWriter();
        // writer
        String datasource = readerDatasource.getDatasource();
        this.writerColumns = convertKeywordsColumns(datasource, this.writerColumns);
        if (MYSQL.equals(datasource)) {
            writerPlugin = new MysqlWriter();
            buildWriter = this.buildWriter();
        } else if (ORACLE.equals(datasource)) {
            writerPlugin = new OraclelWriter();
            buildWriter = this.buildWriter();
        } else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
            writerPlugin = new SqlServerlWriter();
            buildWriter = this.buildWriter();
        } else if (POSTGRESQL.equals(datasource) || GREENPLUM.equals(datasource)) {
            writerPlugin = new PostgresqllWriter();
            buildWriter = this.buildWriter();
        } else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
            writerPlugin = new ClickHouseWriter();
            buildWriter = buildWriter();
        } else if (JdbcConstants.HIVE.equals(datasource)) {
            writerPlugin = new HiveWriter();
            buildWriter = this.buildHiveWriter();
        } else if (JdbcConstants.HBASE.equals(datasource)) {
            writerPlugin = new HBaseWriter();
            buildWriter = this.buildHBaseWriter();
        } else if (JdbcConstants.MONGODB.equals(datasource)) {
            writerPlugin = new MongoDBWriter();
            buildWriter = this.buildMongoDBWriter();
        }else if(IMPALA.equals(datasource)){
            String username = readerDatasource.getJdbcUsername();
            String password = readerDatasource.getJdbcPassword();
            readerDatasource.setJdbcUsername((username != null && !"".equals(username)) ? username : "default");
            readerDatasource.setJdbcPassword((password != null && !"".equals(password)) ? password : "default");
            writerPlugin = new ImpalaWriter();
            buildWriter = this.buildHiveWriter();
        }
    }
    public void initImportWriter(ImportJsonDto importJsonDto, JobDatasource writerDatasource) {
        try {
            this.writerDatasource = writerDatasource;
            this.writerTables = importJsonDto.getWriterTables();
            this.writerColumns = importJsonDto.getWriterColumns();
            this.hiveWriterDto = importJsonDto.getHiveWriter();
            this.rdbmsWriterDto = importJsonDto.getRdbmsWriter();
            this.hbaseWriterDto = importJsonDto.getHbaseWriter();
            this.mongoDBWriterDto = importJsonDto.getMongoDBWriter();
            this.impalaHdfs=importJsonDto.getImpalaHdfs();
            this.hiveHdfs=importJsonDto.getHiveHdfs();
            // writer
            String datasource = writerDatasource.getDatasource();
            this.writerColumns = convertKeywordsColumns(datasource, this.writerColumns);
            if (MYSQL.equals(datasource)) {
                writerPlugin = new MysqlWriter();
                buildWriter = this.buildWriter();
            } else if (ORACLE.equals(datasource)) {
                writerPlugin = new OraclelWriter();
                buildWriter = this.buildWriter();
            } else if (JdbcConstants.SQL_SERVER.equals(datasource)) {
                writerPlugin = new SqlServerlWriter();
                buildWriter = this.buildWriter();
            } else if (POSTGRESQL.equals(datasource) || GREENPLUM.equals(datasource)) {
                writerPlugin = new PostgresqllWriter();
                buildWriter = this.buildWriter();
            } else if (JdbcConstants.CLICKHOUSE.equals(datasource)) {
                writerPlugin = new ClickHouseWriter();
                buildWriter = buildWriter();
            } else if (JdbcConstants.HIVE.equals(datasource)) {
                writerPlugin = new HiveWriter();
                HiveQueryTool queryTool=new HiveQueryTool(writerDatasource);
                this.getLocation(queryTool,writerDatasource,this.writerTables.get(0));
                buildWriter = this.buildHiveWriter();
            } else if (JdbcConstants.HBASE.equals(datasource)) {
                writerPlugin = new HBaseWriter();
                buildWriter = this.buildHBaseWriter();
            } else if (JdbcConstants.MONGODB.equals(datasource)) {
                writerPlugin = new MongoDBWriter();
                buildWriter = this.buildMongoDBWriter();
            }else if(IMPALA.equals(datasource)){
                String username = writerDatasource.getJdbcUsername();
                String password = writerDatasource.getJdbcPassword();
                writerDatasource.setJdbcUsername((username != null && !"".equals(username)) ? username : "default");
                writerDatasource.setJdbcPassword((password != null && !"".equals(password)) ? password : "default");
                List<String> columns=this.getImpalaCol(writerDatasource,this.writerTables.get(0));
                if(UUIDUtils.notEmpty(columns)){
                    this.setWriterColumns(columns);
                }
                ImpalaQueryTool queryTool=new ImpalaQueryTool(writerDatasource);
                this.getLocation(queryTool,writerDatasource,this.writerTables.get(0));
                writerPlugin = new HiveWriter();
                buildWriter = this.buildHiveWriter();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public List<String> getImpalaCol(JobDatasource jobDatasource,String table) {
        try {
            return new ImpalaQueryTool(jobDatasource).getImpalaColumnNames(table,jobDatasource.getDatasource());
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @author: lxq
     * @description: 获取hivewrite的参数
     * @date: 2021/2/1 10:59
     * @param jobDatasource
     * @param table
     * @return: void
     */
    public void getLocation(Object o,JobDatasource jobDatasource,String table) {
        String str=null;
        try {
            this.hiveWriterDto.setId(jobDatasource.getId());
            Class c = Class.forName(o.getClass().getName());//获取查询的类名
            Constructor con = c.getConstructor(JobDatasource.class);//对类进行有参构造方法的初始化
            // 通过带参构造方法对象创建对象
            Object obj = con.newInstance(jobDatasource);
            //获取执行的方法和参数
            Method method = c.getMethod("getLocation",String.class);
            str=(String) method.invoke(obj,table);//执行方法
            String str1 = str.substring(0, str.indexOf("/"));
            String str2 = str.substring(str1.length()+1, str.length());
            String str3 = str2.substring(0, str2.indexOf("'"));
            int index=str3.indexOf("/");
            index=str3.indexOf("/",index+1);
            String path=str3.substring(index+1);
            String str5=str3.substring(0,index);
            StringBuilder stringBuilder=new StringBuilder();
            if(IMPALA.equals(jobDatasource.getDatasource())){
                stringBuilder.append(this.impalaHdfs);
            }
            if(HIVE.equals(jobDatasource.getDatasource())){
                stringBuilder.append(this.hiveHdfs);
            }
            this.hiveWriterDto.setWriteMode("append");
            this.hiveWriterDto.setWriteFieldDelimiter(",");
            this.hiveWriterDto.setWriterDefaultFS(stringBuilder.toString());
            this.hiveWriterDto.setWriterFileName(table+"_temp");
            this.hiveWriterDto.setWriterFileType("text");
            path=path.replace(table,table+"_temp");
            this.hiveWriterDto.setWriterPath("/"+path);
            StringBuilder preSql=new StringBuilder();//datax任务执行前执行的sql
            StringBuilder postSql=new StringBuilder();//datax任务执行后执行的sql
            postSql.append("INSERT INTO "+table);
            if(UUIDUtils.notEmpty(writerPartition)&& writerPartition.getPartition()==0){
                if(UUIDUtils.notEmpty(writerPartition.getPartitionText())){
                    postSql.append("PARTITION (").append(writerPartition.getPartitionText()).append(") ");
                }
            }
            postSql.append(" SELECT ");
            preSql.append("CREATE TABLE IF NOT EXISTS ");
            preSql.append(table+"_temp").append("(");
            for (String c1: writerColumns) {
                preSql.append(c1.split(Constants.SPLIT_SCOLON)[1]).append(" ");
                preSql.append(c1.split(Constants.SPLIT_SCOLON)[2]).append(",");
                postSql.append(c1.split(Constants.SPLIT_SCOLON)[1]).append(",");
            };
            postSql=postSql.deleteCharAt(postSql.length()-1);
            postSql.append(" FROM "+table+"_temp");
            preSql=preSql.deleteCharAt(preSql.length()-1);
            preSql.append(") ");
            preSql.append(" ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' ");
            preSql.append("LOCATION '").append(stringBuilder.toString()+"/"+path).append("'");
            this.hiveWriterDto.setPreSql(preSql.toString());
            this.hiveWriterDto.setPostSql(postSql.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> convertKeywordsColumns(String datasource, List<String> columns) {
        if (columns == null) {
            return null;
        }

        List<String> toColumns = new ArrayList<>();
        columns.forEach(s -> {
            toColumns.add(doConvertKeywordsColumn(datasource, s));
        });
        return toColumns;
    }

    private String doConvertKeywordsColumn(String dbType, String column) {
        if (column == null) {
            return null;
        }

        column = column.trim();
        column = column.replace("[", "");
        column = column.replace("]", "");
        column = column.replace("`", "");
        column = column.replace("\"", "");
        column = column.replace("'", "");

        switch (dbType) {
            case MYSQL:
                return String.format("`%s`", column);
            case SQL_SERVER:
                return String.format("[%s]", column);
            case POSTGRESQL:
            case ORACLE:
                return String.format("\"%s\"", column);
            default:
                return column;
        }
    }

    @Override
    public Map<String, Object> buildJob() {
        Map<String, Object> res = Maps.newLinkedHashMap();
        Map<String, Object> jobMap = Maps.newLinkedHashMap();
        jobMap.put("setting", buildSetting());
        jobMap.put("content", ImmutableList.of(buildContent()));
        res.put("job", jobMap);
        return res;
    }

    @Override
    public Map<String, Object> buildSetting() {
        Map<String, Object> res = Maps.newLinkedHashMap();
        Map<String, Object> speedMap = Maps.newLinkedHashMap();
        Map<String, Object> errorLimitMap = Maps.newLinkedHashMap();
        //speedMap.putAll(ImmutableMap.of("channel", 3, "byte", 1048576));
        speedMap.putAll(ImmutableMap.of("channel",3));
        errorLimitMap.putAll(ImmutableMap.of("record", 0, "percentage", 0.02));
        res.put("speed", speedMap);
        res.put("errorLimit", errorLimitMap);
        return res;
    }

    @Override
    public Map<String, Object> buildContent() {
        Map<String, Object> res = Maps.newLinkedHashMap();
        res.put("reader", this.buildReader);
        res.put("writer", this.buildWriter);
        return res;
    }

    @Override
    public Map<String, Object> buildReader() {
        DataxRdbmsPojo dataxPluginPojo = new DataxRdbmsPojo();
        dataxPluginPojo.setJobDatasource(readerDatasource);
        dataxPluginPojo.setTables(readerTables);
        dataxPluginPojo.setRdbmsColumns(readerColumns);
        dataxPluginPojo.setSplitPk(rdbmsReaderDto.getReaderSplitPk());
        if (StringUtils.isNotBlank(rdbmsReaderDto.getQuerySql())) {
            dataxPluginPojo.setQuerySql(rdbmsReaderDto.getQuerySql());
        }
        //where
        if (StringUtils.isNotBlank(rdbmsReaderDto.getWhereParams())) {
            dataxPluginPojo.setWhereParam(rdbmsReaderDto.getWhereParams());
        }
        //同步方式为每日增量
        if(UUIDUtils.notEmpty(readerSync)&& readerSync.getSyncType()==0){
            //每日增量,选取时间字段
            if(UUIDUtils.notEmpty(readerSync)&& StringUtils.isNotBlank(readerSync.getIncExtract())){
                Date date= DateFormatUtils.addTime(new Date(),Calendar.DATE,-2);
                dataxPluginPojo.setWhereParam(readerSync.getIncExtract()+"> '"+DateFormatUtils.formatDateTime(date)+"'");
            }
            //每日增量,自定义条件
            if(UUIDUtils.notEmpty(readerSync)&&StringUtils.isNotBlank(readerSync.getIncExtractText())){
                dataxPluginPojo.setWhereParam(readerSync.getIncExtractText());
            }
        }else {
            //每日全量
            dataxPluginPojo.setWhereParam("1=1");
        }

        return readerPlugin.build(dataxPluginPojo);
    }

    @Override
    public Map<String, Object> buildHiveReader() {
        DataxHivePojo dataxHivePojo = new DataxHivePojo();
        dataxHivePojo.setJdbcDatasource(readerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        readerColumns.forEach(c -> {
            Map<String, Object> column = Maps.newLinkedHashMap();
            column.put("index", c.split(Constants.SPLIT_SCOLON)[0]);
            column.put("type", c.split(Constants.SPLIT_SCOLON)[2]);
            columns.add(column);
        });
        dataxHivePojo.setColumns(columns);
        dataxHivePojo.setReaderDefaultFS(hiveReaderDto.getReaderDefaultFS());
        dataxHivePojo.setReaderFieldDelimiter(hiveReaderDto.getReaderFieldDelimiter());
        dataxHivePojo.setReaderFileType(hiveReaderDto.getReaderFileType());
        dataxHivePojo.setReaderPath(hiveReaderDto.getReaderPath());
        dataxHivePojo.setSkipHeader(hiveReaderDto.getReaderSkipHeader());
        return readerPlugin.buildHive(dataxHivePojo);
    }

    @Override
    public Map<String, Object> buildHBaseReader() {
        DataxHbasePojo dataxHbasePojo = new DataxHbasePojo();
        dataxHbasePojo.setJdbcDatasource(readerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        for (int i = 0; i < readerColumns.size(); i++) {
            Map<String, Object> column = Maps.newLinkedHashMap();
            column.put("name", readerColumns.get(i));
            column.put("type", "string");
            columns.add(column);
        }
        dataxHbasePojo.setColumns(columns);
        dataxHbasePojo.setReaderHbaseConfig(readerDatasource.getZkAdress());
        String readerTable=!CollectionUtils.isEmpty(readerTables)?readerTables.get(0):Constants.STRING_BLANK;
        dataxHbasePojo.setReaderTable(readerTable);
        dataxHbasePojo.setReaderMode(hbaseReaderDto.getReaderMode());
        dataxHbasePojo.setReaderRange(hbaseReaderDto.getReaderRange());
        return readerPlugin.buildHbase(dataxHbasePojo);
    }

    @Override
    public Map<String, Object> buildMongoDBReader() {
        DataxMongoDBPojo dataxMongoDBPojo = new DataxMongoDBPojo();
        dataxMongoDBPojo.setJdbcDatasource(readerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        buildColumns(readerColumns, columns);
        dataxMongoDBPojo.setColumns(columns);
        dataxMongoDBPojo.setAddress(readerDatasource.getJdbcUrl());
        dataxMongoDBPojo.setDbName(readerDatasource.getDatabaseName());
        dataxMongoDBPojo.setReaderTable(readerTables.get(0));
        return readerPlugin.buildMongoDB(dataxMongoDBPojo);
    }


    @Override
    public Map<String, Object> buildWriter() {
        DataxRdbmsPojo dataxPluginPojo = new DataxRdbmsPojo();
        dataxPluginPojo.setJobDatasource(writerDatasource);
        dataxPluginPojo.setTables(writerTables);
        dataxPluginPojo.setRdbmsColumns(writerColumns);
        dataxPluginPojo.setPreSql(rdbmsWriterDto.getPreSql());
        dataxPluginPojo.setPostSql(rdbmsWriterDto.getPostSql());
        return writerPlugin.build(dataxPluginPojo);
    }

    @Override
    public Map<String, Object> buildHiveWriter() {
        DataxHivePojo dataxHivePojo = new DataxHivePojo();
        dataxHivePojo.setJdbcDatasource(writerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        writerColumns.forEach(c -> {
            Map<String, Object> column = Maps.newLinkedHashMap();
            column.put("name", c.split(Constants.SPLIT_SCOLON)[1]);
            column.put("type", c.split(Constants.SPLIT_SCOLON)[2]);
            columns.add(column);
        });
        dataxHivePojo.setColumns(columns);
        dataxHivePojo.setWriterDefaultFS(hiveWriterDto.getWriterDefaultFS());
        dataxHivePojo.setWriteFieldDelimiter(hiveWriterDto.getWriteFieldDelimiter());
        dataxHivePojo.setWriterFileType(hiveWriterDto.getWriterFileType());
        dataxHivePojo.setWriterPath(hiveWriterDto.getWriterPath());
        dataxHivePojo.setWriteMode(hiveWriterDto.getWriteMode());
        dataxHivePojo.setWriterFileName(hiveWriterDto.getWriterFileName());
        dataxHivePojo.setPreSql(hiveWriterDto.getPreSql());
        dataxHivePojo.setPostSql(hiveWriterDto.getPostSql());
        dataxHivePojo.setId(hiveWriterDto.getId());
        return writerPlugin.buildHive(dataxHivePojo);
    }

    @Override
    public Map<String, Object> buildHBaseWriter() {
        DataxHbasePojo dataxHbasePojo = new DataxHbasePojo();
        dataxHbasePojo.setJdbcDatasource(writerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        for (int i = 0; i < writerColumns.size(); i++) {
            Map<String, Object> column = Maps.newLinkedHashMap();
            column.put("index", i + 1);
            column.put("name", writerColumns.get(i));
            column.put("type", "string");
            columns.add(column);
        }
        dataxHbasePojo.setColumns(columns);
        dataxHbasePojo.setWriterHbaseConfig(writerDatasource.getZkAdress());
        String writerTable=!CollectionUtils.isEmpty(writerTables)?writerTables.get(0):Constants.STRING_BLANK;
        dataxHbasePojo.setWriterTable(writerTable);
        dataxHbasePojo.setWriterVersionColumn(hbaseWriterDto.getWriterVersionColumn());
        dataxHbasePojo.setWriterRowkeyColumn(hbaseWriterDto.getWriterRowkeyColumn());
        dataxHbasePojo.setWriterMode(hbaseWriterDto.getWriterMode());
        return writerPlugin.buildHbase(dataxHbasePojo);
    }


    @Override
    public Map<String, Object> buildMongoDBWriter() {
        DataxMongoDBPojo dataxMongoDBPojo = new DataxMongoDBPojo();
        dataxMongoDBPojo.setJdbcDatasource(writerDatasource);
        List<Map<String, Object>> columns = Lists.newArrayList();
        buildColumns(writerColumns, columns);
        dataxMongoDBPojo.setColumns(columns);
        dataxMongoDBPojo.setAddress(writerDatasource.getJdbcUrl());
        dataxMongoDBPojo.setDbName(writerDatasource.getDatabaseName());
        dataxMongoDBPojo.setWriterTable(readerTables.get(0));
        dataxMongoDBPojo.setUpsertInfo(mongoDBWriterDto.getUpsertInfo());
        return writerPlugin.buildMongoDB(dataxMongoDBPojo);
    }

    private void buildColumns(List<String> columns, List<Map<String, Object>> returnColumns) {
        columns.forEach(c -> {
            Map<String, Object> column = Maps.newLinkedHashMap();
            column.put("name", c.split(Constants.SPLIT_SCOLON)[0]);
            column.put("type", c.split(Constants.SPLIT_SCOLON)[1]);
            returnColumns.add(column);
        });
    }
}
