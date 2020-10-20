package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TResourceExamine;
import com.wugui.datax.admin.datashare.entity.TResourceExamineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TResourceExamineMapper {
    int countByExample(TResourceExamineExample example);

    int deleteByExample(TResourceExamineExample example);

    int deleteByPrimaryKey(String id);

    int insert(TResourceExamine record);

    int insertSelective(TResourceExamine record);

    List<TResourceExamine> selectByExample(TResourceExamineExample example);

    TResourceExamine selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TResourceExamine record,
                                 @Param("example") TResourceExamineExample example);

    int updateByExample(@Param("record") TResourceExamine record, @Param("example") TResourceExamineExample example);

    int updateByPrimaryKeySelective(TResourceExamine record);

    int updateByPrimaryKey(TResourceExamine record);
}