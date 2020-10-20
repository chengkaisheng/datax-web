package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OutputParam {
    @ApiModelProperty(value = "字段名称",name ="columnName" )
    private String fieldName;
    @ApiModelProperty(value = "字段编码",name ="columnCode" )
    private String fieldCode;
    @ApiModelProperty(value = "字段描述",name ="columnRemark" )
    private String fieldRemark;
}
