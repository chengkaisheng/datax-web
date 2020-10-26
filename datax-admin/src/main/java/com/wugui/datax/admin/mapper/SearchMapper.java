package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.Search;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by iwlnner on 2020/9/11.
 */
@Mapper
public interface SearchMapper {

    void saveSearch(Search search);

    List<Search> listSearch(@Param("keyword") String keyword,@Param("pageNum") Integer pageNum,@Param("pageSize") Integer pageSize);

    Long getCountByTableName(String tableName);

    Search getSearchById(Long id);

    void removeSearchById(Long id);
}
