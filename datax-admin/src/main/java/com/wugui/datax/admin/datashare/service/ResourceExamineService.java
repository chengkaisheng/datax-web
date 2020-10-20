package com.wugui.datax.admin.datashare.service;


import com.wugui.datax.admin.datashare.vo.ResourceExamParam;
import com.wugui.datax.admin.datashare.vo.ResourceExamQuery;

public interface ResourceExamineService {

    /**
     * 条件分页查询资源审核信息
     * @param query
     * @return
     */
    Object getTResourceExaminePages(ResourceExamQuery query);

    /**
     * 审批资源申请
     * @param param
     * @return
     */
    Object approveResource(ResourceExamParam param);

    /**
     * 查看审批详情
     * @return
     */
    Object getResourceExamineByResId(String resId);

}
