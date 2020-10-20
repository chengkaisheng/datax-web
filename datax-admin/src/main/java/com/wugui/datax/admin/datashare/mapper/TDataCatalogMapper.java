package com.wugui.datax.admin.datashare.mapper;


import com.wugui.datax.admin.datashare.entity.TDataCatalog;
import com.wugui.datax.admin.datashare.entity.TDataCatalogExample;
import com.wugui.datax.admin.datashare.vo.OrganizationCatalog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TDataCatalogMapper {
    int countByExample(TDataCatalogExample example);

    int deleteByExample(TDataCatalogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDataCatalog record);

    int insertSelective(TDataCatalog record);

    List<TDataCatalog> selectByExample(TDataCatalogExample example);

    TDataCatalog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TDataCatalog record, @Param("example") TDataCatalogExample example);

    int updateByExample(@Param("record") TDataCatalog record, @Param("example") TDataCatalogExample example);

    int updateByPrimaryKeySelective(TDataCatalog record);

    int updateByPrimaryKey(TDataCatalog record);


    List<String> getTDataCatalogServerName();

    List<OrganizationCatalog> getOrganizationCatalog();

    List<OrganizationCatalog> getOrganizationCatalogTop();

    TDataCatalog selectByInfoId(String infoId);
}