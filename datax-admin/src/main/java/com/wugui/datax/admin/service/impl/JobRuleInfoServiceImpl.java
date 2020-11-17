package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobRuleInfo;
import com.wugui.datax.admin.mapper.JobRuleInfoMapper;
import com.wugui.datax.admin.service.JobRuleInfoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("jobRuleInfoService")
public class JobRuleInfoServiceImpl extends ServiceImpl<JobRuleInfoMapper, JobRuleInfo> implements JobRuleInfoService {


    private JobRuleInfoMapper jobRuleInfoMapper;

    @Override
    public Map<String, Object> selectByJobRuleId(Integer id) {
        Map<String,Object> map = new HashMap<>();

        List<JobRuleInfo> list = jobRuleInfoMapper.selectByJobRuleId(id);
        map.put("data",list);

        return map;
    }
}