package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasException;
import org.apache.atlas.model.instance.AtlasEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-04-16:40
 */
public class OracleMetadataQuery extends BaseMetaDataQuery {
    public OracleMetadataQuery(JobDatasource jobDatasource) throws AtlasException, IOException {
        super(jobDatasource);
    }


    @Override
    public Map<String, String> setDbMetadata(String guid) throws IOException, SQLException {
        return super.setDbMetadata(guid);
    }

    @Override
    public Map<String, String> setTableMetadata(String database, List<String> tableNames, String guid) throws IOException, SQLException {
        return super.setTableMetadata(database, tableNames, guid);
    }

    @Override
    public Map<String, String> setColumnMetadata(String database, String tableName, List<String> columns, String guid) throws IOException {
        return super.setColumnMetadata(database, tableName, columns, guid);
    }

    @Override
    public Map<String, String> setIndexMetadata(String database, String tableName, List<String> indexes, String guid) throws IOException {
        return super.setIndexMetadata(database, tableName, indexes, guid);
    }

    @Override
    public AtlasEntity buildInstanceEntity() {
        return super.buildInstanceEntity();
    }

    @Override
    public AtlasEntity buildDbEntity(Map<String, Object> properties, Map<String, String> relationship) {
        return super.buildDbEntity(properties, relationship);
    }

    @Override
    public AtlasEntity buildTableEntity(String tableName, Map<String, Object> properties, Map<String, String> relationship) {
        return super.buildTableEntity(tableName, properties, relationship);
    }

    @Override
    public AtlasEntity buildColumnEntity(String columnName, String tableName, Map<String, Object> properties, Map<String, String> relationship) {
        return super.buildColumnEntity(columnName, tableName, properties, relationship);
    }

    @Override
    public AtlasEntity buildIndexEntity(String indexName, String tableName, Map<String, Object> properties, Map<String, String> relationship) {
        return super.buildIndexEntity(indexName, tableName, properties, relationship);
    }
}
