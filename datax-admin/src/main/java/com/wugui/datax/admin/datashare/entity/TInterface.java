package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TInterface {
    private String id;

    private String contacts;

    private String telephone;

    private String registerCompany;

    private String interName;

    private String interRemark;

    private String interState;

    /*private String dataRange;*/

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

    private String isApply;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts == null ? null : contacts.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getRegisterCompany() {
        return registerCompany;
    }

    public void setRegisterCompany(String registerCompany) {
        this.registerCompany = registerCompany == null ? null : registerCompany.trim();
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

    public String getInterState() {
        return interState;
    }

    public void setInterState(String interState) {
        this.interState = interState == null ? null : interState.trim();
    }

   /* public String getDataRange() {
        return dataRange;
    }

    public void setDataRange(String dataRange) {
        this.dataRange = dataRange == null ? null : dataRange.trim();
    }
*/
    /*public String getDataCompany() {
        return dataCompany;
    }

    public void setDataCompany(String dataCompany) {
        this.dataCompany = dataCompany == null ? null : dataCompany.trim();
    }*/

    /*public String getImplMethod() {
        return implMethod;
    }

    public void setImplMethod(String implMethod) {
        this.implMethod = implMethod == null ? null : implMethod.trim();
    }*/

   /* public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }
*/
    public String getResponseMode() {
        return responseMode;
    }

    public void setResponseMode(String responseMode) {
        this.responseMode = responseMode == null ? null : responseMode.trim();
    }

  /*  public String getDeployMethod() {
        return deployMethod;
    }

    public void setDeployMethod(String deployMethod) {
        this.deployMethod = deployMethod == null ? null : deployMethod.trim();
    }
*/
    /*public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }
*/
   /* public String getIsLimit() {
        return isLimit;
    }

    public void setIsLimit(String isLimit) {
        this.isLimit = isLimit == null ? null : isLimit.trim();
    }

    public String getProvideService() {
        return provideService;
    }

    public void setProvideService(String provideService) {
        this.provideService = provideService == null ? null : provideService.trim();
    }*/

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId == null ? null : infoId.trim();
    }

    public String getTableEnglish() {
        return tableEnglish;
    }

    public void setTableEnglish(String tableEnglish) {
        this.tableEnglish = tableEnglish == null ? null : tableEnglish.trim();
    }

    public String getInterCode() {
        return interCode;
    }

    public void setInterCode(String interCode) {
        this.interCode = interCode == null ? null : interCode.trim();
    }

    public String getInterUrl() {
        return interUrl;
    }

    public void setInterUrl(String interUrl) {
        this.interUrl = interUrl == null ? null : interUrl.trim();
    }

    public String getEncodingFormat() {
        return encodingFormat;
    }

    public void setEncodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat == null ? null : encodingFormat.trim();
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod == null ? null : requestMethod.trim();
    }

    public String getInterVersion() {
        return interVersion;
    }

    public void setInterVersion(String interVersion) {
        this.interVersion = interVersion == null ? null : interVersion.trim();
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

    public String getIsApply() {
        return isApply;
    }

    public void setIsApply(String isApply) {
        this.isApply = isApply;
    }
}