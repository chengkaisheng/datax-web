package com.wugui.datax.admin.util;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.EntityMutationResponse;
import org.apache.commons.collections.CollectionUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hf
 * @creat 2020-09-27-11:53
 */
public class MetadataBuildUtils {
    public static JobDatasource jobDatasource;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String RDBMS_DB = "mysql_test3_db";
    private static final String RDBMS_TABLE = "mysql_test3_table";
    private static final String RDBMS_COLUMN = "mysql_test3_column";

    public static Map<String,String> setRdbmsDb(AtlasClientV2 atlasClientV2,
                                                String coonId) throws IOException {
        Map<String, Map<String, String>> databaseInfo = HttpClientHelper.getDatabaseInfo(coonId, jobDatasource.getDatabaseName());
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> stringMapEntry : databaseInfo.entrySet()) {
            String databaseName = stringMapEntry.getKey();
            Map<String, String> properties = stringMapEntry.getValue();
            atlasEntities.add(buildDatabaseEntity(properties));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < entities.size(); i++) {
            guidMap.put(jobDatasource.getDatabaseName(), entities.get(i).getValue());
        }
        return guidMap;
    }



    /**
     *@author 27441
     *@Description 调用api /v2/entity/bulk 批量插入元数据
     *@Date 11:26 2020/9/27
     *@Param [atlasClientV2, coonId, database, tableNames]
     *@return java.util.List<java.lang.String>
     **/
    public static Map<String, String> setRdbmsTables(AtlasClientV2 atlasClientV2,
                                                     String coonId,
                                                     String database,
                                                     List<String> tableNames,
                                                     String guid) throws IOException {
        if(CollectionUtils.isEmpty(tableNames)){
            throw new RuntimeException("创建失败");
        }
        Map<String, Map<String,String>> map = HttpClientHelper.getTablesInfo(coonId, database, tableNames);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, String>> stringMapEntry : map.entrySet()) {
            String tableName = stringMapEntry.getKey();
            Map<String, String> properties = stringMapEntry.getValue();
            atlasEntities.add(buildTableEntity(tableName, properties, relationship));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < tableNames.size(); i++) {
            guidMap.put(tableNames.get(i), entities.get(i).getValue());
        }
        return guidMap;
    }




    public static Map<String, String> setRdbmsColumns(AtlasClientV2 atlasClientV2,
                                                      String coonId,
                                                      String database,
                                                      String tableName,
                                                      List<String> columns,
                                                      String guid) throws IOException {
        if(CollectionUtils.isEmpty(columns)){
            throw new RuntimeException("创建失败");
        }
        Map<String,Map<String,String>> map = HttpClientHelper.getColumnsInfo(coonId, database, tableName, columns);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, String>> stringMapEntry : map.entrySet()) {
            String columnName = stringMapEntry.getKey();
            Map<String, String> properties = stringMapEntry.getValue();
            atlasEntities.add(buildColumnEntity(columnName,tableName, properties, relationship));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            guidMap.put(columns.get(i), entities.get(i).getValue());
        }
        return guidMap;
    }

    public static Map<String, String> buildRelation(String guid) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        return map;
    }

    public static Map<String, String> buildRelation(String guid,String typeName) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        map.put("typeName", typeName);
        return map;
    }

    public static AtlasEntity buildColumnEntity(String columnName,
                                                 String tableName,
                                                 Map<String, String> properties,
                                                 Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(RDBMS_COLUMN);
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl() + "." + tableName + "." + columnName);
        atlasEntity.setAttribute("table", relationship);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    /**
    *@author 27441
    *@Description 调用api
    *@Date 14:38 2020/9/27
    *@Param [atlasClientV2, atlasEntitiesWithExtInfo]
    *@return org.apache.atlas.model.instance.AtlasEntityHeader
    **/
    public static List<Map.Entry<String,String>> createEntities(AtlasClientV2 atlasClientV2,
                                                                 AtlasEntity.AtlasEntitiesWithExtInfo atlasEntitiesWithExtInfo) {
        EntityMutationResponse entity;

        try {
            //call API
            entity = atlasClientV2.createEntities(atlasEntitiesWithExtInfo);
            Map<String, String> guidMap = entity.getGuidAssignments();
            List<Map.Entry<String,String>> lstEntry=new ArrayList<>(guidMap.entrySet());
            Collections.sort(lstEntry,((o1, o2) ->
                o2.getKey().compareTo(o1.getKey())
            ));
            return lstEntry;

        } catch (AtlasServiceException e) {

            e.printStackTrace();
        }
        return null;
    }


    /**
    *@author 27441
    *@Description 创建table实体 根据得到的guid和typeName与数据库建立联系
    *@Date 14:36 2020/9/27
    *@Param [jobDatasource, tableName, properties, relationship]
    *@return org.apache.atlas.model.instance.AtlasEntity
    **/
    private static AtlasEntity buildTableEntity(String tableName,
                                                Map<String, String> properties,
                                                Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(RDBMS_TABLE);
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl() + "." + tableName);
        atlasEntity.setAttribute("db", relationship);
        properties.forEach(atlasEntity::setAttribute);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    private static AtlasEntity buildDatabaseEntity(Map<String, String> properties) {
        AtlasEntity atlasEntity = new AtlasEntity(RDBMS_DB);
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    public static String[] getUserInput() {
        try {
            /*String  username = System.console().readLine("Enter username: ");
            char[]  pwChar   = System.console().readPassword("Enter password: ");*/
            String  username = "admin";
            char[]  pwChar   = {'a','d','m','i','n'};
            String  password = (pwChar != null) ? new String(pwChar) : "";

            return new String[] { username, password };
        } catch (Exception e) {
            System.out.print("Error while reading user input");
            System.exit(1);
        }

        return null; // will not reach here
    }

    public static String[] getServerUrl() {
        try {
//            String atlasServerUrl = System.console().readLine("Enter Atlas server URL: ");
            String atlasServerUrl = "http://123.56.96.151:8079";

            return new String[] { atlasServerUrl };
        } catch (Exception e) {
            System.out.print("Error while reading user input");
            System.exit(1);
        }

        return null; // will not reach here
    }

    public static List<Map.Entry<String,String>> setRdbmsInstance(AtlasClientV2 atlasClientV2){
        AtlasEntity atlasEntity = buildRdbmsInstance();
        return createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntity));
    }


    public static AtlasEntity buildRdbmsInstance() {
        String[] split = jobDatasource.getJdbcUrl().split("/");
        String host = split[split.length-1].split(":")[0];
        String port = split[split.length-1].split(":")[1];
        AtlasEntity atlasEntity = new AtlasEntity("clickHouse_instance");
        atlasEntity.setAttribute("qualifiedName", host +"@"+ jobDatasource.getDatasource());
        atlasEntity.setAttribute("host", host);
        atlasEntity.setAttribute("port", port);
        atlasEntity.setAttribute("instanceType", jobDatasource.getDatasource());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    public static AtlasEntity buildRdbmsDb(Map<String, String> relationship){
        String[] split = jobDatasource.getJdbcUrl().split("/");
        String host = split[split.length-1].split(":")[0];
        AtlasEntity atlasEntity = new AtlasEntity("clickHouse_db");
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." +host+ "@" + jobDatasource.getDatasource() );
        atlasEntity.setAttribute("databaseName", jobDatasource.getDatabaseName());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        atlasEntity.setAttribute("instance", relationship);
        return atlasEntity;
    }

   /* public static List<Map.Entry<String,String>> setClickHouseDb(AtlasClientV2 atlasClientV2, String guid, List<String> dbs){
        Map<String, String> relation = buildRelation(guid);
        List<Map.Entry<String,String>> list = new ArrayList<>();
        for (String db : dbs) {
            AtlasEntity atlasEntity = buildRdbmsDb(relation);
            list.add(createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntity)));
        }
        return null;

        Map<String, Map<String,String>> map = HttpClientHelper.getTablesInfo(coonId, database, tableNames);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, String>> stringMapEntry : map.entrySet()) {
            String tableName = stringMapEntry.getKey();
            Map<String, String> properties = stringMapEntry.getValue();
            atlasEntities.add(buildTableEntity(tableName, properties, relationship));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < tableNames.size(); i++) {
            guidMap.put(tableNames.get(i), entities.get(i).getValue());
        }
        return guidMap;
    }*/

}
