package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobProject;
import com.wugui.datax.admin.entity.JobProjectUserEntity;
import com.wugui.datax.admin.mapper.JobProjectMapper;
import com.wugui.datax.admin.service.JobProjectService;
import com.wugui.datax.admin.service.JobProjectUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * JobProjectServiceImpl
 * @author jingwk
 * @since 2019-05-30
 * @version v2.1.2
 */
@Service("jobProjectService")
public class JobProjectServiceImpl extends ServiceImpl<JobProjectMapper, JobProject> implements JobProjectService {

    @Autowired
    private JobProjectMapper jobProjectMapper;

    @Autowired
    private JobProjectUserService jobProjectUserService;

    @Override
    public IPage<JobProject> getProjectListPaging(Integer pageSize, Integer pageNo, String searchName) {
        Page<JobProject> page = new Page(pageNo, pageSize);
        return jobProjectMapper.getProjectListPaging(page, searchName);
    }

    @Override
    public Boolean addUser(JobProject jobProject) {
        return jobProjectUserService.addUser(jobProject);
    }


}