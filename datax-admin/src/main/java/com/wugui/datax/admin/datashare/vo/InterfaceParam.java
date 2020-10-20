package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class InterfaceParam {
    @ApiModelProperty(value = "联系人",name ="contacts" )
    private String contacts;

    @ApiModelProperty(value = "数据源",name ="serverName" )
    private String serverName;

    @ApiModelProperty(value = "数据源Id",name ="id" )
    private String id;

    @ApiModelProperty(value = "联系电话",name ="telephone" )
    private String telephone;

    @ApiModelProperty(value = "注册人",name ="registerCompany" )
    private String registerCompany;

    @ApiModelProperty(value = "接口名称",name ="interName" )
    private String interName;

    @ApiModelProperty(value = "接口描述",name ="interRemark" )
    private String interRemark;

   /* @ApiModelProperty(value = "数据范围",name ="dataRange" )
    private String dataRange;*/

  /*  @ApiModelProperty(value = "数源部门",name ="dataCompany" )
    private String dataCompany;*/

    /*@ApiModelProperty(value = "实现方式",name ="implMethod" )
    private String implMethod;*/

    /*@ApiModelProperty(value = "业务类型",name ="businessType" )
    private String businessType;*/

    @ApiModelProperty(value = "返回数据格式",name ="responseMode" )
    private String responseMode;

   /* @ApiModelProperty(value = "部署方式",name ="deployMethod" )
    private String deployMethod;*/

   /* @ApiModelProperty(value = "实名认证",name ="realName" )
    private String realName;
*/
   /* @ApiModelProperty(value = "是否受限",name ="isLimit" )
    private String isLimit;*/

   /* @ApiModelProperty(value = "提供服务",name ="provideService" )
    private String provideService;
*/
    @ApiModelProperty(value = "infoId",name ="infoId" )
    private String infoId;

    @ApiModelProperty(value = "表名称",name ="tableEnglish" )
    private String tableEnglish;

    @ApiModelProperty(value = "输入参数",name ="inputParams" )
    private List<InputParam> inputParams;

    @ApiModelProperty(value = "输出参数",name ="outputParams" )
    private List<OutputParam> outputParams;

}
