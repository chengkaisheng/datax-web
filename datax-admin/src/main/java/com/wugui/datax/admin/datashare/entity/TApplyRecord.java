package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TApplyRecord {
    @ApiModelProperty(value ="申请id",name ="id")
    private String id;

    @ApiModelProperty(value ="申请用户id",name ="userId")
    private String userId;

    @ApiModelProperty(value ="申请部门",name ="departmentName")
    private String departmentName;

    @ApiModelProperty(value ="申请类型",name ="applyType")
    private String applyType;

    @ApiModelProperty(value ="信息资源id",name ="infoId")
    private String infoId;

    @ApiModelProperty(value ="信息资源名称",name ="infoName")
    private String infoName;

    @ApiModelProperty(value ="表英文名称",name ="tableEnglish")
    private String tableEnglish;

    @ApiModelProperty(value ="数源单位",name ="dataCompany")
    private String dataCompany;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date applyTime;

    @ApiModelProperty(value ="目录状态",name ="catalogState")
    private String catalogState;

    @ApiModelProperty(value ="联系人",name ="contacts")
    private String contacts;

    @ApiModelProperty(value ="联系电话",name ="telephone")
    private String telephone;

    @ApiModelProperty(value ="联系邮箱",name ="email")
    private String email;

    @ApiModelProperty(value ="审核描述",name ="exaDescribe")
    private String exaDescribe;

    @ApiModelProperty(value ="数据库连接地址",name ="databaseIp")
    private String databaseIp;

    @ApiModelProperty(value ="数据库用户名",name ="loginName")
    private String loginName;

    @ApiModelProperty(value ="数据库连接密码",name ="password")
    private String password;

    @ApiModelProperty(value ="端口号",name ="port")
    private String port;

    @ApiModelProperty(value ="数据库名",name ="databaseName")
    private String databaseName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

}