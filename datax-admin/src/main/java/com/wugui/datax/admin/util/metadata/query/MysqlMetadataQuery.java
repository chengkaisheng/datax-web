package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasException;
import org.apache.atlas.model.instance.AtlasEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author hf
 * @creat 2020-11-03-18:17
 */
public class MysqlMetadataQuery extends BaseMetaDataQuery {

    public MysqlMetadataQuery(JobDatasource jobDatasource) throws AtlasException, IOException {
        super(jobDatasource);
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
    public Map<String, String> setTableMetadata(String database,
                                                List<String> tableNames,
                                                String guid) throws IOException, SQLException {
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
    public Map<String, String> setColumnMetadata(String database,
                                                 String tableName,
                                                 List<String> columns,
                                                 String guid) throws IOException {
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
            String columnName = stringMapEntry.getKey();
            Map<String, Object> properties = stringMapEntry.getValue();
            atlasEntities.add(buildIndexEntity(columnName,tableName, properties, relationship));
        }
        List<Map.Entry<String, String>> entities = createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        Map<String,String> guidMap = new HashMap<>();
        for (int i = 0; i < indexes.size(); i++) {
            guidMap.put(indexes.get(i), entities.get(i).getValue());
        }
        return guidMap;
    }

    @Override
    public AtlasEntity buildInstanceEntity() {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("instance"));
        atlasEntity.setAttribute("qualifiedName", host +"@"+ jobDatasource.getDatasource());
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
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        atlasEntity.setAttribute("instance", relationship);
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }


    @Override
    public AtlasEntity buildTableEntity(String tableName,
                                        Map<String, Object> properties,
                                        Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("table"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." + tableName + "@" + host + "@" + jobDatasource.getDatasource());
        atlasEntity.setAttribute("db", relationship);
        properties.forEach(atlasEntity::setAttribute);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        return atlasEntity;
    }

    @Override
    public AtlasEntity buildColumnEntity(String columnName,
                                         String tableName,
                                         Map<String, Object> properties,
                                         Map<String, String> relationship) {
        AtlasEntity atlasEntity = new AtlasEntity(metadataTypeNameMap.get("column"));
        atlasEntity.setAttribute("qualifiedName", jobDatasource.getDatabaseName() + "." + tableName + "." + columnName + "@" + host + "@" + jobDatasource.getDatasource());
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
        atlasEntity.setAttribute("table", relationship);
        atlasEntity.setAttribute("updateTime", sdf.format(new Date()));
        properties.forEach(atlasEntity::setAttribute);
        return atlasEntity;
    }
}
