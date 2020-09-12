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
     * @param request
     */
    void add(UniversalRule universalRule, HttpServletRequest request);
}

