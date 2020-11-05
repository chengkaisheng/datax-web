package com.wugui.datax.admin.tool.query;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;
import org.apache.commons.lang.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.wugui.datax.admin.datashare.tools.ResultToJsonUtil.resultSetToJSON;

/**
 * hive
 *
 * @author wenkaijing
 * @version 2.0
 * @since 2020/01/05
 */
public class HiveQueryTool extends BaseQueryTool implements QueryToolInterface {
    public HiveQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    @Override
    public Object getTableColumns(String tableName, String datasource,String databaseName) {

        Map res = new HashMap();
        Statement stmt = null;
        ResultSet rs = null;
        Map<String, List<Map<String,String>>> result = new HashMap<>();
        List<Map<String,String>> list = new ArrayList<>();
        try {
            if(StringUtils.isEmpty(databaseName)){
                String strsql="select database()";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(strsql);
                JSON dataBaseNameJson= resultSetToJSON(rs);
                JSONArray jsonArray = JSONArray.parseArray(dataBaseNameJson.toJSONString());
                if(jsonArray.size()>0){
                    JSONObject job = jsonArray.getJSONObject(0);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
                    databaseName=job.get("database()").toString();  // 得到 每个对象中的属性值
                }
            }
            //获取查询指定表所有字段的sql语句
            String querySql = "desc "+databaseName+"."+tableName;
            logger.info("querySql: {}", querySql);

            //获取所有字段的名称，类型，comment
            stmt = connection.createStatement();
            rs = stmt.executeQuery(querySql);

            while (rs.next()){
                Map<String,String> map = new HashMap<>();
                map.put("COLUMN_NAME", rs.getString(1));
                map.put("DATA_TYPE", rs.getString(2));
                map.put("COLUMN_COMMENT", rs.getString(3));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTableColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
            res.put("error","[getTableColumnNames Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        result.put("datas", list);
        return result;
    }
}
