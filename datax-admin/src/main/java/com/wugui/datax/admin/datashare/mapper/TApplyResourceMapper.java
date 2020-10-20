package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TApplyResource;
import com.wugui.datax.admin.datashare.entity.TApplyResourceExample;
import com.wugui.datax.admin.datashare.vo.ApplyResourceQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.tools.ant.types.resources.ResourceList;

import java.util.List;
@Mapper
public interface TApplyResourceMapper {
    int countByExample(TApplyResourceExample example);

    int deleteByExample(TApplyResourceExample example);

    int deleteByPrimaryKey(String id);

    int insert(TApplyResource record);

    int insertSelective(TApplyResource record);

    List<TApplyResource> selectByExample(TApplyResourceExample example);

    TApplyResource selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") TApplyResource record,
                                 @Param("example") TApplyResourceExample example);

    int updateByExample(@Param("record") TApplyResource record, @Param("example") TApplyResourceExample example);

    int updateByPrimaryKeySelective(TApplyResource record);

    int updateByPrimaryKey(TApplyResource record);

    List<ResourceList> getResourceList(ApplyResourceQuery applyResourceQuery);

    int getResourceListTotal(ApplyResourceQuery applyResourceQuery);
}