package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/1/29 14:17
 */
@Data
public class ReaderSync {

    @ApiModelProperty("同步方式,0--每日增量,1--每日全量")
    private int syncType;

    @ApiModelProperty("增量配置模式,0--根据增量字段自动生成,1--根据增量字段自动生成")
    private int incSetting ;

    @ApiModelProperty("增量字段信息,根据日期字段")
    private String incExtract;

    @ApiModelProperty("增量抽取条件")
    private String incExtractText;

}
