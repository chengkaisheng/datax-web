package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobProject;
import com.wugui.datax.admin.entity.JobProjectUserEntity;

import java.io.Serializable;
import java.util.List;

/**
 * @author hf
 * @creat 2020-12-14-17:28
 */
public interface JobProjectUserService extends IService<JobProjectUserEntity> {
    void addUser(JobProject jobProject);
    void deleteBatch(Integer[] integers);
    List<Integer> getUserIds(Serializable projectId);
}
