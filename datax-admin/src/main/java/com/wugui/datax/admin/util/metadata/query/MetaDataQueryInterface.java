package com.wugui.datax.admin.util.metadata.query;

import org.apache.atlas.model.instance.AtlasEntity;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-13:18
 */
public interface MetaDataQueryInterface {

    Map<String,String> setInstanceMetadata();

    Map<String,String> setDbMetadata(String guid) throws IOException, SQLException;

    Map<String,String> setTableMetadata(String database,
                                        List<String> tableNames,
                                        String guid) throws IOException, SQLException;

    Map<String,String> setColumnMetadata(String database,
                                         String tableName,
                                         List<String> columns,
                                         String guid) throws IOException;

    Map<String,String> setIndexMetadata(String database,
                                        String tableName,
                                        List<String> indexes,
                                        String guid) throws IOException;

    AtlasEntity buildInstanceEntity();

    AtlasEntity buildDbEntity(Map<String, Object> properties, Map<String, String> relationship);

    AtlasEntity buildTableEntity(String tableName,
                                 Map<String, Object> properties,
                                 Map<String, String> relationship);

    AtlasEntity buildColumnEntity(String columnName,
                                  String tableName,
                                  Map<String, Object> properties,
                                  Map<String, String> relationship);

    AtlasEntity buildIndexEntity(String indexName,
                                 String tableName,
                                 Map<String, Object> properties,
                                 Map<String, String> relationship);
}
