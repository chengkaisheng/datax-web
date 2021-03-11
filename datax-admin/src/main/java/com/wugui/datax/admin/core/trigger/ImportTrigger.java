package com.wugui.datax.admin.core.trigger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datatx.core.biz.model.TriggerParam;
import com.wugui.datatx.core.log.JobLogger;
import com.wugui.datax.admin.core.conf.JobAdminConfig;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.tool.query.HiveQueryTool;
import com.wugui.datax.admin.tool.query.ImpalaQueryTool;
import com.wugui.datax.admin.util.JdbcConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author ：lxq
 * @description：引入任务执行操作
 * @date ：2021/2/2 10:08
 */
public class ImportTrigger {
    private static Logger logger = LoggerFactory.getLogger(ImportTrigger.class);

    private static ImportTrigger instance = new ImportTrigger();

    public static ImportTrigger getInstance() {
        return instance;
    }

    /**
     * @author: lxq
     * @description: 执行创建临时表
     * @date: 2021/2/2 11:11
     * @param jobInfo
     * @return: void
     */
    public static void triggerImportCreateTable(JobInfo jobInfo){
        try {
//            JobInfo jobInfo = JobAdminConfig.getAdminConfig().getJobInfoMapper().loadById(triggerParam.getJobId());
            if("IMPORT".equals(jobInfo.getJobType())){
                int count=0;
                JSONObject jsonObject = JSON.parseObject(jobInfo.getJobJson());
                JSONObject jsonObject1=null;
                String content = jsonObject.getJSONObject("job").getJSONArray("content").get(0).toString();
                jsonObject1=JSON.parseObject(content);
                String createTableSql=jsonObject1.getJSONObject("writer").get("preSql").toString();
                String dataSourceId=jsonObject1.getJSONObject("writer").get("id").toString();
                JobDatasource jobDatasource=JobAdminConfig.getAdminConfig().getJobDatasourceMapper().selectById(dataSourceId);
                if(JdbcConstants.HIVE.equals(jobDatasource.getDatasource())){
                    HiveQueryTool hiveQueryTool=new HiveQueryTool(jobDatasource);
                    count=hiveQueryTool.executeUpdate(createTableSql);
                }
                if(JdbcConstants.IMPALA.equals(jobDatasource.getDatasource())){
                    ImpalaQueryTool impalaQueryTool=new ImpalaQueryTool(jobDatasource);
                    count=impalaQueryTool.executeUpdate(createTableSql);
                }
                if(count>0){
                    JobLogger.log("<br>----------- DataElit IMPORT job create table success");
                    System.err.println("创建成功");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JobLogger.log("<br>----------- DataElit IMPORT job create table error");
            JobLogger.log("<br>----------- DataElit IMPORT job create table error info:"+e.getMessage());
        }
    }

    /**
     * @author: lxq
     * @description: 任务执行完成后执行insert语句
     * @date: 2021/2/2 11:11
     * @param jobInfo
     * @return: void
     */
    public static void triggerImportInsert(JobInfo jobInfo){
        try {
            if("IMPORT".equals(jobInfo.getJobType())){
                int count=0;
                JSONObject jsonObject = JSON.parseObject(jobInfo.getJobJson());
                JSONObject jsonObject1=null;
                String content = jsonObject.getJSONObject("job").getJSONArray("content").get(0).toString();
                jsonObject1=JSON.parseObject(content);
                String createTableSql=jsonObject1.getJSONObject("writer").get("postSql").toString();
                String dataSourceId=jsonObject1.getJSONObject("writer").get("id").toString();
                JobDatasource jobDatasource=JobAdminConfig.getAdminConfig().getJobDatasourceMapper().selectById(dataSourceId);
                if(JdbcConstants.HIVE.equals(jobDatasource.getDatasource())){
                    HiveQueryTool hiveQueryTool=new HiveQueryTool(jobDatasource);
                    count=hiveQueryTool.executeUpdate(createTableSql);
                }
                if(JdbcConstants.IMPALA.equals(jobDatasource.getDatasource())){
                    ImpalaQueryTool impalaQueryTool=new ImpalaQueryTool(jobDatasource);
                    count=impalaQueryTool.executeUpdate(createTableSql);
                }
                if(count>0){
                    JobLogger.log("<br>----------- DataElit IMPORT job insert success");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JobLogger.log("<br>----------- DataElit IMPORT job insert error info:"+e.getMessage());
        }
    }

    /**
     * @author: lxq
     * @description: TODO
     * @date: 2021/2/25 9:57
     * @param jobInfo
     * @return: void
     */
    public static void refreshTable(JobInfo jobInfo){
        try {
            if("IMPORT".equals(jobInfo.getJobType())){
                int count=0;
                JSONObject jsonObject = JSON.parseObject(jobInfo.getJobJson());
                JSONObject jsonObject1=null;
                String content = jsonObject.getJSONObject("job").getJSONArray("content").get(0).toString();
                jsonObject1=JSON.parseObject(content);
                String dataSourceId=jsonObject1.getJSONObject("writer").get("id").toString();
                String tableName=jsonObject1.getJSONObject("writer").get("fileName").toString();
                JobDatasource jobDatasource=JobAdminConfig.getAdminConfig().getJobDatasourceMapper().selectById(dataSourceId);
                if(JdbcConstants.HIVE.equals(jobDatasource.getDatasource())){
                    HiveQueryTool hiveQueryTool=new HiveQueryTool(jobDatasource);
                    hiveQueryTool.refreshTable(tableName);
                }
                if(JdbcConstants.IMPALA.equals(jobDatasource.getDatasource())){
                    ImpalaQueryTool impalaQueryTool=new ImpalaQueryTool(jobDatasource);
                    impalaQueryTool.refreshTable(tableName);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            JobLogger.log("<br>----------- DataElit IMPORT job refreshTable error:"+e.getMessage());
        }
    }
}
