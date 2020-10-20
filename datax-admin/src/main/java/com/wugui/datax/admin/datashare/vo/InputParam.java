package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InputParam {

    @ApiModelProperty(value = "字段名称",name ="columnName" )
    private String columnName;
    @ApiModelProperty(value = "字段编码",name ="columnCode" )
    private String columnCode;
    @ApiModelProperty(value = "字段描述",name ="columnRemark" )
    private String columnRemark;
}
