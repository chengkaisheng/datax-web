package com.wugui.datatx.core.biz.model;

import java.io.Serializable;

/**
 * Created by xuxueli on 17/3/2.
 */
public class HandleCallbackParam implements Serializable {
    private static final long serialVersionUID = 42L;
    private String infoId;
    private long logId;

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    private long logDateTim;
    private String jobInfoId;
    private ReturnT<String> executeResult;

    public String getJobInfoId() {
        return jobInfoId;
    }

    public void setJobInfoId(String jobInfoId) {
        this.jobInfoId = jobInfoId;
    }

    public HandleCallbackParam(){}
    public HandleCallbackParam(long logId, long logDateTim, ReturnT<String> executeResult,String jobInfoId,String infoId) {
        this.logId = logId;
        this.jobInfoId=jobInfoId;
        this.logDateTim = logDateTim;
        this.executeResult = executeResult;
        this.infoId=infoId;
    }

    public long getLogId() {
        return logId;
    }

    public void setLogId(long logId) {
        this.logId = logId;
    }

    public long getLogDateTim() {
        return logDateTim;
    }

    public void setLogDateTim(long logDateTim) {
        this.logDateTim = logDateTim;
    }

    public ReturnT<String> getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(ReturnT<String> executeResult) {
        this.executeResult = executeResult;
    }

    @Override
    public String toString() {
        return "HandleCallbackParam{" +
                "logId=" + logId +
                ", logDateTim=" + logDateTim +
                ", executeResult=" + executeResult +
                '}';
    }

}
