package com.wugui.datax.admin.service.impl;


import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Search;
import com.wugui.datax.admin.mapper.JobDatasourceMapper;
import com.wugui.datax.admin.mapper.SearchMapper;
import com.wugui.datax.admin.service.DatasourceQueryService;
import com.wugui.datax.admin.service.ISearchService;
import com.wugui.datax.admin.util.JdbcUtils;
import com.wugui.datax.admin.util.UploadUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Driver;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by iwlnner on 2020/9/12.
 */
@Service
public class SearchServiceImpl implements ISearchService {

    @Autowired
    private SearchMapper searchMapper;
    @Autowired
    private DatasourceQueryService datasourceQueryService;
    @Value("${upload.url}")
    private String url;
    @Value("${upload.service-url}")
    private String serviceUrl;


    public Map<String,Object> createSearch(Search search,MultipartFile multipartFile){
        /*Map<String,Object> uploadResult=UploadUtils.uploadImage(url,serviceUrl,multipartFile);
        if((Integer)uploadResult.get("error")==0){
            search.setImageUrl(uploadResult.get("imageUrl").toString());
            searchMapper.saveSearch(search);
        }*/
        searchMapper.saveSearch(search);
        Map<String,Object> uploadResult=new HashMap(){{this.put("200","新增成功！");}};
        return uploadResult;
    }

    public List<Search> listSearchs(){
        List<Search> searches=searchMapper.listSearch();
        return searches;
    }

    //根据探查数据id获取探查信息
    public Search getSearchById(Long id){
        Search search=searchMapper.getSearchById(id);
        Long datasourceId=search.getJdbcDatasourceId();
        String tableName=search.getTableName();
        datasourceQueryService.getRows(datasourceId,tableName);
        search.setRows(datasourceQueryService.getRows(datasourceId,tableName));
        search.setSize(datasourceQueryService.getTableSize(datasourceId,tableName).getSize());
        return search;
    }
}
