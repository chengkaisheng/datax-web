package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobInfoFile;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface JobInfoFileMapper {
    int save(JobInfoFile jobInfoFile);
}
