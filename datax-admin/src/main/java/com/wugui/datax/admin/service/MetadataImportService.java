package com.wugui.datax.admin.service;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author hf
 * @creat 2020-11-05-16:51
 */
public interface MetadataImportService {

    public void importMetadata(Long datasourceId) throws IOException, SQLException;
}
