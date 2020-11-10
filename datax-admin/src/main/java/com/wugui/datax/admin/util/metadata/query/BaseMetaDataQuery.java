package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.MetadataBuildUtils;
import com.wugui.datax.admin.util.metadata.tool.BaseMetadataAssembleHelper;
import com.wugui.datax.admin.util.metadata.tool.MetadataAssembleHelperFactory;
import com.wugui.datax.admin.util.metadata.tool.MetadataType;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasException;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.instance.AtlasEntity;
import org.apache.atlas.model.instance.EntityMutationResponse;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author hf
 * @creat 2020-11-03-13:19
 */
public abstract class BaseMetaDataQuery implements MetaDataQueryInterface{

    protected static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    protected JobDatasource jobDatasource;

    protected Map<String,String> metadataTypeNameMap;

    protected AtlasClientV2 atlasClientV2;

    protected BaseMetadataAssembleHelper metadataAssembleHelper;

    protected String host;

    protected String port;


    public BaseMetaDataQuery(JobDatasource jobDatasource) throws AtlasException, IOException {
        this.metadataTypeNameMap = MetadataType.getMetaDataTypeName(jobDatasource.getDatasource());
        this.jobDatasource = jobDatasource;
        this.atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        this.metadataAssembleHelper = MetadataAssembleHelperFactory.getCloudBeaverHelper(jobDatasource);
        String[] split = jobDatasource.getJdbcUrl().split("/");
        this.host = split[split.length-2].split(":")[0];
        this.port = split[split.length-2].split(":")[1];
    }

    @Override
    public Map<String, String> setInstanceMetadata() throws AtlasException {
        AtlasEntity atlasEntity = this.buildInstanceEntity();
        List<Map.Entry<String, String>> entities = this.createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntity));
        if (CollectionUtils.isEmpty(entities)){
            throw new AtlasException("创建entity失败");
        }
        Map<String, String> guidMap = new HashMap<>();
        for (int i = 0; i < entities.size(); i++) {
            guidMap.put(jobDatasource.getDatasource(), entities.get(i).getValue());
        }
        return guidMap;
    }

    @Override
    public Map<String, String> setDbMetadata(String guid) throws IOException, SQLException {
        Map<String, Map<String, Object>> databaseInfo = metadataAssembleHelper.getDatabaseInfo(jobDatasource.getDatabaseName());
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : databaseInfo.entrySet()) {
            Map<String, Object> properties = stringMapEntry.getValue();
            atlasEntities.add(this.buildDbEntity(properties, relationship));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < entities.size(); i++) {
            guidMap.put(jobDatasource.getDatabaseName(), entities.get(i).getValue());
        }
        return guidMap;
    }

    @Override
    public Map<String, String> setTableMetadata(String database, List<String> tableNames, String guid) throws IOException, SQLException {
        Map<String, Map<String,Object>> map = metadataAssembleHelper.getTablesInfo(database, tableNames);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : map.entrySet()) {
            String tableName = stringMapEntry.getKey();
            Map<String, Object> properties = stringMapEntry.getValue();
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
    public Map<String, String> setColumnMetadata(String database, String tableName, List<String> columns, String guid) throws IOException {
        Map<String,Map<String,Object>> map = metadataAssembleHelper.getColumnsInfo(database, tableName, columns);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : map.entrySet()) {
            String columnName = stringMapEntry.getKey();
            Map<String, Object> properties = stringMapEntry.getValue();
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
    public Map<String, String> setIndexMetadata(String database,
                                                String tableName,
                                                List<String> indexes,
                                                String guid) throws IOException {
        Map<String,Map<String,Object>> map = metadataAssembleHelper.getIndexesInfo(database, tableName, indexes);
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        Map<String,String> relationship = buildRelation(guid);
        for (Map.Entry<String, Map<String, Object>> stringMapEntry : map.entrySet()) {
            String indexName = stringMapEntry.getKey();
            Map<String, Object> properties = stringMapEntry.getValue();
            atlasEntities.add(buildIndexEntity(indexName,tableName, properties, relationship));
        }
        if(CollectionUtils.isEmpty(atlasEntities)){
            return null;
        }
        createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        return null;
    }

    @Override
    public AtlasEntity buildInstanceEntity() {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("instance"));
        atlasEntity.setAttribute("qualifiedName", host +"@"+ jobDatasource.getDatasource());
        atlasEntity.setAttribute("name", host +"@"+ jobDatasource.getDatasource());
        atlasEntity.setAttribute("host", host);
        atlasEntity.setAttribute("port", port);
        atlasEntity.setAttribute("instanceType", jobDatasource.getDatasource());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildDbEntity(Map<String, Object> properties, Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("db"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("name", jobDatasource.getDatabaseName() + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        atlasEntity.setAttribute("instance", relationship);
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildTableEntity(String tableName, Map<String, Object> properties, Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("table"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." + tableName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("name", jobDatasource.getDatabaseName() + "." + tableName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("db", relationship);
        properties.forEach(atlasEntity::setAttribute);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildColumnEntity(String columnName, String tableName, Map<String, Object> properties, Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("column"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." + tableName + "." + columnName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("name", jobDatasource.getDatabaseName() + "." + tableName + "." + columnName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("table", relationship);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildIndexEntity(String indexName,
                                        String tableName,
                                        Map<String, Object> properties,
                                        Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("index"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." + tableName + "." + indexName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("name", jobDatasource.getDatabaseName() + "." + tableName + "." + indexName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("table", relationship);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }

    protected Map<String, String> buildRelation(String guid) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        return map;
    }

    protected Map<String, String> buildRelation(String guid,String typeName) {
        Map<String,String> map = new HashMap<>();
        map.put("guid", guid);
        map.put("typeName", typeName);
        return map;
    }

    protected List<Map.Entry<String,String>> createEntities(AtlasClientV2 atlasClientV2,
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
