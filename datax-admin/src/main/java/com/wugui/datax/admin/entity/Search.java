package com.wugui.datax.admin.entity;

import lombok.Data;

/**
 * Created by iwlnner on 2020/9/12.
 */
@Data
public class Search {

    private Long id;
    private String taskName;
    private String tableName;
    private String intro;
    private String description;
    private String imageUrl;
    private Long jdbcDatasourceId;
    private Long rows;
    private String size;
    private Integer cols;
}
