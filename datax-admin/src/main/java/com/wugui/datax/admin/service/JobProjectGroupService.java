package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobProjectGroup;

import java.util.List;

/**
 * @author hf
 * @creat 2021-01-29-16:02
 */

public interface JobProjectGroupService extends IService<JobProjectGroup> {
    List<JobProjectGroup> getTree(Integer projectId);

    void deleteBatch(Integer id);

    void paste(JobProjectGroup jobProjectGroup, Integer pid);
}
