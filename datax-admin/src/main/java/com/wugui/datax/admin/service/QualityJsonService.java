package com.wugui.datax.admin.service;

import com.wugui.datax.admin.dto.QualityJsonBuildDto;

public interface QualityJsonService {
    /**
     * 构架datax 任务的json
     * @param dto
     * @return
     */
    String buildJobJson(QualityJsonBuildDto dto);
}
