package com.wugui.datax.admin.datashare.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class InterfaceResult {
    private String id;

    private String contacts;

    private String telephone;

    private String registerCompany;

    private String interName;

    private String interRemark;

    private String interState;

//    private String dataRange;

//    private String dataCompany;

//    private String implMethod;

//    private String businessType;

    private String responseMode;

//    private String deployMethod;

//    private String realName;

//    private String isLimit;

//    private String provideService;

    private String infoId;

    private String tableEnglish;

    private String interCode;

    private String interUrl;

    private String encodingFormat;

    private String requestMethod;

    private String interVersion;

    private String example;

    private String registerCode;

    private String stateCode;

    private String inputParam;

    private String outputParam;

    private String success;

    private String fail;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

    private String isApply;
}
