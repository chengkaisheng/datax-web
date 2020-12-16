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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;



/**
 * @author 27441
 */
@RestController
@RequestMapping("/api/jobMenu")
public class JobMenuController {

	@Autowired
	private JobMenuService jobMenuService;


	
	/**
	 * 所有菜单列表
	 */
	@GetMapping("/list")
	public List<JobMenuEntity> list(){
		List<JobMenuEntity> menuList = jobMenuService.list();
		for(JobMenuEntity sysMenuEntity : menuList){
			JobMenuEntity parentMenuEntity = jobMenuService.getById(sysMenuEntity.getParentId());
			if(parentMenuEntity != null){
				sysMenuEntity.setParentName(parentMenuEntity.getName());
			}
		}
		return menuList;
	}
	
	/**
	 * 选择菜单(添加、修改菜单)
	 */
	@GetMapping("/select")
	public R select(){
		//查询列表数据
		List<JobMenuEntity> menuList = jobMenuService.queryNotButtonList();

		//添加顶级菜单
		JobMenuEntity root = new JobMenuEntity();
		root.setMenuId(0L);
		root.setName("一级菜单");
		root.setParentId(-1L);
		root.setOpen(true);
		menuList.add(root);

		return R.ok(menuList);
	}
	
	/**
	 * 菜单信息
	 */
	@GetMapping("/info/{menuId}")
	public R info(@PathVariable("menuId") Long menuId){
		JobMenuEntity menu = jobMenuService.getById(menuId);
		return R.ok(menu);
	}
	
	/**
	 * 保存
	 */
	@PostMapping("/save")
	public R save(@RequestBody JobMenuEntity menu){
		jobMenuService.save(menu);
		return R.ok("保存成功");
	}
	
	/**
	 * 修改
	 */
	@PostMapping("/update")
	public R update(@RequestBody JobMenuEntity menu){
		jobMenuService.updateById(menu);

		return R.ok("更新成功");
	}
	
	/**
	 * 删除
	 */
	@PostMapping("/delete/{menuId}")
	public R delete(@PathVariable("menuId") long menuId){
		if(menuId <= 31){
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

}
