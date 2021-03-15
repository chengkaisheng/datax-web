package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author hf
 * @creat 2021-03-10-16:38
 */
@TableName("sql_history")
@Data
public class SqlHistory {
    private Integer id;

    private Long datasourceId;

    private Integer projectId;

    private String databaseSchema;

    private String sqlResult;

    private Integer sqlStatus;

    private String sqlContent;

    private Date submitTime;

    private Integer submitUser;

    private Integer isSaved;
}
