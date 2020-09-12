package com.wugui.datax.admin.service.impl;
import com.wugui.datax.admin.entity.UniversalRule;
import com.wugui.datax.admin.mapper.UniversalRuleMapper;
import com.wugui.datax.admin.service.UniversalRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    public void add(UniversalRule universalRuleEntity, HttpServletRequest request){

        /*//获取当前登录用户
        try {
            LoginUser loginUser = new ObjectMapper().readValue(request.getInputStream(), LoginUser.class);
            logger.info(loginUser.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}