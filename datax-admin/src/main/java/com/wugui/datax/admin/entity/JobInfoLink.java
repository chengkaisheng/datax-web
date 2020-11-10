package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JobInfoLink {
    @ApiModelProperty("主键ID")
    private int id;

    @ApiModelProperty("子任务ID")
    private String childJobId;

    @ApiModelProperty("父任务ID")
    private String parentJobId;

    @ApiModelProperty("调度状态")
    private int triggerStatus;

    @ApiModelProperty("任务信息")
    private String jobInfoId;

    @ApiModelProperty("任务主键Id")
    private String infoId;
}
