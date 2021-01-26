package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wugui.datax.admin.entity.JobProject;
import com.wugui.datax.admin.entity.vo.JobProjectVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Project
 *
 * @author jingwk
 * @version v2.1.12
 * @since 2020-05-24
 */
@Mapper
public interface JobProjectMapper extends BaseMapper<JobProject> {
    /**
     * project page
     * @param page
     * @param searchName
     * @return
     */
    IPage<JobProject> getProjectListPaging(IPage<JobProject> page,
                                          @Param("searchName") String searchName);

    IPage<JobProjectVo> getProjectListPagingByUserId(IPage<JobProjectVo> page,
                                           @Param("searchName") String searchName,@Param("userId") int id);

    Integer queryDataSourceCountByProject(@Param("userId") Integer userId);

    Integer queryUserCountByProject(@Param("userId") Integer userId);

    Integer queryTaskCountByProject(@Param("userId") Integer userId);

    List<Map<String,Object>> getItemTaskDistribution(@Param("userId") Integer userId);

    List<Map<String,Object>> getItemTaskRunStateDistribution(@Param("userId") Integer userId);

    List<Map<String,Object>> getItemTaskTypeDistribution(@Param("userId") Integer userId);

    List<Map<String,Object>> getTaskExecutorDistribution(@Param("userId") Integer userId);

    Integer count(@Param("userId") Integer userId);

    List<Integer> projectIds(@Param("userId") Integer userId);
}
