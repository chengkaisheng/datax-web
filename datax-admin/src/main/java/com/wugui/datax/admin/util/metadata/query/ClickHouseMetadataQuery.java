package com.wugui.datax.admin.util.metadata.query;

import com.wugui.datax.admin.entity.JobDatasource;
import org.apache.atlas.AtlasException;

import java.io.IOException;

/**
 * @author hf
 * @creat 2020-11-03-18:18
 */
public class ClickHouseMetadataQuery extends BaseMetaDataQuery {
    public ClickHouseMetadataQuery(JobDatasource jobDatasource) throws AtlasException, IOException {
        super(jobDatasource);
    }
}
