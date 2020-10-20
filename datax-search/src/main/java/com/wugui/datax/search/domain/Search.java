package com.wugui.datax.search.domain;

import lombok.Data;

/**
 * Created by iwlnner on 2020/9/12.
 */
@Data
public class Search {

    private Long id;
    private String StrtaskName;
    private String tableName;
    private String intro;
    private String description;
    private String imageUrl;
    private Long jdbcDatasourceId;
}
