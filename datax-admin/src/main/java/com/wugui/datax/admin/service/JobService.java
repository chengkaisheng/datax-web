package com.wugui.datax.admin.service;


import com.wugui.datatx.core.biz.model.LogResult;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.core.trigger.TriggerTypeEnum;
import com.wugui.datax.admin.dto.DataXBatchJsonBuildDto;
import com.wugui.datax.admin.dto.TaskScheduleDto;
import com.wugui.datax.admin.entity.Dashboard;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobInfoDetail;
import com.wugui.datax.admin.entity.JobLog;

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
     * 查询质量规则的项目
     * @param current
     * @param size
     * @param name
     * @return
     */
    Map<String, Object> getPageConf(int current, int size, String name);

    /**
     * 添加虚任务
     * @param jobInfo
     * @return
     */
    ReturnT<String> addVirtualTask(JobInfo jobInfo);

    /**
     * 更新虚任务
     * @param jobInfo
     * @return
     */
    ReturnT<String> updateVirtualTask(JobInfo jobInfo);

    /**
     * 触发虚任务
     * @param jobInfo
     * @return
     */
    ReturnT<String> triggerVirtualTask(JobInfo jobInfo);

    /**
     * 加载虚任务日志
     * @param log
     * @return
     */
    ReturnT<LogResult> loadingVirtualLog(String log, JobLog jobLog);

    ReturnT<Dashboard> getRunReport() throws IOException;
}
