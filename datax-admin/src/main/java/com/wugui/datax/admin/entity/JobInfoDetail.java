package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JobInfoDetail {
    @ApiModelProperty("任务ID")
    private String jobInfoId;

    @ApiModelProperty("任务名称")
    private String jobInfoName;

    @ApiModelProperty("任务Cron表达式")
    private String jobCron;

    @ApiModelProperty("流程图信息")
    private String flowChatInformation;

    @ApiModelProperty("下次调度时间")
    private long triggerNextTime;

    @ApiModelProperty("所属项目Id")
    private int projectId;
}
