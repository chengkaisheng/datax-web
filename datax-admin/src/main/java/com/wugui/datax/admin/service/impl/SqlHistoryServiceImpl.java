package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.SqlHistory;
import com.wugui.datax.admin.mapper.SqlHistoryMapper;
import com.wugui.datax.admin.service.SqlHistoryService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author hf
 * @creat 2021-03-10-16:49
 */
@Service
public class SqlHistoryServiceImpl extends ServiceImpl<SqlHistoryMapper, SqlHistory> implements SqlHistoryService {

    @Resource
    private SqlHistoryMapper sqlHistoryMapper;

    @Override
    public Page<SqlHistory> queryPage(Map<String, String> params) {
        String projectId = params.get("projectId");
        String datasourceId = params.get("datasourceId");
        String schema = params.get("databaseSchema");
        String size = params.get("size");
        String current = params.get("current");
        String isSaved = params.get("isSaved");
        if(StringUtils.isBlank(projectId) || StringUtils.isBlank(datasourceId)
        ||StringUtils.isBlank(schema) || StringUtils.isBlank(size) || StringUtils.isBlank(current)){
            throw new RuntimeException("查询异常") ;
        }
        int projectIdI = Integer.parseInt(projectId);
        int datasourceIdI = Integer.parseInt(datasourceId);
        int sizeI = Integer.parseInt(size);
        int currentI = Integer.parseInt(current);
        int isSavedI = 0;
        if(StringUtils.isNotBlank(isSaved)){
            isSavedI = Integer.parseInt(isSaved);
        }
        return sqlHistoryMapper.selectPage(new Page<SqlHistory>(currentI, sizeI), new QueryWrapper<SqlHistory>()
                .eq("project_id", projectIdI).eq("datasource_id", datasourceIdI).eq("database_schema", schema).eq("is_saved", isSavedI).orderByDesc("submit_time"));
    }
}
