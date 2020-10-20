package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 数据目录查询条件参数类
 */

@Data
public class DataCatalogVo {
    @ApiModelProperty(value = "组织部门名称",name ="organizeName" )
    private String organizeName;

    @ApiModelProperty(value = "信息资源名称",name ="infoName" )
    private String infoName;//信息资源名称

    @ApiModelProperty(value = "所属系统名称",name ="infoSystemName")
    private String infoSystemName;//所属系统名称

    @ApiModelProperty(value = "数据存储服务器名称",name ="dataServerName")
    private String dataServerName;

    @ApiModelProperty(value = "所属系统id",name ="systemId" )
    private String systemId;//所属系统id

    @ApiModelProperty(value = "目录状态",name ="catalogState" )
    private String catalogState;//目录状态

    @ApiModelProperty(value = "归集状态",name ="isNotionalPool" )
    private String isNotionalPool;//归集状态

    @ApiModelProperty(value = "开放属性",name ="openAttribute" )
    private String openAttribute;//开放属性

    @ApiModelProperty(value = "当前页",name ="pageNum", required=true)
    private Integer pageNum;//当前页

    @ApiModelProperty(value = "分页条数",name ="pageSize",required = true)
    private Integer pageSize;//分页条数



}
