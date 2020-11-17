package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobRuleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-16 16:38:34
 */
@Mapper
public interface JobRuleInfoMapper extends BaseMapper<JobRuleInfo> {

    List<JobRuleInfo> selectByJobRuleId(@Param("id") Integer id);

}
