package com.wugui.datax.admin.datashare.service;


import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.vo.DataBaseInfoQuery;

import java.util.List;

public interface DataBaseInfoServise {
    /**
     * 新增服务器
     * @param databaseInfo
     * @return
     */
    Object insertDataBaseInfo(TDatabaseInfo databaseInfo);


    /**
     * 修改服务器
     */
    Object updateDataBaseInfo(TDatabaseInfo databaseInfo);

    /**
     * 分页查询服务器信息
     */
    Object getDataBaseInfoPages(DataBaseInfoQuery dataBaseInfoQuery);

    /**
     * 查询所在服务器名称
     * @return
     */
    List<TDatabaseInfo> getDataBaseInfoServerName();

    /**
     * 删除服务器
     * @param databaseInfo
     * @return
     */
    Object deleteDataBaseInfoServer(TDatabaseInfo databaseInfo);
}
