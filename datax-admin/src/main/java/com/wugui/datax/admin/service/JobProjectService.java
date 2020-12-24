package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobProject;

import java.io.Serializable;
import java.util.List;

/**
 * Job project
 *
 * @author jingwk
 * @version v2.1.2
 * @since 2020-05-24
 */
public interface JobProjectService extends IService<JobProject> {

    /**
     * project page
     * @param pageSize
     * @param pageNo
     * @param searchName
     * @return
     */

    IPage<JobProject> getProjectListPaging(Integer pageSize, Integer pageNo, String searchName);

    /**
     * project page
     * @param jobProject
     * @return
     */
    Boolean addUser(JobProject jobProject);


    JobProject getProject(Serializable id);

    List<Integer> getProjectUserIds(Long projectId);
}