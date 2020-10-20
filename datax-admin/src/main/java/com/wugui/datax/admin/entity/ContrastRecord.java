package com.wugui.datax.admin.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @author hf
 * @creat 2020-10-19-15:19
 */
@Data
@ApiModel
@TableName("job_contrast_record")
public class ContrastRecord {
    @TableId
    @ApiModelProperty(value = "自增主键")
    private Long id;

    @ApiModelProperty("guid")
    private String guid;

    @ApiModelProperty("时间戳1")
    private Long timestamp1;

    @ApiModelProperty("时间戳2")
    private Long timestamp2;

    @ApiModelProperty("对比记录")
    private String record;

    @ApiModelProperty("对比时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JSONField(format = "yyyy/MM/dd HH:mm:ss")
    private Date createTime;
}
