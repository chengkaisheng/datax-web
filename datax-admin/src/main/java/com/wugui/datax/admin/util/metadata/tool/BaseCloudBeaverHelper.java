package com.wugui.datax.admin.util.metadata.tool;


import com.alibaba.fastjson.JSONObject;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Property;
import com.wugui.datax.admin.util.AESUtil;
import com.wugui.datax.admin.util.DriverIdBuilder;
import com.wugui.datax.admin.util.HttpClientHelper;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hf
 * @creat 2020-11-03-16:52
 */
public abstract class BaseCloudBeaverHelper implements CloudBeaverHelperInterface{

    private static final String URL = "http://localhost:8979/dbeaver/gql";

    private Map<String,String> map;

    public BaseCloudBeaverHelper(String datasource){
        map = MetadataType.getMetaDataUrlName(datasource);
    }

    public  String initConnection(JobDatasource jobDatasource) throws HttpException, IOException {
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
        HttpClientHelper.sendPost(URL, initConnBody);
        return connectionId;
    }

    public Map<String,Map<String,String>> getDatabaseInfo(String coonId, String databaseName) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        String requestBody = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n   }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+coonId+"\"\n" +
                "    }\n" +
                "}";
        Map<String, String> datasourceInfo = parse(HttpClientHelper.sendPost(URL, requestBody));
        String requestBody1 = "{\n" +
                "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                "    \"variables\": {\n" +
                "        \"navNodeId\": \"database://"+coonId+"/"+map.get("db")+"/"+databaseName+"\"\n" +
                "    }\n" +
                "}";
        Map<String, String> databaseInfo = parse(HttpClientHelper.sendPost(URL, requestBody1));
        datasourceInfo.putAll(databaseInfo);
        map.put(databaseName, datasourceInfo);
        return map;
    }


    public  Map<String,Map<String,String>> getTablesInfo(String coonId, String database, List<String> tableNames) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        for (String tableName : tableNames) {
            String requestBody = "{\n" +
                    "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"navNodeId\": \"database://"+coonId+"/"+map.get("db")+"/"+database+"/"+map.get("table")+"/"+tableName+"\"\n" +
                    "    }\n" +
                    "}";
            map.put(tableName,parse(HttpClientHelper.sendPost(URL, requestBody)));
        }
        return map;
    }

    public  Map<String,Map<String,String>> getColumnsInfo(String coonId, String database, String tablename, List<String> columns) throws IOException {
        Map<String,Map<String,String>> map = new HashMap<>();
        for (String column : columns) {
            String requestBody = "{\n" +
                    "    \"query\": \"\\n    query getDBObjectInfo($navNodeId: ID!, $filter: ObjectPropertyFilter) {\\n  objectInfo: navNodeInfo(nodePath: $navNodeId) {\\n    object {\\n      features\\n      properties(filter: $filter) {\\n        id\\n        category\\n        dataType\\n        description\\n        displayName\\n        features\\n        value\\n      }\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"navNodeId\": \"database://"+coonId+"/"+map.get("db")+"/"+database+"/"+map.get("table")+"/"+tablename+"/"+map.get("column")+"/"+column+"\"\n" +
                    "    }\n" +
                    "}";
            map.put(column,parse(HttpClientHelper.sendPost(URL, requestBody)));
        }
        return map;
    }



    public  Map<String, Map<String, String>> getIndexesInfo(String connId, String databaseName, String tableName, List<String> indexes) throws IOException {

        Map<String,Map<String,String>> map = new HashMap<>();
        for (String index : indexes) {
            String requestBody = "{\n" +
                    "    \"query\": \"\\n    query navNodeChildren($parentPath: ID!) {\\n  navNodeChildren(parentPath: $parentPath) {\\n    id\\n    name\\n    hasChildren\\n    nodeType\\n    icon\\n    folder\\n    inline\\n    navigable\\n    features\\n    object {\\n      features\\n    }\\n  }\\n  navNodeInfo(nodePath: $parentPath) {\\n    id\\n    name\\n    hasChildren\\n    nodeType\\n    icon\\n    folder\\n    inline\\n    navigable\\n    features\\n    object {\\n      features\\n    }\\n  }\\n}\\n    \",\n" +
                    "    \"variables\": {\n" +
                    "        \"parentPath\": \"database://"+connId+"/"+databaseName+"/org.jkiss.dbeaver.ext.postgresql.model.PostgreSchema/"+databaseName+"/org.jkiss.dbeaver.ext.postgresql.model.PostgreTable/"+tableName+"/org.jkiss.dbeaver.ext.postgresql.model.PostgreIndex/"+index+"\"\n" +
                    "    }\n" +
                    "}";
            map.put(index,parse(HttpClientHelper.sendPost(URL, requestBody)));
        }
        return map;

    }

    public  Map<String, String> parse(String response){
        Map<String, String> mapInfo = new HashMap<>();
        Map<String, JSONObject> map = JSONObject.parseObject(response, HashMap.class);
        JSONObject jsonObject = map.get("data");
        JSONObject objectInfo = jsonObject.getJSONObject("objectInfo");
        JSONObject object1 = objectInfo.getJSONObject("object");
        List<Property> list = object1.getJSONArray("properties").toJavaList(Property.class);
        for (Property property : list) {
            mapInfo.put(property.getId(), property.getValue());
        }
        return mapInfo;
    }
}
