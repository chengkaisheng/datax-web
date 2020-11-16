package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobRuleInfo;
import com.wugui.datax.admin.mapper.JobRuleInfoMapper;
import com.wugui.datax.admin.service.JobRuleInfoService;
import org.springframework.stereotype.Service;



@Service("jobRuleInfoService")
public class JobRuleInfoServiceImpl extends ServiceImpl<JobRuleInfoMapper, JobRuleInfo> implements JobRuleInfoService {


}