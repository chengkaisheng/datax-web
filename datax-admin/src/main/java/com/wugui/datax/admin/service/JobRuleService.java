package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobRule;

import java.util.Map;

/**
 * 任务规则关系表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-13 18:14:52
 */
public interface JobRuleService extends IService<JobRule> {

    Map<String, Object> pageList(int current, int size);

    Map<String, Object> getJobRuleInfo(Integer id);
}
