package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class InterfaceQuery {
    @ApiModelProperty(value = "注册人",name ="registerCompany" )
    private String registerCompany;

    @ApiModelProperty(value = "接口名称",name ="interName" )
    private String interName;

    @ApiModelProperty(value = "状态",name ="state" )
    private String state;

    @ApiModelProperty(value = "当前页",name ="pageNum", required=true)
    private Integer pageNum;//当前页

    @ApiModelProperty(value = "分页条数",name ="pageSize",required = true)
    private Integer pageSize;//分页条数
}
