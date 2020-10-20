package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InterfaceExamParam {

    @ApiModelProperty(value ="token",name ="token")
    private String token;

    @ApiModelProperty(value ="审核人",name ="applyUserName")
    private String applyUserName;

    @ApiModelProperty(value ="接口id",name ="interId")
    private String interId;

    @ApiModelProperty(value ="审核结果",name ="exaResult")
    private String exaResult;

    @ApiModelProperty(value ="审核描述",name ="exaDescribe")
    private String exaDescribe;
}
