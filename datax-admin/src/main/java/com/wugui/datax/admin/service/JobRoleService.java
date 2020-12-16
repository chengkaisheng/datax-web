
package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.JobRole;
import com.wugui.datax.admin.util.PageUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * @author hf
 * @creat 2020-11-13-18:47
 */

public interface JobRoleService extends IService<JobRole> {
    IPage<JobRole> queryPage(Map<String, Object> params);

    void saveRole(JobRole role);

    void update(JobRole role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}

