package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobProjectGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author hf
 * @creat 2021-01-29-15:52
 */
@Mapper
public interface JobProjectGroupMapper extends BaseMapper<JobProjectGroup> {
    List<Integer> getChildrenIds(Integer id);
}
