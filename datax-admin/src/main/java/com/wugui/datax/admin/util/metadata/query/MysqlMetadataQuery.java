package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasException;

/**
 * @author hf
 * @creat 2020-11-03-18:17
 */
public class MysqlMetadataQuery extends BaseMetaDataQuery {
    public MysqlMetadataQuery(JobDatasource jobDatasource) throws AtlasException {
        super(jobDatasource);
    }
}
