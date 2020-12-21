package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.ColumnMsg;
import com.wugui.datax.admin.entity.HiveParameter;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.tool.query.HiveQueryTool;
import com.wugui.datax.admin.util.UUIDUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询数据库表名，字段的控制器
 *
 * @author jingwk
 * @ClassName MetadataController
 * @Version 2.1.2
 * @since 2020/05/31 20:48
 */
@RestController
@RequestMapping("api/metadata")
@Api(tags = "jdbc数据库查询控制器")
public class MetadataController extends BaseController {

    @Autowired
    private DatasourceQueryService datasourceQueryService;

    /**
     * 根据数据源id获取mongo库名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBs")
    @ApiOperation("根据数据源id获取mongo库名")
    public R<List<String>> getDBs(Long datasourceId) throws IOException {
        return success(datasourceQueryService.getDBs(datasourceId));
    }


    /**
     * 根据数据源id,dbname获取CollectionNames
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/collectionNames")
    @ApiOperation("根据数据源id,dbname获取CollectionNames")
    public R<List<String>> getCollectionNames(Long datasourceId,String dbName) throws IOException {
        return success(datasourceQueryService.getCollectionNames(datasourceId,dbName));
    }

    /**
     * 获取PG table schema
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getDBSchema")
    @ApiOperation("根据数据源id获取 db schema")
    public R<List<String>> getTableSchema(Long datasourceId) {
        return success(datasourceQueryService.getTableSchema(datasourceId));
    }

    /**
     * 根据数据源id获取可用表名
     *
     * @param datasourceId
     * @return
     */
    @GetMapping("/getTables")
    @ApiOperation("根据数据源id获取可用表名")
    public R<List<String>> getTableNames(Long datasourceId,String tableSchema) throws IOException {
        logger.info("888888888888888888888888:"+tableSchema);
        return success(datasourceQueryService.getTables(datasourceId,tableSchema));
    }

    /**
     * 根据数据源id和表名获取所有字段
     *
     * @param datasourceId 数据源id
     * @param tableName    表名
     * @return
     */
    @GetMapping("/getColumns")
    @ApiOperation("根据数据源id和表名获取所有字段")
    public R<List<String>> getColumns(Long datasourceId, String tableName) throws IOException {
        return success(datasourceQueryService.getColumns(datasourceId, tableName));
    }

    /**
     * 根据数据源id和表名获取字段的名称,类型，comment
     *
     * @param datasourceId 数据源id
     * @param tableName    表名
     * @return
     */
    @GetMapping("/getTableColumns")
    @ApiOperation("根据数据源id和表名获取所有字段名称，类型，comment")
    public R<Object> getTableColumns(Long datasourceId,String schema, String tableName) throws IOException {
        return success(datasourceQueryService.getTableColumns(datasourceId, schema, tableName));
    }

    /**
     * 根据数据源id和表名获取字段的名称,类型，comment
     *
     * @param hiveParameter
     * @return
     */
    @PostMapping("/dbToHive")
    @ApiOperation("根据数据源id和表名获取所有字段名称，类型，comment")
    public R<Object> dbToHive(@RequestBody HiveParameter hiveParameter) throws IOException {
        Map map =new HashMap();
        if(UUIDUtils.isEmpty(hiveParameter.getSchema())){
            map.put("code","500");
            map.put("data","数据库schema不能为空");
            return success(map);
        }
        if(UUIDUtils.isEmpty(hiveParameter.getVersion())){
            map.put("code","500");
            map.put("data","请选择hive版本");
            return success(map);
        }
        return success(datasourceQueryService.dbToHive(hiveParameter));
    }

    /**
     * 根据数据源id和sql语句获取所有字段
     *
     * @param datasourceId 数据源id
     * @param querySql     表名
     * @return
     */
    @GetMapping("/getColumnsByQuerySql")
    @ApiOperation("根据数据源id和sql语句获取所有字段")
    public R<List<String>> getColumnsByQuerySql(Long datasourceId, String querySql) throws SQLException {
        return success(datasourceQueryService.getColumnsByQuerySql(datasourceId, querySql));
    }

    /**
     * 根据数据源id和表名获取记录数
     * @param datasourceId 数据源id
     * @param tableName 表名
     * @return
     * @throws SQLException
     */
    @GetMapping("/getRows")
    @ApiOperation("根据数据源id和表名获取记录数")
    public R<Long> getRowsByTableName(Long datasourceId, String tableName) throws Exception {
        return success(datasourceQueryService.getRows(datasourceId,tableName));
    }

    /**
     * 根据数据源id和表名获取表所有数据
     * @param datasourceId 数据源id
     * @param tableName 表名
     * @param pageNumber 当前页数
     * @param pageSize 每页显示的记录数
     * @return
     * @throws Exception
     */
    @RequestMapping("/listAll")
    @ApiOperation("根据数据源id和表名获取表所有数据")
    public R<Map<String,Object>> listALLByTableName(Long datasourceId, String tableName, @RequestParam(required = false,defaultValue ="1") Integer pageNumber, @RequestParam(required = false,defaultValue = "10") Integer pageSize) throws Exception {
        return success(datasourceQueryService.listAll(datasourceId,tableName,pageNumber,pageSize));
    }

    /**
     * 根据数据源id和表名获取表所有字段名和字段类型以及字段统计结果
     * @param datasourceId 数据源id
     * @param tableName 表名
     * @return
     * @throws Exception
     */
    @RequestMapping("/getColumnSchema")
    @ApiOperation("根据数据源id和表名获取表所有字段名和字段类型以及字段统计结果")
    public R<List<ColumnMsg>> getColumnSchema(Long datasourceId, String tableName) throws Exception {
        return success(datasourceQueryService.getColumnSchema(datasourceId,tableName));
    }

    @RequestMapping("/getTableSize")
    @ApiOperation("根据数据源id和表名获取表所有字段名和字段类型以及字段统计结果")
    public R<String> getTableSize(Long datasourceId, String tableName) throws Exception {
        return success(datasourceQueryService.getTableSize(datasourceId,tableName));
    }

    @GetMapping("/hive/pathDefault")
    public R<Map<String,String>> getHivePathDefault(Long datasourceId, String schema, String tableName){
        return R.ok(datasourceQueryService.getHivePathDefault(datasourceId, schema, tableName));
    }

}
