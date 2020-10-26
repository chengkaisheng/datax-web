package com.wugui.datax.admin.service;


import com.wugui.datax.admin.entity.Search;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Created by iwlnner on 2020/9/12.
 */
public interface ISearchService {
    Map<String,Object> createSearch(Search search, MultipartFile multipartFile);

    List<Search> listSearchs(String keyword, Integer pageNum, Integer pageSize);

    Search getSearchById(Long id);

    void remove(Long id);
}
