package com.wugui.datax.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wugui.datax.admin.entity.PersonaliseRule;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 个性化规则表
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
@Mapper
public interface PersonaliseRuleMapper extends BaseMapper<PersonaliseRule> {

    List<PersonaliseRule> pageList(@Param("offSet")int offSet, @Param("pageSize") int pageSize, @Param("type") Integer type, @Param("name") String name,@Param("joinType") Integer joinType);

    int pageListCount(@Param("name") String name, @Param("type") Integer type,@Param("joinType") Integer joinType);

    int getPersonalRule(@Param("userId") Integer userId);

    void updateIsDelete(@Param("id") Integer id);

    int selectCountByCode(@Param("code") String code);

    PersonaliseRule selectByCode(@Param("code") String codeTemp);
}
