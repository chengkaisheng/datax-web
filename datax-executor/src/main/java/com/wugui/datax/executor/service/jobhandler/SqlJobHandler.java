package com.wugui.datax.executor.service.jobhandler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.biz.model.TriggerParam;
import com.wugui.datatx.core.handler.IJobHandler;
import com.wugui.datatx.core.handler.annotation.JobHandler;
import com.wugui.datatx.core.log.JobLogger;
import com.wugui.datax.executor.entity.JobDatasource;
import com.wugui.datax.executor.util.HttpClientHelper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-10-21-16:51
 */
@JobHandler(value = "sqlJobHandler")
@Component
public class SqlJobHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
        String jobJson = tgParam.getJobJson();
        /*JobLogger.log(jobJson);*/
        JSONObject jsonObject = JSONObject.parseObject(jobJson);
        /*Map<String,Object> jsonObject = JSONObject.parseObject(jobJson, HashMap.class);
        JSONObject jobDatasourceJson = (JSONObject) jsonObject.get("jobDatasource");
        String sqlScript = (String)jsonObject.get("sqlScript");*/
        JSONObject jobDatasourceJson = jsonObject.getJSONObject("jobDatasource");
        String sqlScript = jsonObject.getString("sqlScript");
        JobDatasource jobDatasource = jobDatasourceJson.toJavaObject(JobDatasource.class);
        String[] split = jobDatasource.getJdbcUrl().split("/");
        String databaseName = split[split.length - 1];
        System.out.println(databaseName);
        jobDatasource.setDatabaseName(databaseName);
        String connId = HttpClientHelper.initConnection(jobDatasource);
        String contextId = HttpClientHelper.createContext(connId);
        String taskId = HttpClientHelper.executeSql(connId, contextId, sqlScript);
        int i = 0;
        while (!HttpClientHelper.getAsyncTaskInfo(taskId)){
            i++;
            Thread.sleep(500);
            if(i>=10){
                JobLogger.log("sql执行出错");
                return ReturnT.FAIL;
            }
        }
        String sqlExecuteTaskResults = HttpClientHelper.getSqlExecuteTaskResults(taskId);
        JobLogger.log(sqlExecuteTaskResults);
        return new ReturnT<>(200, "****************  sql任务执行啦  ****************");
    }
}
