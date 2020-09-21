package com.wugui.datax.admin.service.impl;
import com.wugui.datax.admin.entity.UniversalRule;
import com.wugui.datax.admin.mapper.UniversalRuleMapper;
import com.wugui.datax.admin.service.UniversalRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("universalRuleService")
public class UniversalRuleServiceImpl  implements UniversalRuleService {

    private static Logger logger = LoggerFactory.getLogger(UniversalRuleServiceImpl.class);

    @Resource
    private UniversalRuleMapper universalRuleMapper;

    @Override
    public Map<String, Object> getUniversalList(int current, int size,String name) {

        List<UniversalRule> list = universalRuleMapper.pageList((current-1) * size,size,name);
        int count = universalRuleMapper.pageListCount(name);

        Map<String,Object> map = new HashMap<>();
        map.put("count",count);
        map.put("data",list);
        return map;
    }

    @Override
    public void add(UniversalRule universalRule){
        universalRule.setIsDelete(0);
        universalRule.setCreateTime(new Date());
        universalRuleMapper.insert(universalRule);

    }

    @Override
    public void delete(Integer id) {
        universalRuleMapper.updateIsDelete(id);
    }

    @Override
    public void update(UniversalRule universalRule) {
        universalRuleMapper.updateById(universalRule);
    }

    @Override
    public Map<String, Object> info(Integer id) {
        Map<String,Object> map = new HashMap<>();
        UniversalRule universalRule = universalRuleMapper.selectById(id);
        map.put("data",universalRule);
        return map;
    }

    @Override
    public Map<String, Object> getUniversalNameByType(Integer type) {
        Map<String,Object> map = new HashMap<>();
        List<UniversalRule> list = universalRuleMapper.selectUniversalNameByType(type);
        map.put("data",list);
        return map;
    }

    @Override
    public Map<String, Object> getUniverToPerson() {
        Map<String,Object> map = new HashMap<>();
        //先根据规则大类找到对应的通用规则

        List<UniversalRule> list = universalRuleMapper.selectUniverToPerson();
        map.put("data",list);
        return map;
    }

    @Override
    public Map<String,Object> check(String code) {
        int count = universalRuleMapper.selectCountByCode(code);
        Map<String,Object> map = new HashMap<>();
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