package com.wugui.datax.admin.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 任务规则关系表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-13 18:14:52
 */
@Mapper
public interface JobRuleMapper extends BaseMapper<JobRule> {

    List<JobRule> pageList(@Param("offset") int offset, @Param("pageSize") int pageSize);

    int pageListCount();
}
