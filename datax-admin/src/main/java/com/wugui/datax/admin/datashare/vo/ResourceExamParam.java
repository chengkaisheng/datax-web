package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourceExamParam {
    @ApiModelProperty(value ="token",name ="token")
    private String token;

    @ApiModelProperty(value ="资源申请id",name ="resId")
    private String resId;

    @ApiModelProperty(value ="审核结果",name ="exaResult")
    private String exaResult;

    @ApiModelProperty(value ="审核描述",name ="exaDescribe")
    private String exaDescribe;

}
