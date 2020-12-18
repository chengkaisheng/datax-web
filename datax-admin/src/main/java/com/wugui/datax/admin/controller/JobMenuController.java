/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.wugui.datax.admin.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.JobMenuEntity;
import com.wugui.datax.admin.service.JobMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 27441
 */
@RestController
@RequestMapping("/api/jobMenu")
@Api(tags = "权限接口")
public class JobMenuController {

	@Autowired
	private JobMenuService jobMenuService;

	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
	@ApiOperation("权限列表")
	public R list(){
		List<JobMenuEntity> menuList = jobMenuService.list();
		for(JobMenuEntity sysMenuEntity : menuList){
			JobMenuEntity parentMenuEntity = jobMenuService.getById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}
		return R.ok(menuList);
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
	@ApiOperation("权限列表")
	public R select(){
		//查询列表数据
		List<JobMenuEntity> menuList = jobMenuService.queryNotButtonList();
		List<JobMenuEntity> parentMenu = menuList.stream().filter(item ->item.getParentId() == 0)
				.collect(Collectors.toList());
		parentMenu.forEach(item->{
			setChildrenMenu(item, jobMenuService.queryListParentId(item.getMenuId()));
			/*menuList.forEach(children->{
				if(children.getParentId().equals(item.getMenuId())){
					ArrayList<JobMenuEntity> menuEntities = new ArrayList<>();
					jobMenuService.queryListParentId(children.getMenuId()).forEach(innerChild->{
						if(innerChild.getParentId().equals(children.getMenuId())){
							menuEntities.add(innerChild);
						}
					});
					children.setChildren(menuEntities);
					jobMenuEntities.add(children);
				}
			});
			item.setChildren(jobMenuEntities);*/
		});
		return R.ok(parentMenu);
	}
	
	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
	@ApiOperation("菜单信息列表")
	public R info(@PathVariable("menuId") Long menuId){
		JobMenuEntity menu = jobMenuService.getById(menuId);
		return R.ok(menu);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	@ApiOperation("保存权限")
	public R save(@RequestBody JobMenuEntity menu){
		if(menu.getParentId() == null || menu.getParentId() == 0){
			menu.setParentId(0L);
			menu.setType(0);
		}
		if (menu.getType() == null){
			menu.setType(1);
		}
		jobMenuService.save(menu);
		return R.ok("保存成功");
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	@ApiOperation("更新权限")
	public R update(@RequestBody JobMenuEntity menu){
		jobMenuService.updateById(menu);

		return R.ok("更新成功");
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete/{menuId}")
	@ApiOperation("删除权限")
	public R delete(@PathVariable("menuId") long menuId){
		if(menuId <= 37){
			return R.failed("系统菜单，不能删除");
		}

		//判断是否有子菜单或按钮
		List<JobMenuEntity> menuList = jobMenuService.queryListParentId(menuId);
		if(menuList.size() > 0){
			return R.failed("请先删除子菜单或按钮");
		}

		jobMenuService.delete(menuId);

		return R.ok("删除成功");
	}

	public void setChildrenMenu(JobMenuEntity jobMenuEntity, List<JobMenuEntity> childList){
		if(childList == null || childList.size() == 0){
			return;
		}
		childList.forEach(child->{
			setChildrenMenu(child, jobMenuService.queryListParentId(child.getMenuId()));
		});
		jobMenuEntity.setChildren(childList);
	}

}
