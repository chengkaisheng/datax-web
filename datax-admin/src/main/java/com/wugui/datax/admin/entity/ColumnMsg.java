package com.wugui.datax.admin.entity;

import lombok.Data;

import java.util.Map;

/**
 * Created by iwlnner on 2020/9/17.
 */
@Data
public class ColumnMsg {

    private String name;
    private String comment;
    private String type;
    private Map<String,Map<String,Object>> indicator;
    private Object statistics;
}
