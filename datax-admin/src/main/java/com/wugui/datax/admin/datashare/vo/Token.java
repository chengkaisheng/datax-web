package com.wugui.datax.admin.datashare.vo;


import com.wugui.datax.admin.datashare.entity.TDepartment;
import com.wugui.datax.admin.datashare.entity.TFgwUser;
import com.wugui.datax.admin.datashare.entity.TOrganize;
import com.wugui.datax.admin.datashare.entity.TUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    //用户信息
    private TUser tuser;
    private TFgwUser user;

    //部门信息
    private TDepartment department;
    private TOrganize organize;


    public Token(TUser user, TDepartment department) {
        this.tuser=user;
        this.department=department;
    }

    public Token(TFgwUser fgwUser, TOrganize organize) {
        this.user=fgwUser;
        this.organize=organize;
    }
}
