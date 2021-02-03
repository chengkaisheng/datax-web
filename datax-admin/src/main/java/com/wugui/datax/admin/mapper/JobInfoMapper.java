package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.dto.QualityConfDto;
import com.wugui.datax.admin.entity.JobInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * job info
 *
 * @author xuxueli 2016-1-12 18:03:45
 */
@Mapper
public interface JobInfoMapper{

    List<JobInfo> pageList(@Param("offset") int offset,
                           @Param("pagesize") int pagesize,
                           @Param("jobGroup") int jobGroup,
                           @Param("triggerStatus") int triggerStatus,
                           @Param("jobDesc") String jobDesc,
                           @Param("glueType") String glueType,
                           @Param("userId") int userId,
                           @Param("projectIds") Integer[] projectIds);

    int pageListCount(@Param("offset") int offset,
                      @Param("pagesize") int pagesize,
                      @Param("jobGroup") int jobGroup,
                      @Param("triggerStatus") int triggerStatus,
                      @Param("jobDesc") String jobDesc,
                      @Param("glueType") String glueType,
                      @Param("userId") int userId,
                      @Param("projectIds") Integer[] projectIds);

    List<JobInfo> findAll();

    int save(JobInfo info);

    JobInfo loadById(@Param("id") int id);

    int update(JobInfo jobInfo);

    int delete(@Param("id") long id);

    List<JobInfo> getJobsByGroup(@Param("jobGroup") int jobGroup);

    int findAllCount();

    List<JobInfo> scheduleJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize);

    List<JobInfo> scheduleVirtualJobQuery(@Param("maxNextTime") long maxNextTime, @Param("pagesize") int pagesize);

    int scheduleUpdate(JobInfo xxlJobInfo);

    int incrementTimeUpdate(@Param("id") int id, @Param("incStartTime") Date incStartTime);

	public int updateLastHandleCode(@Param("id") int id,@Param("lastHandleCode")int lastHandleCode);

    void incrementIdUpdate(@Param("id") int id, @Param("incStartId")Long incStartId);

    List<QualityConfDto> pageConfList(@Param("offset") int offset, @Param("pagesize")int pagesize, @Param("name") String name, @Param("jobType") String jobType);

    int selectConfCount(@Param("offset") int offset, @Param("pagesize") int pagesize, @Param("name") String name, @Param("jobType") String jobType);

    Integer getConfigedRuleCount(@Param("userId") Integer userId);

    List<Map<String,Object>> getTaskTypeDistribution(@Param("userId") Integer userId);
}
