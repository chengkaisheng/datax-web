package com.wugui.datax.admin.service;


import com.wugui.datax.admin.dto.ImportJsonDto;

public interface ImportJsonService {

    /**
     * @author: lxq
     * @description: 构建引入任务json
     * @date: 2021/1/29 11:29
     * @param dto
     * @return: java.lang.String
     */
    String buildJobJson(ImportJsonDto dto);
}
