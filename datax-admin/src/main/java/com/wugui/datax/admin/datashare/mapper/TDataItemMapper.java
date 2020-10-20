package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TDataItem;
import com.wugui.datax.admin.datashare.entity.TDataItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TDataItemMapper {
    int countByExample(TDataItemExample example);

    int deleteByExample(TDataItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDataItem record);

    int insertSelective(TDataItem record);

    List<TDataItem> selectByExample(TDataItemExample example);

    TDataItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDataItem record, @Param("example") TDataItemExample example);

    int updateByExample(@Param("record") TDataItem record, @Param("example") TDataItemExample example);

    int updateByPrimaryKeySelective(TDataItem record);

    int updateByPrimaryKey(TDataItem record);
}