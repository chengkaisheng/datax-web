package com.wugui.datax.admin.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.ContrastRecord;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.service.ContrastRecordService;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.JobDatasourceService;
import com.wugui.datax.admin.tool.query.MySQLQueryTool;
import com.wugui.datax.admin.util.FastJsonDiff;
import com.wugui.datax.admin.util.HttpClientHelper;
import com.wugui.datax.admin.util.MetadataBuildUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.atlas.AtlasClientV2;
import org.apache.atlas.AtlasException;
import org.apache.atlas.AtlasServiceException;
import org.apache.atlas.model.audit.EntityAuditEventV2;
import org.apache.atlas.model.instance.AtlasEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

/**
 * @author hf
 * @creat 2020-09-28-15:54
 */
@RestController
@RequestMapping("/api/metadata")
@Api(tags = "导入元数据到atlas的接口")
public class AtlasController extends BaseController {

    @Autowired
    private DatasourceQueryService datasourceQueryService;

    @Autowired
    private JobDatasourceService jobJdbcDatasourceService;

    @Autowired
    private ContrastRecordService contrastRecordService;

    @PostMapping("/import")
    @ApiOperation("导入某一个库下的所有元数据")
    public ReturnT<String> importMetadata(@RequestParam Long id) throws IOException, SQLException {
        //查询出该id对应的datasource
        JobDatasource jobDatasource = jobJdbcDatasourceService.getById(id);
        //拼接字符串
        MySQLQueryTool mySQLQueryTool = new MySQLQueryTool(jobDatasource);
        String schema = mySQLQueryTool.getDBName();
        System.out.println(schema);
        jobDatasource.setDatabaseName(schema);
        MetadataBuildUtils.jobDatasource = jobDatasource;
        String coonId = HttpClientHelper.initConnection(jobDatasource);
        AtlasClientV2 atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        Map<String, String> dbGuidMap = MetadataBuildUtils.setRdbmsDb(atlasClientV2, coonId);
        List<String> tables = datasourceQueryService.getTables(id, null);
        Map<String, String> tableGuidMap = MetadataBuildUtils.setRdbmsTables(atlasClientV2, coonId, schema, tables, dbGuidMap.get(schema));
        for (String tableName : tables) {
            List<String> columnNames = datasourceQueryService.getColumns(id, tableName);
            MetadataBuildUtils.setRdbmsColumns(atlasClientV2, coonId, schema, tableName, columnNames, tableGuidMap.get(tableName));
        }
        return new ReturnT(200, "写入元数据成功");
    }


