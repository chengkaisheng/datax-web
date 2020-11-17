package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobRule;
import com.wugui.datax.admin.mapper.JobRuleMapper;
import com.wugui.datax.admin.service.JobRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("jobRuleService")
public class JobRuleServiceImpl extends ServiceImpl<JobRuleMapper, JobRule> implements JobRuleService {

    @Resource
    private JobRuleMapper jobRuleMapper;

    @Override
    public Map<String, Object> pageList(int current, int size) {
        Map<String,Object> map = new HashMap();

        List<JobRule> list = jobRuleMapper.pageList((current-1) * size,size);
        int count = jobRuleMapper.pageListCount();

        map.put("data",list);
        map.put("count",count);
        return map;
    }

    @Override
    public Map<String, Object> getJobRuleInfo(Integer id) {
        Map<String,Object> map = new HashMap<>();

        JobRule jobRule = jobRuleMapper.selectById(id);
        map.put("data",jobRule);
        return map;
    }
}