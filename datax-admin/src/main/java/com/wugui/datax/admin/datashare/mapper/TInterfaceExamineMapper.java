package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TInterfaceExamine;
import com.wugui.datax.admin.datashare.entity.TInterfaceExamineExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TInterfaceExamineMapper {
    int countByExample(TInterfaceExamineExample example);

    int deleteByExample(TInterfaceExamineExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TInterfaceExamine record);

    int insertSelective(TInterfaceExamine record);

    List<TInterfaceExamine> selectByExample(TInterfaceExamineExample example);

    TInterfaceExamine selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TInterfaceExamine record,
                                 @Param("example") TInterfaceExamineExample example);

    int updateByExample(@Param("record") TInterfaceExamine record, @Param("example") TInterfaceExamineExample example);

    int updateByPrimaryKeySelective(TInterfaceExamine record);

    int updateByPrimaryKey(TInterfaceExamine record);
}