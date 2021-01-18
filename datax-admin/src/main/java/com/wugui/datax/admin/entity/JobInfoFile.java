package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/1/11 15:08
 */
@Data
public class JobInfoFile {
    @ApiModelProperty("主键Id")
    private int id;

    @ApiModelProperty("任务文件夹分类Id")
    private int jobFileId;

    @ApiModelProperty("任务Id")
    private int jobId;
}
