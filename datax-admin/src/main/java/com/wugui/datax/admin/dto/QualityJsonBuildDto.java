package com.wugui.datax.admin.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class QualityJsonBuildDto extends DataXJsonBuildDto implements Serializable {

    private List<RuleInfoDto> rule;
}
