package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TInterfaceExamine {
    private Integer id;

    private String interId;

    private String interName;

    private String interRemark;

    private String registerCompany;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date applyTime;

    private String state;

    private String reviewer;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date exaTime;

    private String exaResult;

    private String exaDescribe;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInterId() {
        return interId;
    }

    public void setInterId(String interId) {
        this.interId = interId == null ? null : interId.trim();
    }

    public String getInterName() {
        return interName;
    }

    public void setInterName(String interName) {
        this.interName = interName == null ? null : interName.trim();
    }

    public String getInterRemark() {
        return interRemark;
    }

    public void setInterRemark(String interRemark) {
        this.interRemark = interRemark == null ? null : interRemark.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer == null ? null : reviewer.trim();
    }

    public Date getExaTime() {
        return exaTime;
    }

    public void setExaTime(Date exaTime) {
        this.exaTime = exaTime;
    }

    public String getExaResult() {
        return exaResult;
    }

    public void setExaResult(String exaResult) {
        this.exaResult = exaResult == null ? null : exaResult.trim();
    }

    public String getExaDescribe() {
        return exaDescribe;
    }

    public void setExaDescribe(String exaDescribe) {
        this.exaDescribe = exaDescribe == null ? null : exaDescribe.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRegisterCompany() {
        return registerCompany;
    }

    public void setRegisterCompany(String registerCompany) {
        this.registerCompany = registerCompany;
    }
}