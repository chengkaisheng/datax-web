package com.wugui.datax.admin.tool.query;

import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.util.JdbcUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @author zhouhongfa@gz-yibo.com
 * @ClassName PostgresqlQueryTool
 * @Version 1.0
 * @since 2019/8/2 11:28
 */
public class PostgresqlQueryTool extends BaseQueryTool implements QueryToolInterface {
    public PostgresqlQueryTool(JobDatasource jobDatasource) throws SQLException {
        super(jobDatasource);
    }

    @Override
    public List<Map<String, Object>> getTablesInfo(String schema) {
        Statement stmt = null;
        ResultSet rs = null;

        List<Map<String,Object>> list = new ArrayList<>();
        try {
            stmt = connection.createStatement();
            //获取sql
            String sql = sqlBuilder.getSQLQueryTablesNameComments(schema);
            rs = stmt.executeQuery(sql);
            while (rs.next()){
                Map<String,Object> map= new HashMap<>();
                map.put("table_name", rs.getString(1));
                map.put("table_comment", rs.getString(2));
                list.add(map);
            }
        } catch (SQLException e) {
            logger.error("[getTablesInfo Exception] --> "
                    + "the exception message is:" + e.getMessage());
        } finally {
            JdbcUtils.close(rs);
            JdbcUtils.close(stmt);
        }
        return list;
    }

}
