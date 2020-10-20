package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DataBaseInfoQuery {
    @ApiModelProperty(value ="所在服务器名称",name ="dataServerName")
    private  String dataServerName;

    @ApiModelProperty(value ="数据库驱动程序",name ="databaseDriver")
    private  String databaseDriver;

    @ApiModelProperty(value ="数据源地址",name ="databaseUrl")
    private  String databaseUrl;

    @ApiModelProperty(value ="用户名",name ="userName")
    private  String userName;

    @ApiModelProperty(value ="密码",name ="password")
    private  String password;

    @ApiModelProperty(value ="创建时间",name ="createTime")
    private  String createTime;

    @ApiModelProperty(value ="修改时间",name ="updateTime")
    private  String updateTime;

    @ApiModelProperty(value ="数据源",name ="dataSource")
    private  String dataSource;

    @ApiModelProperty(value ="备注",name ="remarks")
    private  String remarks;

    @ApiModelProperty(value = "当前页",name ="pageNum", required=true)
    private Integer pageNum;//当前页

    @ApiModelProperty(value = "分页条数",name ="pageSize",required = true)
    private Integer pageSize;//分页条数
}
