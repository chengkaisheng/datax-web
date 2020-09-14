package com.wugui.datax.admin.service;

import com.wugui.datax.admin.entity.UniversalRule;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 通用规则表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
public interface UniversalRuleService{
    /**
     * 获取通用规则列表
     * @param current
     * @param size
     * @return
     */
    Map<String, Object> getUniversalList(int current, int size, String name);

    /**
     * 添加通用规则
     * @param universalRule
     */
    void add(UniversalRule universalRule);

    /**
     * 删除通用规则
     * @param id
     */
    void delete(Integer id);

    /**
     * 修改通用规则
     * @param universalRule
     */
    void update(UniversalRule universalRule);

    /**
     * 通用规则详情
     * @param id
     * @return
     */
    Map<String, Object> info(Integer id);

    /**
     * 根据规则类别获取规则名称
     * @param type
     * @return
     */
    Map<String, Object> getUniversalNameByType(Integer type);
}

