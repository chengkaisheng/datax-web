package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobVersion;
import com.wugui.datax.admin.mapper.JobVersionMapper;
import com.wugui.datax.admin.service.JobVersionService;
import org.springframework.stereotype.Service;

/**
 * @author hf
 * @creat 2021-02-02-15:04
 */
@Service
public class JobVersionServiceImpl extends ServiceImpl<JobVersionMapper, JobVersion> implements JobVersionService {
}
