package com.wugui.datax.admin.service;


import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.core.trigger.TriggerTypeEnum;
import com.wugui.datax.admin.dto.DataXBatchJsonBuildDto;
import com.wugui.datax.admin.dto.TaskScheduleDto;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobInfoDetail;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * core job action for datax-web
 *
 * @author xuxueli 2016-5-28 15:30:33
 */
public interface JobService {

    /**
     * page list
     *
     * @param start
     * @param length
     * @param jobGroup
     * @param jobDesc
     * @param glueType
     * @param userId
     * @return
     */
    Map<String, Object> pageList(int start, int length, int jobGroup, int triggerStatus, String jobDesc, String glueType, int userId,Integer[] projectIds);

    List<JobInfo> list();

    /**
     * add job
     *
     * @param jobInfo
     * @return
     */
    ReturnT<String> add(JobInfo jobInfo);

    /**
     * update job
     *
     * @param jobInfo
     * @return
     */
    ReturnT<String> update(JobInfo jobInfo);

    /**
     * remove job
     * *
     *
     * @param id
     * @return
     */
    ReturnT<String> remove(int id);

    /**
     * start job
     *
     * @param id
     * @return
     */
    ReturnT<String> start(int id);

    /**
     * stop job
     *
     * @param id
     * @return
     */
    ReturnT<String> stop(int id);

    /**
     * dashboard info
     *
     * @return
     */
    Map<String, Object> dashboardInfo();

    /**
     * chart info
     *
     * @return
     */
    ReturnT<Map<String, Object>> chartInfo();

    /**
     * batch add
     * @param dto
     * @return
     */
    ReturnT<String> batchAdd(DataXBatchJsonBuildDto dto) throws IOException;

    /**
     * triggerJob
     * @param jobId
     * @param triggerType
     * @param failRetryCount
     * @param executorShardingParam
     * @param executorParam
     * @return
     * @throws IOException
     */
    ReturnT<String> triggerJob(int jobId, TriggerTypeEnum triggerType, int failRetryCount, String executorShardingParam, String executorParam) throws IOException;

    /**
     * 添加虚任务
     * @param jobInfoDetail
     * @return
     */
    ReturnT<String> addVirtualTask(JobInfoDetail jobInfoDetail);

    /**
     * 更新虚任务
     * @param jobInfoDetail
     * @return
     */
    ReturnT<String> updateVirtualTask(JobInfoDetail jobInfoDetail);

    /**
     * 获取虚任务列表
     * @return
     */
    List<JobInfoDetail> listVirtualTask(int projectId,String jobInfoId);

    /**
     * 触发虚任务
     * @param jobInfoDetail
     * @return
     */
    ReturnT<String> triggerVirtualTask(JobInfoDetail jobInfoDetail);
}
