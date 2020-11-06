package com.wugui.datax.admin.mapper;


import com.wugui.datax.admin.entity.JobInfoLink;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobInfoLinkMapper {
    int save(JobInfoLink info);

    JobInfoLink loadById(@Param("id") int id);

    JobInfoLink loadByJobInfoId (@Param("jobInfoId") String id,@Param("jobId") int jobId);

    int update(JobInfoLink jobInfo);

    int updateByIdAndJobInfoId(JobInfoLink jobInfo);

    int delete(@Param("id") long id);

    List<JobInfoLink> loadTriggerVirtualTask(@Param("jobInfoId") String jobInfoId);
}
