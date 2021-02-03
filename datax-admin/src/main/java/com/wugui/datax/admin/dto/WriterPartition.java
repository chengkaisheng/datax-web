package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/2/1 18:19
 */
@Data
public class WriterPartition {

    @ApiModelProperty("分区,0--分区,1--非分区")
    private int partition;

    @ApiModelProperty("分区字段")
    private String partitionText;
}
