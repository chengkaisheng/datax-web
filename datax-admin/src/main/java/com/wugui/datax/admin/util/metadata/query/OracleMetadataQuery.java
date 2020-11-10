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
}
