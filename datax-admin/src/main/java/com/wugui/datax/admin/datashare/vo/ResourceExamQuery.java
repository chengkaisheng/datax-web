package com.wugui.datax.admin.datashare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class ResourceExamQuery {
    @ApiModelProperty(value = "部门名称",name ="containName")
    private String containName;

    @ApiModelProperty(value = "信息资源名称",name ="infoName")
    private String infoName;

    @ApiModelProperty(value = "状态",name ="state")
    private String state;

    @ApiModelProperty(value = "时间搜索范围开始时间",name ="endTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date startTime;

    @ApiModelProperty(value = "时间搜索范围结束时间",name ="endTime")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "当前页",name ="pageNum", required=true)
    private Integer pageNum;//当前页

    @ApiModelProperty(value = "分页条数",name ="pageSize",required = true)
    private Integer pageSize;//分页条数

}
