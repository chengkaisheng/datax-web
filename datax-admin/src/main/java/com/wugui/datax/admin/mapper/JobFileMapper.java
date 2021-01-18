package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobFile;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface JobFileMapper {
    int save(JobFile jobFile);

    JobFile selectByProjectIdAndFileName(@Param("projectId") int projectId,@Param("jobFileName") String jobFileName);
}
