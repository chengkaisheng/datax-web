package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TDatabaseInfo;
import com.wugui.datax.admin.datashare.entity.TDatabaseInfoExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TDatabaseInfoMapper {
    int countByExample(TDatabaseInfoExample example);

    int deleteByExample(TDatabaseInfoExample example);

    int deleteByPrimaryKey(String id);

    int insert(TDatabaseInfo record);

    int insertSelective(TDatabaseInfo record);

    List<TDatabaseInfo> selectByExample(TDatabaseInfoExample example);

    TDatabaseInfo selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TDatabaseInfo record, @Param("example") TDatabaseInfoExample example);

    int updateByExample(@Param("record") TDatabaseInfo record, @Param("example") TDatabaseInfoExample example);

    int updateByPrimaryKeySelective(TDatabaseInfo record);

    int updateByPrimaryKey(TDatabaseInfo record);

    List<TDatabaseInfo> getDataBaseInfoServerName();
}