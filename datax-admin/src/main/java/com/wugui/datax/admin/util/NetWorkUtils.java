package com.wugui.datax.admin.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datatx.core.biz.model.NetWork;
import com.wugui.datax.admin.core.conf.JobAdminConfig;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobInfoDetail;
import com.wugui.datax.admin.entity.JobInfoLink;

import java.util.ArrayList;
import java.util.List;

public class NetWorkUtils {
   /* public static com.wugui.datatx.core.biz.model.NetWork getNetWork(int jobId, com.wugui.datatx.core.biz.model.NetWork netWork){
        JobInfo jobInfo= JobAdminConfig.getAdminConfig().getJobInfoMapper().loadById(jobId);
        if(jobInfo != null && jobInfo.getChildJobId() != null && jobInfo.getChildJobId().trim().length() > 0){
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            for (int i = 0; i < childJobIds.length; i++) {
                netWork.addPoint(String.valueOf(jobInfo.getId()) , String.valueOf(childJobIds[i]),"1");
                getNetWork(Integer.parseInt(childJobIds[i]),netWork);
            }
        }
        return netWork;
    }
    private static boolean isNumeric(String str) {
        try {
            Integer.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }*/
    /*public static void  getJobInfoLink(int jobId, com.wugui.datatx.core.biz.model.NetWork netWork, JobInfoDetail jobInfoDetail){
        JobInfo jobInfo = JobAdminConfig.getAdminConfig().getJobInfoMapper().loadById(jobId);
        JobInfoLink jobInfoLink=JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().loadByJobInfoId(jobInfoDetail.getJobInfoId(),jobId);
        if (jobInfo != null) {
            String[] childJobIds = jobInfo.getChildJobId().split(",");
            List<String> linePoint = new ArrayList<>();//获取任务前置条件
            netWork.getLinePoint(String.valueOf(jobId), NetWork.TYPE.Y).forEach(jobLine -> linePoint.add(jobLine.get("X")));//获取当前任务的前置条件
            String parentId="";
            for (int i=0;i<linePoint.size();i++){
                parentId+=linePoint.get(i)+",";
            }
            if(!parentId.equals("")){
                parentId=parentId.substring(0,parentId.length() -1);
            }
            if(jobInfoLink!=null){
                jobInfoLink.setId(jobInfo.getId());
                jobInfoLink.setChildJobId(jobInfo.getChildJobId());
                jobInfoLink.setParentJobId(parentId);
                jobInfoLink.setTriggerStatus(0);
                jobInfoLink.setJobInfoId(jobInfoDetail.getJobInfoId());
                JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().updateByIdAndJobInfoId(jobInfoLink);
            }else {
                jobInfoLink=new JobInfoLink();
                jobInfoLink.setId(jobInfo.getId());
                jobInfoLink.setChildJobId(jobInfo.getChildJobId());
                jobInfoLink.setParentJobId(parentId);
                jobInfoLink.setTriggerStatus(0);
                jobInfoLink.setJobInfoId(jobInfoDetail.getJobInfoId());
                JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().save(jobInfoLink);
            }
            for (int i = 0; i < childJobIds.length; i++) {
                int childJobId = (childJobIds[i] != null && childJobIds[i].trim().length() > 0 && isNumeric(childJobIds[i])) ? Integer.valueOf(childJobIds[i]) : -1;
                getJobInfoLink(childJobId,netWork,jobInfoDetail);
            }

        }
    }*/

    public static void triggerVirtualTask(String jobInfoId,JobInfo jobInfo){
        List<JobInfoLink> jobInfoLinks=new ArrayList<>();
        JSONObject jSONObject = JSONObject.parseObject(jobInfo.getJobJson());
        JSONArray jsonArray = null;
        jsonArray = jSONObject.getJSONArray("linkDataArray");
        JSONArray jsonValues=jSONObject.getJSONArray("nodeDataArray");
        for (int i=0;i<jsonArray.size() ;i++){
            JobInfoLink jobInfoLink=new JobInfoLink();
            jobInfoLink.setJobInfoId(jobInfoId);
            String str1= jsonArray.getJSONObject(i).get("to").toString();
            String childId="";
            jobInfoLink.setId(getValues(str1,jsonValues));
            jobInfoLink.setInfoId(String.valueOf(getInfoId(str1,jsonValues)));
            //查找childId
            for (int j=0;j<jsonArray.size();j++){
                if(str1.equals(jsonArray.getJSONObject(j).get("from").toString())){
                    childId+=getInfoId(jsonArray.getJSONObject(j).get("to").toString(),jsonValues)+",";
                }
            }
            if(!childId.equals("")){
                childId=childId.substring(0,childId.length() -1);
            }
            if(childId.equals("0")){
                childId="";
            }
            jobInfoLink.setChildJobId(childId);
            //查找parentId
            String parenetId="";
            for (int j=0;j<jsonArray.size();j++){
                if(str1.equals(jsonArray.getJSONObject(j).get("to").toString())){
                    parenetId+=getInfoId(jsonArray.getJSONObject(j).get("from").toString(),jsonValues)+",";
                }
            }
            if(!parenetId.equals("")){
                parenetId=parenetId.substring(0,parenetId.length() -1);
            }
            if(parenetId.equals("0")){
                parenetId="";
            }
            jobInfoLink.setParentJobId(parenetId);
            //查找parentId主键infoId

            /*String infoIds="";
            for (int j=0;j<jsonArray.size();j++){
                if(str1.equals(jsonArray.getJSONObject(j).get("to").toString())){
                    infoIds+=getInfoId(jsonArray.getJSONObject(j).get("from").toString(),jsonValues)+",";
                }
            }
            if(!infoIds.equals("")){
                infoIds=infoIds.substring(0,infoIds.length() -1);
            }
            if(infoIds.equals("0")){
                infoIds="";
            }
            jobInfoLink.setInfoIds(infoIds);*/
            if(jobInfoLink.getId()!=0 && jobInfoLinks.contains(jobInfoLink)==false){
                jobInfoLinks.add(jobInfoLink);
                JobInfoLink jobInfoLinkInfo=JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().loadByInfoId(jobInfoLink.getInfoId(),jobInfoLink.getJobInfoId());
                if(UUIDUtils.notEmpty(jobInfoLinkInfo)){
                    JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().updateByIdAndJobInfoId(jobInfoLink);
                }else {
                    JobAdminConfig.getAdminConfig().getJobInfoLinkMapper().save(jobInfoLink);
                }
            }

        }
    }

    public static int getValues(String str,JSONArray jsonValues){
        for (int j=0;j<jsonValues.size();j++){
            if(str.equals(String.valueOf(jsonValues.getJSONObject(j).get("key"))) && !jsonValues.getJSONObject(j).get("text").toString().equals("开始")&&!jsonValues.getJSONObject(j).get("text").toString().equals("结束")){
                return Integer.parseInt(jsonValues.getJSONObject(j).get("id").toString());
            }
        }
        return 0;
    }

    public static String getInfoId(String str,JSONArray jsonValues){
        for (int j=0;j<jsonValues.size();j++){
            if(str.equals(jsonValues.getJSONObject(j).get("key").toString()) && !jsonValues.getJSONObject(j).get("text").toString().equals("开始")&&!jsonValues.getJSONObject(j).get("text").toString().equals("结束")){
                return jsonValues.getJSONObject(j).get("infoId").toString();
            }
        }
        return "0";
    }
}
