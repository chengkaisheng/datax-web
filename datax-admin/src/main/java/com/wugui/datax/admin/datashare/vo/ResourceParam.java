package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ResourceParam {

    @ApiModelProperty(value = "token",name ="token")
    private String token;

    @ApiModelProperty(value = "接口id",name ="interId")
    private String interId;

    @ApiModelProperty(value = "信息资源id",name ="infoId")
    private String infoId;

    @ApiModelProperty(value = "信息资源名称",name ="infoName")
    private String infoName;

    @ApiModelProperty(value = "信息资源摘要",name ="infoExtract")
    private String infoExtract;

    @ApiModelProperty(value = "联系人",name ="contacts")
    private String contacts;

    @ApiModelProperty(value = "手机号",name ="telephone")
    private String telephone;

    @ApiModelProperty(value = "接口类型",name ="resType")
    private String resType;

    @ApiModelProperty(value = "应用场景",name ="useScene")
    private String useScene;

    @ApiModelProperty(value = "接口所属部门",name ="department")
    private String department;

}
