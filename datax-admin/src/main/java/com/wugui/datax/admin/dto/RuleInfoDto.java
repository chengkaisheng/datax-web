package com.wugui.datax.admin.dto;

import lombok.Data;

import java.util.List;

@Data
public class RuleInfoDto {

    private String columnName;

    private Integer id;

    private String status;

    private List<RuleIdInfoDto> ruleId;
}
