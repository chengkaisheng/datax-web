package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobRule;
import com.wugui.datax.admin.mapper.JobRuleMapper;
import com.wugui.datax.admin.service.JobRuleService;
import org.springframework.stereotype.Service;



@Service("jobRuleService")
public class JobRuleServiceImpl extends ServiceImpl<JobRuleMapper, JobRule> implements JobRuleService {


}