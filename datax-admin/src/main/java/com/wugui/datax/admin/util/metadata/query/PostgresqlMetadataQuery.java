package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasException;

/**
 * @author hf
 * @creat 2020-11-03-18:19
 */
public class PostgresqlMetadataQuery extends BaseMetaDataQuery {
    public PostgresqlMetadataQuery(JobDatasource jobDatasource) throws AtlasException {
        super(jobDatasource);
    }
}
