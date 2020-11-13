package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author xuxueli 2019-05-04 16:43:12
 */
public class JobRole {

    private int id;
    @ApiModelProperty("账号")
    private String name;

    private String type;

    private String permission;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
