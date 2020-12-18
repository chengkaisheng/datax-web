package com.wugui.datax.admin.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.constans.Constant;
import com.wugui.datax.admin.entity.JobUser;
import com.wugui.datax.admin.mapper.JobUserMapper;
import com.wugui.datax.admin.service.JobRoleService;
import com.wugui.datax.admin.service.JobUserRoleService;
import com.wugui.datax.admin.service.JobUserService;
import com.wugui.datax.admin.util.DataXException;
import com.wugui.datax.admin.util.Query;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Service
public class JobUserServiceImpl extends ServiceImpl<JobUserMapper, JobUser> implements JobUserService {
	@Autowired
	private JobUserRoleService jobUserRoleService;

	@Autowired
	private JobRoleService jobRoleService;

	@Override
	public IPage<JobUser> queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		Integer createUserId = (Integer)params.get("createUserId");

		IPage<JobUser> page = this.page(
			new Query<JobUser>().getPage(params),
			new QueryWrapper<JobUser>()
				.like(StringUtils.isNotBlank(username),"username", username)
				.eq(createUserId != null,"create_user_id", createUserId)
		);
		List<JobUser> users = page.getRecords();
		if(users != null && users.size() != 0){
			users.forEach(user->{
				List<String> roleNames = jobUserRoleService.queryRoleNames((long) user.getId());
				List<Long> roleIds = jobUserRoleService.queryRoleIdList((long) user.getId());
				user.setRoleIdList(roleIds);
				user.setRoleName(roleNames);
				user.setPassword(null);
			});
		}
		return page;
	}

	@Override
	public List<String> queryAllPerms(Long userId) {
		return baseMapper.queryAllPerms(userId);
	}

	@Override
	public List<Long> queryAllMenuId(Long userId) {
		return baseMapper.queryAllMenuId(userId);
	}

	@Override
	public JobUser queryByUserName(String username) {
		JobUser jobUser = baseMapper.queryByUserName(username);
		List<String> roleNames = jobUserRoleService.queryRoleNames((long) jobUser.getId());
		if(roleNames == null || roleNames.size() != 0){
			jobUser.setRoleName(Collections.emptyList());
			jobUser.setRole("1");
		}
		jobUser.setRole("1");
		return jobUser;
	}

	@Override
	@Transactional
	public void saveUser(JobUser user) {

		this.save(user);
		
		//检查角色是否越权
		checkRole(user);
		
		//保存用户与角色关系
		jobUserRoleService.saveOrUpdate((long) user.getId(), user.getRoleIdList());
	}

	@Override
	@Transactional
	public void update(JobUser user) {
		this.updateById(user);
		//检查角色是否越权
		checkRole(user);
		//保存用户与角色关系
		jobUserRoleService.saveOrUpdate((long) user.getId(), user.getRoleIdList());
	}

	@Override
	public void deleteBatch(Long[] userId) {
		this.removeByIds(Arrays.asList(userId));
	}

	@Override
	public boolean updatePassword(Long userId, String password, String newPassword) {
		JobUser userEntity = new JobUser();
		userEntity.setPassword(newPassword);
		return this.update(userEntity,
				new QueryWrapper<JobUser>().eq("user_id", userId).eq("password", password));
	}
	
	/**
	 * 检查角色是否越权
	 */
	private void checkRole(JobUser user){
		if(user.getRoleIdList() == null || user.getRoleIdList().size() == 0){
			return;
		}
		//如果不是超级管理员，则需要判断用户的角色是否自己创建
		if(user.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户创建的角色列表
		List<Long> roleIdList = jobRoleService.queryRoleIdList(user.getCreateUserId());

		//判断是否越权
		if(!roleIdList.containsAll(user.getRoleIdList())){
			throw new DataXException("新增用户所选角色，不是本人创建");
		}
	}
}