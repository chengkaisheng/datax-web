package com.wugui.datax.admin.datashare.service;


import java.util.List;

public interface DataCatalogService {

    /**
     * 根据所在服务器名称  查询表名等信息
     * @param serverName
     * @return
     */
    Object getCatalogByServerName(String serverName);

    /**
     * 根据infoName 查询出数据项
     * @param infoName
     * @return
     */
    Object getItemByInfoName(String infoName);

}
