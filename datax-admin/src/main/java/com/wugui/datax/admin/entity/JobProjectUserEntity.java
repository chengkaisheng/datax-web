package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author hf
 * @creat 2020-12-14-17:26
 */
@Data
@TableName("job_project_user")
public class JobProjectUserEntity {
    private static final long serialVersionUID = 1L;
    @TableId
    private int id;

    /**
     * 用户ID
     */
    private int userId;

    /**
     * 角色ID
     */
    private int projectId;

}
