package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyParams {

    @ApiModelProperty(value ="信息资源id",name ="infoId")
    private String infoId;

    @ApiModelProperty(value ="信息资源名称",name ="infoName")
    private String infoName;

    @ApiModelProperty(value ="申请类型",name ="applyType")
    private String applyType;

    @ApiModelProperty(value ="表英文名称",name ="tableEnglish")
    private String tableEnglish;

    @ApiModelProperty(value ="数源单位",name ="dataCompany")
    private String dataCompany;

  /*  @ApiModelProperty(value ="联系人",name ="contacts")
    private String contacts;

    @ApiModelProperty(value ="联系电话",name ="telephone")
    private String telephone;

    @ApiModelProperty(value ="联系邮箱",name ="email")
    private String email;*/

    private String catalogState;

}
