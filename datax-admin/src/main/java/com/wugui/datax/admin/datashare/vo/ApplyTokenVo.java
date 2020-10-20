package com.wugui.datax.admin.datashare.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ApplyTokenVo {
    @ApiModelProperty(value ="token",name ="token")
    private String token;

    @ApiModelProperty(value ="申请提交参数",name ="applyParams")
    private List<ApplyParams> applyParams;
}
