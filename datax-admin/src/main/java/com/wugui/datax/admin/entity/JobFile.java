package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author ：lxq
 * @description：任务文件夹分类
 * @date ：2021/1/11 14:16
 */
@Data
public class JobFile {
    @ApiModelProperty("任务文件夹分类Id")
    private int jobFileId;

    @ApiModelProperty("文件夹名称")
    private String jobFileName;

    @ApiModelProperty("文件夹保存路径")
    private String jobFilePath;

    @ApiModelProperty("项目Id")
    private int projectId;

    @ApiModelProperty("创建人Id")
    private int  createId;

    @ApiModelProperty("创建时间")
    private Date createTime;
}
