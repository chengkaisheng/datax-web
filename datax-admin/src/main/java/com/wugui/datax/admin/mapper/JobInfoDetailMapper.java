package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.JobInfoDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface JobInfoDetailMapper {
    int save(JobInfoDetail info);

    JobInfoDetail loadById(@Param("jobInfoId") String id);

    int update(JobInfoDetail jobInfo);

    int delete(@Param("jobInfoId") String id);

    List<JobInfoDetail> findAll(@Param("projectId") int projectId,@Param("jobInfoId") String jobInfoId);

    List<JobInfoDetail> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize);

    int scheduleUpdate(JobInfoDetail jobInfoDetail);
}
