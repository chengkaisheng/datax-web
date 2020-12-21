package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: datax-web
 * @description
 * @author: lxq
 * @create: 2020-12-15 15:49
 **/
@Data
public class DimDDL {
    @ApiModelProperty("源数据库")
    private String source;

    @ApiModelProperty("源数据库字段类型")
    private String sourceType;

    @ApiModelProperty("目标数据库")
    private String target;

    @ApiModelProperty("目标数据库子段类型")
    private String targetType;

    @ApiModelProperty("更新时间")
    private String updateTime;
}
