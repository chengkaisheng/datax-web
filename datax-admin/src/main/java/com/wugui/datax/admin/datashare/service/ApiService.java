package com.wugui.datax.admin.datashare.service;

import com.wugui.datax.admin.datashare.vo.DataBaseInfoQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface ApiService {

    /**
     *
     * @param interCode
     * @param request
     * @return
     */
    Object getDatas(String interCode, HttpServletRequest request);

    /**
     * 成功示例
     * @param tableName
     * @param out
     * @return
     */
    Map querySucessExamples(String id, String tableName, String out);

    /**
     * 测试连接
     * @param dataBaseInfoQuery
     * @return
     */
    Object deleteDataBaseInfoServer(DataBaseInfoQuery dataBaseInfoQuery);

}
