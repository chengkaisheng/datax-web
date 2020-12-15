package com.wugui.datax.admin.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.wugui.datax.admin.entity.JobUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author hf
 * @creat 2020-12-14-18:47
 */
@Data
public class JobProjectVo {
    private int id;

    private String name;

    private String description;

    private int userId;

    private Boolean flag;

    private Date createTime;

    private Date updateTime;

    private String userName;

    private List<JobUser> users;
}
