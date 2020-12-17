
package com.wugui.datax.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobRole;
import com.wugui.datax.admin.mapper.JobRoleMapper;
import com.wugui.datax.admin.service.JobRoleMenuService;
import com.wugui.datax.admin.service.JobRoleService;
import com.wugui.datax.admin.service.JobUserRoleService;
import com.wugui.datax.admin.util.DataXException;
import com.wugui.datax.admin.util.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author hf
 * @creat 2020-11-13-18:47
 */

@Service
public class JobRoleServiceImpl extends ServiceImpl<JobRoleMapper, JobRole> implements JobRoleService {

    @Autowired
    private JobRoleMenuService jobRoleMenuService;
    @Autowired
    private JobUserRoleService jobUserRoleService;

    @Override
    public IPage<JobRole> queryPage(Map<String, Object> params) {
        String roleName = (String)params.get("roleName");
        Long createUserId = (Long)params.get("createUserId");

        IPage<JobRole> page = this.page(
                new Query<JobRole>().getPage(params),
                new QueryWrapper<JobRole>()
                        .like(StringUtils.isNotBlank(roleName),"role_name", roleName)
                        .eq(createUserId != null,"create_user_id", createUserId)
        );
        page.getRecords().forEach(item->item.setMenuIdList(jobRoleMenuService.queryMenuIdList(item.getRoleId())));
        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(JobRole role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        jobRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(JobRole role) {
        this.updateById(role);
        if(role.getMenuIdList() == null || role.getMenuIdList().size() == 0){
            return;
        }
        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        jobRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    public void deleteBatch(Long[] roleIds) {
//删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        jobRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        jobUserRoleService.deleteBatch(roleIds);
    }

    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }
    
    /**
     * 检查权限是否越权
     */
    private void checkPrems(JobRole role){
        //如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
        if(role.getCreateUserId() == 1){
            return ;
        }

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = null;

        //判断是否越权
        if(!menuIdList.containsAll(role.getMenuIdList())){
            throw new DataXException("新增角色的权限，已超出你的权限范围");
        }
    }
}

