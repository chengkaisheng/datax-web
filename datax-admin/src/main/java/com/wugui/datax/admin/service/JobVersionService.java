package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobVersion;

import java.util.List;

/**
 * @author hf
 * @creat 2021-02-02-15:04
 */
public interface JobVersionService extends IService<JobVersion> {

    List<JobVersion> pageList(int jobId);

    String saveVersion(JobInfo jobInfo);

    ReturnT<String> rollBackVersion(JobInfo jobInfo);


}
