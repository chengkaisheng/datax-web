package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.Search;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by iwlnner on 2020/9/11.
 */
@Mapper
public interface SearchMapper {

    void saveSearch(Search search);

    List<Search> listSearch();

    Long getCountByTableName(String tableName);

    Search getSearchById(Long id);
}
