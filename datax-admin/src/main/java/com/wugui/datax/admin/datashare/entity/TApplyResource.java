package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class TApplyResource {
    private String id;

    private String interId;

    private String userId;

    private String infoId;

    private String infoName;

    private String infoExtract;

    private String contacts;

    private String telephone;

    private String resType;

    private String applyImg;

    private String useScene;

    private String state;

    private String businessType;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;


}