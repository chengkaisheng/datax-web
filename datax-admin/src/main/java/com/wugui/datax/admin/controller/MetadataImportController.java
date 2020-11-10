package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.service.MetadataImportService;
import org.apache.atlas.AtlasException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.SQLException;

/**
 * @author hf
 * @creat 2020-11-05-16:49
 */
@RestController
@RequestMapping("api/metadataImport")
public class MetadataImportController {

    @Autowired
    MetadataImportService metadataImportService;

    @PostMapping("import")
    public ReturnT metadataImport(Long datasourceId) throws IOException, SQLException, AtlasException {
        metadataImportService.importMetadata(datasourceId);
        return new ReturnT(200, "导入元数据成功");
    }
}
