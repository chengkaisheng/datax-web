package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ApplyResourceQuery {
    @ApiModelProperty(value = "token",name ="token")
    private String token;

    @ApiModelProperty(value = "信息资源名称",name ="infoName")
    private String infoName;

    @ApiModelProperty(value = "信息资源接口名称",name ="interName")
    private String interName;

    @ApiModelProperty(value = "部门名称",name ="dataCompany")
    private String dataCompany;

    @ApiModelProperty(value = "当前页",name ="pageNum", required=true)
    private Integer pageNum;//当前页

    @ApiModelProperty(value = "分页条数",name ="pageSize",required = true)
    private Integer pageSize;//分页条数

}
