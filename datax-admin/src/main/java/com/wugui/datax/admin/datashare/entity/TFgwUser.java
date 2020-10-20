package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@Data
public class TFgwUser {
    private String uid;

    private String loginname;

    private String password;

    private String username;

    private String idcard;

    private String nation;

    private String sex;

    private String birthday;

    private String politicsface;

    private String headpicture;

    private String userstatus;

    private String title;

    private String officeaddr;

    private String officephone;

    private String mobilephone;

    private List<TUserorg> userorgs;

    private String email;

    private String mobilephone2;

    private String virtualnum;

    private String province;

    private String city;

    private String address;

    private String postcode;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;


    private List<TRole> roleList;
}