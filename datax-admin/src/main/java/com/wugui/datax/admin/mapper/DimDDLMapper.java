package com.wugui.datax.admin.mapper;

import com.wugui.datax.admin.entity.DimDDL;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * @program: datax-web
 * @description
 * @author: lxq
 * @create: 2020-12-15 15:52
 **/
@Mapper
public interface DimDDLMapper {
    DimDDL select(@Param("source") String source,@Param("target")  String target,@Param("sourceType") String sourceType);
}
