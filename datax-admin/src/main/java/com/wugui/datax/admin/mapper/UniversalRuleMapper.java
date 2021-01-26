package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.UniversalRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 通用规则表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
@Mapper
public interface UniversalRuleMapper extends BaseMapper<UniversalRule> {

    List<UniversalRule> pageList(@Param("offset") int offset, @Param("pageSize") int pageSize, @Param("name") String name);

    int pageListCount(@Param("name") String name);

    int getGeneralRule(@Param("userId") Integer userId);

    void updateIsDelete(@Param("id") Integer id);

    List<UniversalRule> selectUniversalNameByType(@Param("type")Integer type);

    List<UniversalRule> selectUniverToPerson();

    int selectCountByCode(@Param("code") String code);

    UniversalRule selectByCode(@Param("code")String code);

    List<Map<String,Object>> getUsedRuleDistribution(@Param("userId") Integer userId);
}
