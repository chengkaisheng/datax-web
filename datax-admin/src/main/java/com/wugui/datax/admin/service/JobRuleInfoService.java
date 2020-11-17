package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobRuleInfo;


import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-16 16:38:34
 */
public interface JobRuleInfoService extends IService<JobRuleInfo> {

    Map<String, Object> selectByJobRuleId(Integer id);
}

