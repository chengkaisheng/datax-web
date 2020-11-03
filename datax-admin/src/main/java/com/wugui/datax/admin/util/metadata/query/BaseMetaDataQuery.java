package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.HttpClientHelper;
import com.wugui.datax.admin.util.MetadataBuildUtils;
import com.wugui.datax.admin.util.metadata.tool.BaseCloudBeaverHelper;
import com.wugui.datax.admin.util.metadata.tool.MetadataType;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasException;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.EntityMutationResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hf
 * @creat 2020-11-03-13:19
 */
public abstract class BaseMetaDataQuery implements MetaDataQueryInterface{

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private JobDatasource jobDatasource;

    private String connId;

    private Map<String,String> metadataTypeNameMap;

    private AtlasClientV2 atlasClientV2;

    private BaseCloudBeaverHelper cloudBeaverHelper;


    public BaseMetaDataQuery(JobDatasource jobDatasource) throws AtlasException {
        this.metadataTypeNameMap = MetadataType.getMetaDataTypeName(jobDatasource.getDatasource());
        this.jobDatasource = jobDatasource;
        this.atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
    }

    @Override
    public Map<String, String> setInstanceMetadata() {
        AtlasEntity atlasEntity = this.buildInstanceEntity();
        List<Map.Entry<String, String>> entities = this.createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntity));
        Map<String, String> guidMap = new HashMap<>();
        for (int i = 0; i < entities.size(); i++) {
            guidMap.put(jobDatasource.getDatabaseName(), entities.get(i).getValue());
        }
        return guidMap;
    }

    @Override
    public Map<String, String> setDbMetadata() throws IOException {
        Map<String, Map<String, String>> databaseInfo = cloudBeaverHelper.getDatabaseInfo(connId, jobDatasource.getDatabaseName());
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        for (Map.Entry<String, Map<String, String>> stringMapEntry : databaseInfo.entrySet()) {
            Map<String, String> properties = stringMapEntry.getValue();
            atlasEntities.add(this.buildDbEntity(properties));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < entities.size(); i++) {
            guidMap.put(jobDatasource.getDatabaseName(), entities.get(i).getValue());
        }
        return guidMap;
    }

    @Override
    public Map<String, String> setTableMetadata(String database,
                                                List<String> tableNames,
                                                String guid) throws IOException {
        Map<String, Map<String,String>> map = cloudBeaverHelper.getTablesInfo(connId, database, tableNames);
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

    @Override
    public Map<String, String> setColumnMetadata(String database,
                                                 String tableName,
                                                 List<String> columns,
                                                 String guid) throws IOException {
        Map<String,Map<String,String>> map = cloudBeaverHelper.getColumnsInfo(connId, database, tableName, columns);
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

    @Override
    public Map<String, String> setIndexMetadata() {
        return null;
    }

    @Override
    public AtlasEntity buildInstanceEntity() {
        String[] split = jobDatasource.getJdbcUrl().split("/");
        String host = split[split.length-1].split(":")[0];
        String port = split[split.length-1].split(":")[1];
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("instance"));
        atlasEntity.setAttribute("qualifiedName", host +"@"+ jobDatasource.getDatasource());
        atlasEntity.setAttribute("host", host);
        atlasEntity.setAttribute("port", port);
        atlasEntity.setAttribute("instanceType", jobDatasource.getDatasource());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildDbEntity(Map<String, String> properties) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("db"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }


    @Override
    public AtlasEntity buildTableEntity(String tableName,
                                        Map<String, String> properties,
                                        Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("table"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl() + "." + tableName);
        atlasEntity.setAttribute("db", relationship);
        properties.forEach(atlasEntity::setAttribute);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildColumnEntity(String columnName,
                                         String tableName,
                                         Map<String, String> properties,
                                         Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("column"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getJdbcUrl() + "." + tableName + "." + columnName);
        atlasEntity.setAttribute("table", relationship);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildIndexEntity() {
        return null;
    }

    private Map<String, String> buildRelation(String guid) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        return map;
    }

    private Map<String, String> buildRelation(String guid,String typeName) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        map.put("typeName", typeName);
        return map;
    }

    private List<Map.Entry<String,String>> createEntities(AtlasClientV2 atlasClientV2,
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


}
