package com.wugui.datax.admin.datashare.service;


import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import com.wugui.datax.admin.datashare.vo.ResourceParam;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

public interface ApplyResourceServie {
    /**
     * 条件分页查询资源列表
     * @param applyResourceQuery
     * @return
     */
    Object getResourceList(ApplyResourceQuery applyResourceQuery);

    /**
     * 提交申请信息包括申请文件
     * @param file
     * @param request
     * @return
     */
    Object applyAnduploadFile(MultipartFile file, HttpServletRequest request, ResourceParam resourceParam);

    /**
     * 条件分页查询申请的资源
     * @param applyResourceQuery
     * @return
     */
    Object getMyApplyResource(ApplyResourceQuery applyResourceQuery);

}