    @PostMapping("/import1")
    @ApiOperation("优化后批量导入mysql元数据")
    public ReturnT importMetadata1(@RequestParam Long id) throws IOException, SQLException {
        //查询出该id对应的datasource
        JobDatasource jobDatasource = jobJdbcDatasourceService.getById(id);
        //拼接字符串
        MySQLQueryTool mySQLQueryTool = new MySQLQueryTool(jobDatasource);
        String schema = mySQLQueryTool.getDBName();
        System.out.println(schema);
        jobDatasource.setDatabaseName(schema);
        MetadataBuildUtils.jobDatasource = jobDatasource;
        String coonId = HttpClientHelper.initConnection(jobDatasource);
        AtlasClientV2 atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        Map<String, String> dbGuidMap = MetadataBuildUtils.setRdbmsDb(atlasClientV2, coonId);
        Map<String, List<String>> columnToTable = new HashMap<>();
        List<String> tables = datasourceQueryService.getTables(id, null);
        Map<String, String> tableGuidMap = MetadataBuildUtils.setRdbmsTables(atlasClientV2, coonId, schema, tables, dbGuidMap.get(schema));
        for (String table : tables) {
            List<String> columnNames = datasourceQueryService.getColumns(id, table);
            columnToTable.put(table, columnNames);
        }
        List<AtlasEntity> atlasEntities = new ArrayList<>();
        //批量封装table对应column集合的entity
        columnToTable.forEach((key, value) ->
        {
            try {
                Map<String, String> relationship = MetadataBuildUtils.buildRelation(tableGuidMap.get(key), "mysql_test1_table");
                Map<String, Map<String, String>> columnsInfo = HttpClientHelper.getColumnsInfo(coonId, schema, key, value);
                for (Map.Entry<String, Map<String, String>> stringMapEntry : columnsInfo.entrySet()) {
                    String columnName = stringMapEntry.getKey();
                    Map<String, String> properties = stringMapEntry.getValue();
                    atlasEntities.add(MetadataBuildUtils.buildColumnEntity(columnName, key, properties, relationship));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //封装好了之后批量导入
        MetadataBuildUtils.createEntities(atlasClientV2, new AtlasEntity.AtlasEntitiesWithExtInfo(atlasEntities));
        return new ReturnT(200, "写入元数据成功");
    }



    @PostMapping("/contrast")
    @ApiOperation("两个版本数据对比")
    public ReturnT<Map<String,Object>> contrast(@RequestBody Map<String,Object> requestMap) throws AtlasServiceException {
        List<Long> timestamps = (List<Long>) requestMap.get("timestamp");
        Map<String,Object> returnMap = new HashMap<>();
        String guid = (String) requestMap.get("guid");
        Map<Long,String> map1 = new HashMap<>();
        Long timestamp1 = timestamps.get(0);
        Long timestamp2 = timestamps.get(1);
        map1.put(timestamp1, "json1");
        map1.put(timestamp2, "json2");
        ContrastRecord contrastRecord = new ContrastRecord();
        contrastRecord.setGuid(guid);
        contrastRecord.setTimestamp1(timestamp1);
        contrastRecord.setTimestamp2(timestamp2);

        AtlasClientV2 atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        List<EntityAuditEventV2> auditEventsMap = atlasClientV2.getAuditEvents(guid, null, null, (short) 100);
        //获取typeName和name
        AtlasEntity.AtlasEntityWithExtInfo entityByGuid = atlasClientV2.getEntityByGuid(guid);
        String typeName = entityByGuid.getEntity().getTypeName();
        String name = (String)entityByGuid.getEntity().getAttributes().get("name");
        contrastRecord.setName(name);
        contrastRecord.setTypeName(typeName);
        Map<Long, EntityAuditEventV2> map = new LinkedHashMap<>();
        System.out.println(auditEventsMap);
        //在这里将LinkedHashMap转为Java对象
        List<EntityAuditEventV2> auditEvents = JSON.parseArray(JSON.toJSONString(auditEventsMap), EntityAuditEventV2.class);
        for (EntityAuditEventV2 auditEvent : auditEvents) {
            for (Long timestamp : timestamps) {
                if(Objects.equals(auditEvent.getTimestamp(), timestamp)){
                    map.put(timestamp, auditEvent);

                }
            }
        }
        if(map.isEmpty()){
            return new ReturnT<>(0, "guid或时间戳出错");
        }
        //这里再创建一个map，防止再遍历原始map的时候修改原始map
        Map<Long, EntityAuditEventV2> tempMap = new LinkedHashMap<>(map);
        
        for (Map.Entry<Long, EntityAuditEventV2> entry : tempMap.entrySet()) {
            EntityAuditEventV2.EntityAuditActionV2 action = entry.getValue().getAction();
            if( EntityAuditEventV2.EntityAuditActionV2.LABEL_ADD.equals(action) ||
                    EntityAuditEventV2.EntityAuditActionV2.LABEL_DELETE.equals(action) ||
                    EntityAuditEventV2.EntityAuditActionV2.CLASSIFICATION_ADD.equals(action) ||
                    EntityAuditEventV2.EntityAuditActionV2.CLASSIFICATION_DELETE.equals(action) ||
                    EntityAuditEventV2.EntityAuditActionV2.TERM_ADD.equals(action) ||
                    EntityAuditEventV2.EntityAuditActionV2.PROPAGATED_CLASSIFICATION_ADD.equals(action)) {
                timestamps.remove(entry.getKey());
                FastJsonDiff.contrast.add(entry.getValue().getDetails());
            }
        }

        if(timestamps.size() == 2){
            String details1 = map.get(timestamp1).getDetails();
            String details2 = map.get(timestamp2).getDetails();
            int i1 = details1.indexOf("{");
            details1 = details1.substring(i1);
            int i2 = details2.indexOf("{");
            details2 = details2.substring(i2);
            JSONObject jsonObject1 = JSONObject.parseObject(details1);
            JSONObject jsonObject2 = JSONObject.parseObject(details2);
            //JSONObject jsonObjectTemp = jsonObject1;
            FastJsonDiff.compareJson(jsonObject1/*.get("attributes")*/, jsonObject2/*.get("attributes")*/, null);
            returnMap.put("json1", jsonObject1);
            returnMap.put("json2", jsonObject2);
            List<String> contrastList = new ArrayList<>(FastJsonDiff.contrast);
            returnMap.put("contrast", contrastList);
            FastJsonDiff.contrast.clear();
            contrastRecord.setRecord(returnMap.toString());
            contrastRecordService.save(contrastRecord);
            return new ReturnT<>(returnMap);
        }else if(timestamps.size() == 1){
            List<String> contrastList = new ArrayList<>(FastJsonDiff.contrast);
            EntityAuditEventV2 entityAuditEventV2 = map.get(timestamps.get(0));
            String details = entityAuditEventV2.getDetails();
            int i = details.indexOf("{");
            details = details.substring(i);
            JSONObject jsonObject = JSONObject.parseObject(details);
            String str = map1.get(timestamps.get(0));
            returnMap.put(str, jsonObject);
            if(str.equals("json1")){
                returnMap.put("json2", null);
            }else {
                returnMap.put("json1", null);
            }
            returnMap.put("contrast", contrastList);
            //清空当前数组
            FastJsonDiff.contrast.clear();
            contrastRecord.setRecord(returnMap.toString());
            contrastRecordService.save(contrastRecord);
            return new ReturnT<>(returnMap);
        }else {
            List<String> contrastList = new ArrayList<>(FastJsonDiff.contrast);
            returnMap.put("json1", null);
            returnMap.put("json2", null);
            returnMap.put("contrast", contrastList);
            FastJsonDiff.contrast.clear();
            //将此次记录保存到数据库中
            contrastRecord.setRecord(returnMap.toString());
            contrastRecordService.save(contrastRecord);
            return new ReturnT<>(returnMap);
        }
    }

    @GetMapping("/audit")
    @ApiOperation("根据guid获取该entity的所有版本信息集合，包括时间戳等")
    public ReturnT<List<EntityAuditEventV2>> getAuditEvents(@RequestParam String guid) throws AtlasServiceException {
        AtlasClientV2 atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        return new ReturnT<>(atlasClientV2.getAuditEvents(guid, null, null, (short) 100));
    }


    @GetMapping("/contrast/record")
    @ApiOperation("根据id获取版本比较结果")
    public ReturnT<String> contrastRecord(@RequestParam Long id){
        return new ReturnT<>(contrastRecordService.getById(id).getRecord());
    }

    @GetMapping("/contrast/all")
    @ApiOperation("获取所有版本比较结果")
    public ReturnT<List<ContrastRecord>> allRecord(){
        return new ReturnT<>(contrastRecordService.list());
    }

    @GetMapping("contrast/{guid}")
    @ApiOperation("根据guid查询该entity对应的所有版本比较结果")
    public ReturnT<List<ContrastRecord>> recordsByGuid(@PathVariable String guid){
        QueryWrapper<ContrastRecord> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("guid", guid);
        return new ReturnT<>(contrastRecordService.list(queryWrapper));
    }

    @PostMapping("/clickHouse/import")
    public ReturnT clickHouseMetadata(@RequestParam Long datasourceId) throws AtlasException, IOException {
        JobDatasource jobDatasource = jobJdbcDatasourceService.getById(datasourceId);
        jobDatasource.setDatabaseName(getDatabaseName(jobDatasource.getJdbcUrl()));
        MetadataBuildUtils.jobDatasource = jobDatasource;
        AtlasClientV2 atlasClientV2 = new AtlasClientV2(MetadataBuildUtils.getServerUrl(), MetadataBuildUtils.getUserInput());
        String coonId = HttpClientHelper.initConnection(jobDatasource);
        //创建instance
        MetadataBuildUtils.setRdbmsInstance(atlasClientV2);


        //创建database


        //创建table


        //创建column


        //创建index



        return ReturnT.SUCCESS;
    }

    private String getDatabaseName(String url){
        String[] split = url.split("/");
        String databaseName = split[split.length - 1];
        return databaseName;
    }
}
