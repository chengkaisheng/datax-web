package com.wugui.datax.admin.service.impl;

import com.wugui.datax.admin.entity.PersonaliseRule;
import com.wugui.datax.admin.mapper.PersonaliseRuleMapper;
import com.wugui.datax.admin.service.PersonaliseRuleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("personaliseRuleService")
public class PersonaliseRuleServiceImpl implements PersonaliseRuleService {

    @Resource
    private PersonaliseRuleMapper personaliseRuleMapper;

    @Override
    public Map<String, Object> getPersonaliseList(int current, int size, String name, Integer type,Integer joinType) {
        List<PersonaliseRule> list = personaliseRuleMapper.pageList((current-1) * size,size,type,name,joinType);
        int count = personaliseRuleMapper.pageListCount(name,type,joinType);

        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",list);

        return map;
    }

    @Override
    public void add(PersonaliseRule personaliseRule) {
        personaliseRule.setIsDelete(0);
        personaliseRuleMapper.insert(personaliseRule);
    }

    @Override
    public Map<String, Object> info(Integer id) {
        PersonaliseRule personaliseRule = personaliseRuleMapper.selectById(id);

        Map<String,Object> map = new HashMap<>();
        map.put("data",personaliseRule);
        return map;
    }

    @Override
    public void update(PersonaliseRule personaliseRule) {
        personaliseRuleMapper.updateById(personaliseRule);
    }

    @Override
    public void delete(Integer id) {
        personaliseRuleMapper.updateIsDelete(id);
    }

    @Override
    public Map<String,Object> check(String code) {
        Map<String,Object> map = new HashMap<>();
        int count = personaliseRuleMapper.selectCountByCode(code);
        if(count > 0){
            map.put("code",500);
            map.put("msg","编码已存在");
        }else {
            map.put("code",200);
            map.put("msg","编码可以使用");
        }
        return map;
    }
}