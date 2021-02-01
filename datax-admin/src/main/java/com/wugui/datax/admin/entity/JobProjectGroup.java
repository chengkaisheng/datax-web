package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author hf
 * @creat 2021-01-29-15:53
 */
@TableName("job_project_group")
@Data
@ToString
public class JobProjectGroup {

    private Integer id;

    private Integer projectId;

    private Integer jobId;

    private Integer parentId;

    private String name;

    private Integer type;

    private String jobType;

    @TableField(exist = false, select = false)
    private List<JobProjectGroup> children;
}
