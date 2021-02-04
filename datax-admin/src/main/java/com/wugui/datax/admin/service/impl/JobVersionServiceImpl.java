package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobVersion;
import com.wugui.datax.admin.mapper.JobVersionMapper;
import com.wugui.datax.admin.service.JobVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hf
 * @creat 2021-02-02-15:04
 */
@Service
public class JobVersionServiceImpl extends ServiceImpl<JobVersionMapper, JobVersion> implements JobVersionService {
    @Autowired
    private JobVersionMapper jobVersionMapper;
    @Override
    public List<JobVersion> pageList(int jobId) {
        QueryWrapper<JobVersion> queryWrapper=new QueryWrapper<>();
        List<JobVersion> jobVersionList=this.list(queryWrapper.eq("job_id",jobId));
        return jobVersionList;
    }
}
