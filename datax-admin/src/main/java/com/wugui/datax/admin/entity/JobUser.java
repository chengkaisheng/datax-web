package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xuxueli 2019-05-04 16:43:12
 */
@Data
public class JobUser {

    private int id;
    @ApiModelProperty("账号")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("角色：0-普通用户、1-管理员")
    @TableField(exist=false)
    private String role;

    @ApiModelProperty("权限：执行器ID列表，多个逗号分割")
    @TableField(exist=false)
    private String permission;

    @TableField(exist=false)
    private List<Long> roleIdList;

    @TableField(exist = false, select = false)
    private List<String> roleName;

    @ApiModelProperty("创建者id")
    private Long createUserId;



    // plugin
    public boolean validPermission(int jobGroup){
        if ("1".equals(this.role)) {
            return true;
        } else {
            if (StringUtils.hasText(this.permission)) {
                for (String permissionItem : this.permission.split(",")) {
                    if (String.valueOf(jobGroup).equals(permissionItem)) {
                        return true;
                    }
                }
            }
            return false;
        }

    }

}