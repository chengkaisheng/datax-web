package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TInterface;
import com.wugui.datax.admin.datashare.entity.TInterfaceExample;
import com.wugui.datax.admin.datashare.entity.TInterfaceWithBLOBs;
import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import com.wugui.datax.admin.datashare.vo.InterfaceResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TInterfaceMapper {
    int countByExample(TInterfaceExample example);

    int deleteByExample(TInterfaceExample example);

    int deleteByPrimaryKey(String id);

    int insert(TInterfaceWithBLOBs record);

    int insertSelective(TInterfaceWithBLOBs record);

    List<TInterfaceWithBLOBs> selectByExampleWithBLOBs(TInterfaceExample example);

    List<TInterface> selectByExample(TInterfaceExample example);

    TInterfaceWithBLOBs selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TInterfaceWithBLOBs record,
                                 @Param("example") TInterfaceExample example);

    int updateByExampleWithBLOBs(@Param("record") TInterfaceWithBLOBs record, @Param("example") TInterfaceExample example);

    int updateByExample(@Param("record") TInterface record, @Param("example") TInterfaceExample example);

    int updateByPrimaryKeySelective(TInterfaceWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(TInterfaceWithBLOBs record);

    int updateByPrimaryKey(TInterface record);

    List<InterfaceResult> getInterfaceResult(ApplyResourceQuery applyResourceQuery);

    int getInterfaceTotal(ApplyResourceQuery applyResourceQuery);

}