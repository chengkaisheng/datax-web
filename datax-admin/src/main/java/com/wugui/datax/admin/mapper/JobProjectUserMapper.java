package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.JobProjectUserEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hf
 * @creat 2020-12-14-17:29
 */
@Mapper
public interface JobProjectUserMapper extends BaseMapper<JobProjectUserEntity> {
    void deleteBatch(Integer[] integers);
}
