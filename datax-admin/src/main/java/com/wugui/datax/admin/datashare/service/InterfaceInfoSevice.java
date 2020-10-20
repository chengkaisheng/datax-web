package com.wugui.datax.admin.datashare.service;



import com.wugui.datax.admin.datashare.entity.TInterfaceWithBLOBs;
import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import com.wugui.datax.admin.datashare.vo.InterfaceParam;
import com.wugui.datax.admin.datashare.vo.InterfaceQuery;

import javax.servlet.http.HttpServletRequest;

public interface InterfaceInfoSevice {

    /**
     * 条件分页查询接口列表
     * @param query
     * @return
     */
    Object getInterfaceInfoPages(InterfaceQuery query);


    //查看已申请的接口信息
    Object getInterfaceByinfoId(String id);


    /**
     * 注册接口
     * @param param
     * @return
     */
    Object insertInterface(InterfaceParam param, HttpServletRequest request);

    /**
     * 查看接口详情
     * @param id
     * @return
     */
    Object getInterfaceDetails(String id);

    /**
     * 根据接口编码查询接口信息
     * @param interCode
     * @return
     */
    TInterfaceWithBLOBs getInterfaceByCode(String interCode);

    /**
     * 共享模块条件查询接口列表
     * @return
     */
    Object getInterfaceResult(ApplyResourceQuery applyResourceQuery);


}
