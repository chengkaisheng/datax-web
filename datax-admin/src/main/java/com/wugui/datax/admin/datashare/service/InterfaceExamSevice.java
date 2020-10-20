package com.wugui.datax.admin.datashare.service;


import com.wugui.datax.admin.datashare.vo.InterfaceExamParam;
import com.wugui.datax.admin.datashare.vo.InterfaceQuery;

public interface InterfaceExamSevice {

    /**
     * 查询审核列表
     * @param query
     * @return
     */
    Object getInterfaceExamPage(InterfaceQuery query);


    /**
     * 审批接口
     * @param param
     * @return
     */
    Object approvalInterface(InterfaceExamParam param);

    /**
     * 查看审批详情
     * @param id
     * @return
     */
    Object getInterfaceExamById(String id);

}
