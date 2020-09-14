package com.wugui.datax.admin.service;


import com.wugui.datax.admin.entity.PersonaliseRule;

import java.util.Map;

/**
 * 个性化规则表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
public interface PersonaliseRuleService{

    /**
     * 个性化规则列表接口
     * @param current
     * @param size
     * @param name
     * @param type
     * @return
     */
    Map<String, Object> getPersonaliseList(int current, int size, String name, Integer type,Integer joinType);

    /**
     * 添加个性化规则
     * @param personaliseRule
     */
    void add(PersonaliseRule personaliseRule);

    /**
     * 详情
     * @param id
     * @return
     */
    Map<String, Object> info(Integer id);

    /**
     * 更新个性化规则
     * @param personaliseRule
     */
    void update(PersonaliseRule personaliseRule);

    /**
     * 删除个性化规则
     * @param id
     */
    void delete(Integer id);
}

