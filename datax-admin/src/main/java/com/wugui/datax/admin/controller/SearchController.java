package com.wugui.datax.admin.controller;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobDatasource;
import com.wugui.datax.admin.entity.Search;
import com.wugui.datax.admin.service.ISearchService;
import com.wugui.datax.admin.service.JobDatasourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by iwlnner on 2020/9/11.create
 */
@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private JobDatasourceService jobDatasourceService;
    @Autowired
    private ISearchService searchService;

    @RequestMapping("/getCreateMsg")
    public ReturnT<List<JobDatasource>> getCreateMsg(String username){
        List<JobDatasource>  jobDatasources=jobDatasourceService.listDataSource(username);
        return new ReturnT<>(jobDatasources);
}

    /**
     * 新增探查数据
     * @param search
     * @param multipartFile
     * @return
     */
    @RequestMapping("/create")
    public ReturnT<Map<String,Object>> createSearch(Search search, MultipartFile multipartFile){
        return new ReturnT<>(searchService.createSearch(search,multipartFile));
    }

    /**
     * 查询所有探查列表
     * @return
     */
    @RequestMapping("/list")
    public ReturnT<List<Search>> listSearch(){
        List<Search> searches=searchService.listSearchs();
        return new ReturnT<>(searches);
    }

    /**
     * 根据探查数据id获取探查数据
     * @param id 探查数据id
     * @return 探查数据详细信息
     */
    @RequestMapping("/getSearchById")
    public ReturnT<Search> getSearchById(Long id){
        Search search=searchService.getSearchById(id);
        return new ReturnT<>(search);
    }
}
