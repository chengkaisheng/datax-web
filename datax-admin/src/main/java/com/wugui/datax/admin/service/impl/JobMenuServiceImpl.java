/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.wugui.datax.admin.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.constans.Constant;
import com.wugui.datax.admin.constans.MenuType;
import com.wugui.datax.admin.entity.JobMenuEntity;
import com.wugui.datax.admin.mapper.JobMenuMapper;
import com.wugui.datax.admin.mapper.JobUserMapper;
import com.wugui.datax.admin.service.JobMenuService;
import com.wugui.datax.admin.service.JobRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("sysMenuService")
public class JobMenuServiceImpl extends ServiceImpl<JobMenuMapper, JobMenuEntity> implements JobMenuService {
	@Autowired
	private JobRoleMenuService jobRoleMenuService;

	@Autowired
	private JobUserMapper jobUserMapper;

	@Override
	public List<JobMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList) {
		List<JobMenuEntity> menuList = queryListParentId(parentId);
		if(menuIdList == null){
			return menuList;
		}
		List<JobMenuEntity> userMenuList = new ArrayList<>();
		for(JobMenuEntity menu : menuList){
			if(menuIdList.contains(menu.getMenuId())){
				userMenuList.add(menu);
			}
		}
		return userMenuList;
	}

	@Override
	public List<JobMenuEntity> queryListParentId(Long parentId) {
		return baseMapper.queryListParentId(parentId);
	}

	@Override
	public List<JobMenuEntity> queryNotButtonList() {
		return baseMapper.queryNotButtonList();
	}

	@Override
	public List<JobMenuEntity> getUserMenuList(Long userId) {
		if(userId == Constant.SUPER_ADMIN){
			return getAllMenuList(null);
		}
		List<Long> list = jobUserMapper.queryAllMenuId(userId);
		return getAllMenuList(list);
	}

	@Override
	public void delete(Long menuId){
		//删除菜单
		this.removeById(menuId);
		//删除菜单与角色关联
		Map<String,Object> map = new HashMap<>();
		map.put("menu_id", menuId);
		jobRoleMenuService.removeByMap(map);
	}

	/**
	 * 获取所有菜单列表
	 */
	public List<JobMenuEntity> getAllMenuList(List<Long> menuIdList){
		//查询根菜单列表
		List<JobMenuEntity> menuList = queryListParentId(0L, menuIdList);
		//递归获取子菜单
		getMenuTreeList(menuList, menuIdList);

		return menuList;
	}

	/**
	 * 递归
	 */
	private List<JobMenuEntity> getMenuTreeList(List<JobMenuEntity> menuList, List<Long> menuIdList){
		List<JobMenuEntity> subMenuList = new ArrayList<JobMenuEntity>();

		for(JobMenuEntity entity : menuList){
			//目录
			Map<String,String> mata = new HashMap<>();
			mata.put("title",entity.getTitle());
			mata.put("icon", entity.getIcon());
			entity.setMeta(mata);
			if(entity.getType() == MenuType.CATALOG.getValue()){
				entity.setChildren(getMenuTreeList(queryListParentId(entity.getMenuId(), menuIdList), menuIdList));
			}
			subMenuList.add(entity);
		}

		return subMenuList.size() == 0?null:subMenuList;
	}
}
