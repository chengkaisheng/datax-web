package com.wugui.datax.executor.util;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datax.executor.entity.JobDatasource;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.jws.soap.SOAPBinding;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClientHelper {

    private static final String URL = "http://localhost:8979/dbeaver/gql";

    private static Log logger = LogFactory.getLog(HttpClientHelper.class);

    public static String sendPost(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = HttpClientUtil.getInstance();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        httpClient.executeMethod(postMethod);

        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }

    public static String sendPost(String urlParam, String requestBody) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = HttpClientUtil.getInstance();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建post请求方法实例对象
        PostMethod postMethod = new PostMethod(urlParam);
        // 设置post请求超时时间
        postMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        postMethod.addRequestHeader("Content-Type", "application/json");
        postMethod.setRequestHeader("Connection", "keep-alive");
        postMethod.setRequestBody(requestBody);
        httpClient.executeMethod(postMethod);
        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        return result;
    }


    public static String sendGet(String urlParam) throws HttpException, IOException {
        // 创建httpClient实例对象
        HttpClient httpClient = new HttpClient();
        // 设置httpClient连接主机服务器超时时间：15000毫秒
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(15000);
        // 创建GET请求方法实例对象
        GetMethod getMethod = new GetMethod(urlParam);
        // 设置post请求超时时间
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 60000);
        getMethod.addRequestHeader("Content-Type", "application/json");
        httpClient.executeMethod(getMethod);
        String result = getMethod.getResponseBodyAsString();
        getMethod.releaseConnection();
        return result;
    }

    /*public static void main(String[] args) throws HttpException, IOException {
        String id = initConnection();
        System.out.println("===========================================");
        System.out.println(id);
        System.out.println(getDBObjectInfo(id));
        System.out.println(getDatabaseInfo(id));
        System.out.println(getTableInfo(id));
    }*/



    public static String initConnection(JobDatasource jobDatasource) throws HttpException, IOException {
        //创建连接
        String[] split = jobDatasource.getJdbcUrl().split("/");
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
                "            \"driverId\": \""+DriverIdBuilder.getDriverId(jobDatasource.getDatasource())+"\",\n" +
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
        response = sendPost(URL, createConnBody);
        /*System.out.println(response);*/
        if(StringUtils.isEmpty(response)){
            throw new RuntimeException("创建连接失败");
        }

        Map<String, JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        String data = map.get("data").toJSONString();
        Map<String, JSONObject> dataMap = JSONObject.parseObject(data, HashMap.class);
        String result = dataMap.get("createConnection").toJSONString();
        Map<String, String> resultMap = JSONObject.parseObject(result, HashMap.class);
        String connectionId = resultMap.get("id");
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
        /*System.out.println(initConnBody);
        System.out.println(sendPost(URL, initConnBody));*/
        sendPost(URL, initConnBody);
        return connectionId;
    }

    public static String createContext(String coonId) throws IOException {
        String requestBody = "{\n" +
                "    \"query\": \"\\n    mutation sqlContextCreate($connectionId: ID!, $defaultCatalog: String, $defaultSchema: String) {\\n  context: sqlContextCreate(connectionId: $connectionId, defaultCatalog: $defaultCatalog, defaultSchema: $defaultSchema) {\\n    id\\n    defaultCatalog\\n    defaultSchema\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"connectionId\": \""+coonId+"\"\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, requestBody);
        Map<String,JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        JSONObject data = map.get("data");
        JSONObject context = data.getJSONObject("context");
        return context.getString("id");
    }

    public static  String executeSql(String coonId, String contextId, String sql) throws IOException {
        String requestBody = "{\n" +
                "    \"query\": \"\\n    mutation asyncSqlExecuteQuery($connectionId: ID!, $contextId: ID!, $query: String!, $filter: SQLDataFilter) {\\n  taskInfo: asyncSqlExecuteQuery(connectionId: $connectionId, contextId: $contextId, sql: $query, filter: $filter) {\\n    id\\n    name\\n    running\\n    status\\n    error {\\n      message\\n      errorCode\\n      stackTrace\\n    }\\n    taskResult\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"connectionId\": \""+coonId+"\",\n" +
                "        \"contextId\": \""+contextId+"\",\n" +
                "        \"query\": \""+sql+"\",\n" +
                "        \"filter\": {\n" +
                "            \"offset\": 0,\n" +
                "            \"limit\": 200,\n" +
                "            \"constraints\": []\n" +
                "        }\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, requestBody);
        Map<String,JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        JSONObject data = map.get("data");
        JSONObject taskInfo = data.getJSONObject("taskInfo");
        if(taskInfo.get("error") != null){
            return null;
        }
        return taskInfo.getString("id");
    }

    public static Boolean getAsyncTaskInfo(String taskId) throws IOException {
        String request = "{\n" +
                "    \"query\": \"\\n    mutation getAsyncTaskInfo($taskId: String!, $removeOnFinish: Boolean!) {\\n  taskInfo: asyncTaskInfo(id: $taskId, removeOnFinish: $removeOnFinish) {\\n    id\\n    name\\n    running\\n    status\\n    error {\\n      message\\n      errorCode\\n      stackTrace\\n    }\\n    taskResult\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"taskId\": \""+taskId+"\",\n" +
                "        \"removeOnFinish\": false\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, request);
        Map<String,JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        JSONObject data = map.get("data");
        JSONObject taskInfo = data.getJSONObject("taskInfo");
        String status = taskInfo.getString("status");
        return ( taskInfo.get("error")==null && "Finished".equals(status) );
    }

    public static String getSqlExecuteTaskResults(String taskId) throws IOException {
        String request = "{\n" +
                "    \"query\": \"\\n    mutation getSqlExecuteTaskResults($taskId: ID!) {\\n  result: asyncSqlExecuteResults(taskId: $taskId) {\\n    duration\\n    statusMessage\\n    results {\\n      updateRowCount\\n      sourceQuery\\n      title\\n      resultSet {\\n        id\\n        columns {\\n          dataKind\\n          entityName\\n          fullTypeName\\n          icon\\n          label\\n          maxLength\\n          name\\n          position\\n          precision\\n          readOnly\\n          scale\\n          typeName\\n        }\\n        rows\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"taskId\": \""+taskId+"\"\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, request);
        Map<String,JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        JSONObject data = map.get("data");
        JSONObject result = data.getJSONObject("result");
        JSONArray results = result.getJSONArray("results");
        return results.toJSONString();
    }
}