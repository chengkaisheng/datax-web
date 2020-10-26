package com.wugui.datax.executor.service.jobhandler;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datatx.core.biz.model.TriggerParam;
import com.wugui.datatx.core.handler.IJobHandler;
import com.wugui.datatx.core.handler.annotation.JobHandler;
import com.wugui.datatx.core.log.JobLogger;
import com.wugui.datax.executor.entity.JobDatasource;
import com.wugui.datax.executor.util.AESUtil;
import com.wugui.datax.executor.util.DriverIdBuilder;
import com.wugui.datax.executor.util.HttpClientHelper;
import org.apache.commons.lang3.StringUtils;
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

    private static final String URL = "http://localhost:8979/dbeaver/gql";

    @Override
    public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
        String jobJson = tgParam.getJobJson();
        //JobLogger.log(jobJson);
        JSONObject jsonObject = JSONObject.parseObject(jobJson);
        JSONObject jobDatasourceJson = jsonObject.getJSONObject("jobDatasource");
        String sqlScript = jsonObject.getString("sqlScript");
        JobLogger.log("sqlScript:"+sqlScript);
        JobDatasource jobDatasource = jobDatasourceJson.toJavaObject(JobDatasource.class);

        //创建连接
        String[] split = jobDatasource.getJdbcUrl().split("/");

        String databaseName = split[split.length - 1];
        //System.out.println(databaseName);
        jobDatasource.setDatabaseName(databaseName);

        String temp = split[split.length - 2];
        String host = temp.split(":")[0];
        String port = temp.split(":")[1];
        String name = jobDatasource.getDatabaseName()+"@"+host;

        String response = "";
        String username = AESUtil.decrypt(jobDatasource.getJdbcUsername());
        String password = AESUtil.decrypt(jobDatasource.getJdbcPassword());
        String createConnBody = "{\n" +
                "    \"query\": \"\\n    mutation createConnection($config: ConnectionConfig!) {\\n  createConnection(config: $config) {\\n    id\\n    name\\n    description\\n    driverId\\n    connected\\n    readOnly\\n    features\\n    authNeeded\\n    authModel\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"config\": {\n" +
                "            \"name\": \""+name+"\",\n" +
                "            \"driverId\": \""+ DriverIdBuilder.getDriverId(jobDatasource.getDatasource())+"\",\n" +
                "            \"host\": \""+host+"\",\n" +
                "            \"port\": \""+port+"\",\n" +
                "            \"databaseName\": \""+jobDatasource.getDatabaseName()+"\",\n" +
                "            \"authModelId\": \"native\",\n" +
                "            \"credentials\": {\n" +
                "                \"userName\": \""+ username +"\",\n" +
                "                \"userPassword\": \""+ password +"\"\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "}";
        response = HttpClientHelper.sendPost(URL, createConnBody);

        //System.out.println(response);
        if(StringUtils.isEmpty(response)){
            throw new RuntimeException("Create Connection Failed");
        }
        Map<String, JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        String data = map.get("data").toJSONString();
        //记录到handle日志
        JobLogger.log(data);

        Map<String, JSONObject> dataMap = JSONObject.parseObject(data, HashMap.class);
        String result = dataMap.get("createConnection").toJSONString();
        Map<String, String> resultMap = JSONObject.parseObject(result, HashMap.class);
        String connectionId = resultMap.get("id");
        if(connectionId == null){
            JobLogger.log("Create Connection Failed");
            return ReturnT.FAIL;
        }
        //拿到connectionId后  再去初始化连接
        String initConnBody = "{\n" +
                "    \"query\": \"\\n    mutation initConnection($id: ID!, $credentials: Object) {\\n  connection: initConnection(id: $id, credentials: $credentials) {\\n    id\\n    name\\n    description\\n    driverId\\n    connected\\n    readOnly\\n    features\\n    authNeeded\\n    authModel\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"id\": \""+connectionId+"\",\n" +
                "        \"credentials\": {\n" +
                "            \"userName\": \""+ username +"\",\n" +
                "            \"userPassword\": \""+ password +"\"\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String initCoon = HttpClientHelper.sendPost(URL, initConnBody);
        JobLogger.log(JSONObject.parseObject(initCoon).toJSONString());

        //===========================================================================
        String requestBody = "{\n" +
                "    \"query\": \"\\n    mutation sqlContextCreate($connectionId: ID!, $defaultCatalog: String, $defaultSchema: String) {\\n  context: sqlContextCreate(connectionId: $connectionId, defaultCatalog: $defaultCatalog, defaultSchema: $defaultSchema) {\\n    id\\n    defaultCatalog\\n    defaultSchema\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"connectionId\": \""+connectionId+"\"\n" +
                "    }\n" +
                "}";
        response = HttpClientHelper.sendPost(URL, requestBody);
        map = JSONObject.parseObject(response, HashMap.class);
        JSONObject dataObject = map.get("data");
        JobLogger.log(dataObject.toJSONString());
        JSONObject context = dataObject.getJSONObject("context");
        String contextId = context.getString("id");
        if(StringUtils.isEmpty(contextId)){
            JobLogger.log("Create SqlContext Fail");
            return ReturnT.FAIL;
        }

        //===========================================================================
        requestBody = "{\n" +
                "    \"query\": \"\\n    mutation asyncSqlExecuteQuery($connectionId: ID!, $contextId: ID!, $query: String!, $filter: SQLDataFilter) {\\n  taskInfo: asyncSqlExecuteQuery(connectionId: $connectionId, contextId: $contextId, sql: $query, filter: $filter) {\\n    id\\n    name\\n    running\\n    status\\n    error {\\n      message\\n      errorCode\\n      stackTrace\\n    }\\n    taskResult\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"connectionId\": \""+connectionId+"\",\n" +
                "        \"contextId\": \""+contextId+"\",\n" +
                "        \"query\": \""+sqlScript+"\",\n" +
                "        \"filter\": {\n" +
                "            \"offset\": 0,\n" +
                "            \"limit\": 200,\n" +
                "            \"constraints\": []\n" +
                "        }\n" +
                "    }\n" +
                "}";
        response = HttpClientHelper.sendPost(URL, requestBody);
        map = JSONObject.parseObject(response, HashMap.class);
        dataObject = map.get("data");
        JobLogger.log(dataObject.toJSONString());
        JSONObject taskInfo = dataObject.getJSONObject("taskInfo");
        if(taskInfo.get("error") != null){
            JobLogger.log(taskInfo.getString("error"));
            return ReturnT.FAIL;
        }
        String taskId = taskInfo.getString("id");
        if(StringUtils.isEmpty(taskId)){
            JobLogger.log("SqlTask Executed Fail");
        }
        //========================================================
        int i = 0;
        requestBody = "{\n" +
                "    \"query\": \"\\n    mutation getAsyncTaskInfo($taskId: String!, $removeOnFinish: Boolean!) {\\n  taskInfo: asyncTaskInfo(id: $taskId, removeOnFinish: $removeOnFinish) {\\n    id\\n    name\\n    running\\n    status\\n    error {\\n      message\\n      errorCode\\n      stackTrace\\n    }\\n    taskResult\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"taskId\": \""+taskId+"\",\n" +
                "        \"removeOnFinish\": false\n" +
                "    }\n" +
                "}";
        while (i < 10){
            response = HttpClientHelper.sendPost(URL, requestBody);
            map = JSONObject.parseObject(response, HashMap.class);
            dataObject = map.get("data");
            taskInfo = dataObject.getJSONObject("taskInfo");
            String status = taskInfo.getString("status");
            Thread.sleep(1000);
            i++;
            if(taskInfo.get("error") != null){
                JobLogger.log(taskInfo.get("error").toString());
                return ReturnT.FAIL;
            }
            if("Finished".equals(status)){
                JobLogger.log(taskInfo.toJSONString());
                break;
            }
        }
        if( i >= 10){
            JobLogger.log("SqlTask Executed TimeOut");
            return ReturnT.FAIL;
        }

        //===========================================
        requestBody = "{\n" +
                "    \"query\": \"\\n    mutation getSqlExecuteTaskResults($taskId: ID!) {\\n  result: asyncSqlExecuteResults(taskId: $taskId) {\\n    duration\\n    statusMessage\\n    results {\\n      updateRowCount\\n      sourceQuery\\n      title\\n      resultSet {\\n        id\\n        columns {\\n          dataKind\\n          entityName\\n          fullTypeName\\n          icon\\n          label\\n          maxLength\\n          name\\n          position\\n          precision\\n          readOnly\\n          scale\\n          typeName\\n        }\\n        rows\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"taskId\": \""+taskId+"\"\n" +
                "    }\n" +
                "}";
        response = HttpClientHelper.sendPost(URL, requestBody);
        map = JSONObject.parseObject(response, HashMap.class);
        dataObject = map.get("data");
        JobLogger.log(dataObject.toJSONString());
        JSONObject resultObject = dataObject.getJSONObject("result");
        JSONArray results = resultObject.getJSONArray("results");
        return new ReturnT<>("SqlTask Executed Success");
    }

    /*@Override
    public ReturnT<String> execute(TriggerParam tgParam) throws Exception {
        String jobJson = tgParam.getJobJson();
        JobLogger.log(jobJson);
        JSONObject jsonObject = JSONObject.parseObject(jobJson);
        JSONObject jobDatasourceJson = jsonObject.getJSONObject("jobDatasource");
        String sqlScript = jsonObject.getString("sqlScript");
        JobLogger.log("sqlScript:"+sqlScript);
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
    }*/
}
