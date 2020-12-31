package com.wugui.datax.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wugui.datax.admin.entity.JobUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hf
 * @creat 2020-12-14-18:47
 */
@Data
public class JobProjectVo {
    @ApiModelProperty("项目Id")
    private int id;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目描述")
    private String description;

    @ApiModelProperty("用户Id")
    private int userId;

    @ApiModelProperty("标记")
    private Boolean flag;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("更新时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @TableField(exist=false)
    @ApiModelProperty("用户名")
    private String userName;

    @TableField(exist=false)
    @ApiModelProperty("创建or参与")
    private String note;
}
