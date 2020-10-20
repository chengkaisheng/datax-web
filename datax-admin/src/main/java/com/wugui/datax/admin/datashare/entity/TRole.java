package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Data
public class TRole {
    @ApiModelProperty(value ="角色主键" ,name = "rid")
    private String rid;

    @ApiModelProperty(value ="角色名称" ,name = "roleName")
    private String roleName;

    @ApiModelProperty(value ="角色描述" ,name = "description")
    private String description;

    @ApiModelProperty(value ="角色标识" ,name = "roleCode")
    private String roleCode;

    @ApiModelProperty(value ="数据权限" ,name = "roleAuth")
    private String roleAuth;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

}