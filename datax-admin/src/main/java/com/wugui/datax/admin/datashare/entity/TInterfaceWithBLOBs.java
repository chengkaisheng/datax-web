package com.wugui.datax.admin.datashare.entity;

import org.springframework.stereotype.Component;

@Component
public class TInterfaceWithBLOBs extends TInterface {
    private String example;

    private String registerCode;

    private String stateCode;

    private String inputParam;

    private String outputParam;

    private String success;

    private String fail;

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example == null ? null : example.trim();
    }

    public String getRegisterCode() {
        return registerCode;
    }

    public void setRegisterCode(String registerCode) {
        this.registerCode = registerCode == null ? null : registerCode.trim();
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode == null ? null : stateCode.trim();
    }

    public String getInputParam() {
        return inputParam;
    }

    public void setInputParam(String inputParam) {
        this.inputParam = inputParam == null ? null : inputParam.trim();
    }

    public String getOutputParam() {
        return outputParam;
    }

    public void setOutputParam(String outputParam) {
        this.outputParam = outputParam == null ? null : outputParam.trim();
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success == null ? null : success.trim();
    }

    public String getFail() {
        return fail;
    }

    public void setFail(String fail) {
        this.fail = fail == null ? null : fail.trim();
    }
}