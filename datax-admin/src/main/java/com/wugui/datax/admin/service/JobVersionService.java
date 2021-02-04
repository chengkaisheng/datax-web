package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobVersion;

import java.util.List;

/**
 * @author hf
 * @creat 2021-02-02-15:04
 */
public interface JobVersionService extends IService<JobVersion> {

    List<JobVersion> pageList(int jobId);
}
