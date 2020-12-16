package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface JobRoleMapper extends BaseMapper<JobRole> {
    List<Long> queryRoleIdList(Long createUserId);
}
