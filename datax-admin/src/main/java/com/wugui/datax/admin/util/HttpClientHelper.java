package com.wugui.datax.admin.util;


import com.alibaba.fastjson.JSONObject;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Property;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

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




    public static Map<String, String> parse(String response){
        Map<String, String> mapInfo = new HashMap<>();
        Map<String, JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        /*System.out.println(map);
        if(map.get("data") == null){
            System.out.println("json解析异常：data=null");
        }*/
        JSONObject jsonObject = map.get("data");
        JSONObject objectInfo = jsonObject.getJSONObject("objectInfo");
        JSONObject object1 = objectInfo.getJSONObject("object");
        List<Property> list = object1.getJSONArray("properties").toJavaList(Property.class);
        /*JSONArray properties1 = (JSONArray) properties2;
        System.out.println(jsonObject.toString());
        String data = jsonObject.toString();
        Map<String, JSONObject> dataMap = JSONObject.parseObject(data, HashMap.class);
        String result = dataMap.get("objectInfo").toString();
        Map<String, JSONObject> resultMap = JSONObject.parseObject(result, HashMap.class);
        String object = resultMap.get("object").toString();
        Map<String, JSONArray> objectMap = JSONObject.parseObject(object, HashMap.class);
        JSONArray properties = objectMap.get("properties");
        List<Property> list = properties.toJavaList(Property.class);*/
        for (Property property : list) {
            mapInfo.put(property.getId(), property.getValue());
        }
        return mapInfo;
    }


    /**
    *@author 27441
    *@Description 批量获取并封装表的元数据
    *@Date 10:48 2020/9/27
    *@Param [coonId, database, tableNames]
    *@return java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>>
    **/
    public static Map<String,Map<String,String>> getTablesInfo(String coonId, String database, List<String> tableNames) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        for (String tableName : tableNames) {
            String requestBody = "{\n" +
                    "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"navNodeId\": \"database://"+coonId+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/"+database+"/org.jkiss.dbeaver.ext.mysql.model.MySQLTable/"+tableName+"\"\n" +
                    "    }\n" +
                    "}";
            map.put(tableName,parse(sendPost(URL, requestBody)));
        }
        return map;
    }

    /**
    *@author 27441
    *@Description 批量获取某数据库下的某一表名下的所有列的元数据信息
    *@Date 10:56 2020/9/27
    *@Param [coonId, database, tablename, columns]
    *@return java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>>
    **/
    public static Map<String,Map<String,String>> getColumnsInfo(String coonId, String database, String tablename, List<String> columns) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        for (String column : columns) {
            String requestBody = "{\n" +
                    "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"navNodeId\": \"database://"+coonId+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/"+database+"/org.jkiss.dbeaver.ext.mysql.model.MySQLTable/"+tablename+"/org.jkiss.dbeaver.ext.mysql.model.MySQLTableColumn/"+column+"\"\n" +
                    "    }\n" +
                    "}";
            map.put(column,parse(sendPost(URL, requestBody)));
        }
        return map;
    }

    public static Map<String,Map<String,String>> getDatabaseInfo(String coonId, String databaseName) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        String requestBody = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n   }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+coonId+"\"\n" +
                "    }\n" +
                "}";
        Map<String, String> datasourceInfo = parse(sendPost(URL, requestBody));
        String requestBody1 = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+coonId+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/"+databaseName+"\"\n" +
                "    }\n" +
                "}";
        Map<String, String> databaseInfo = parse(sendPost(URL, requestBody1));
        datasourceInfo.putAll(databaseInfo);
        map.put(databaseName, datasourceInfo);
        return map;
    }


    /*public static Map<String, String> getDBObjectInfo(String id) throws HttpException, IOException{
        String requestBody = "{\"query\":\"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\"variables\":{\"navNodeId\":\"database://"+ id +"\"}}";
        System.out.println(requestBody);
        String response = sendPost(URL, requestBody);
        //解析json
        Map<String, String> mapInfo = new HashMap<>();

        return parse(response);
    }

    public static Map<String, String> getDatabaseInfo(String id) throws IOException {
        String requestBody = "{\"query\":\"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\"variables\":{\"navNodeId\":\"database://"+id+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/datax_web\"}}";
        String response = sendPost(URL, requestBody);
        return parse(response);
    }

    public static Map<String,String> getTableInfo(String id) throws IOException {
        String requestBody = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+id+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/datax_web/org.jkiss.dbeaver.ext.mysql.model.MySQLTable/job_group\"\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, requestBody);
        return parse(response);
    }

    public static Map<String,String> getColumnInfo(String id) throws IOException {
        String requestBody = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+id+"/org.jkiss.dbeaver.ext.mysql.model.MySQLCatalog/datax_web/org.jkiss.dbeaver.ext.mysql.model.MySQLTable/job_group/org.jkiss.dbeaver.ext.mysql.model.MySQLTableColumn/id\"\n" +
                "    }\n" +
                "}";
        String response = sendPost(URL, requestBody);
        return parse(response);
    }*/
}