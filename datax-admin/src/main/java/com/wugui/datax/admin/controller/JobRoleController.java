package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datax.admin.entity.JobRole;
import com.wugui.datax.admin.service.JobRoleMenuService;
import com.wugui.datax.admin.service.JobRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/jobRole")
@Api("角色管理接口")
public class JobRoleController extends BaseController{

    @Autowired
	private JobRoleService jobRoleService;
	@Autowired
	private JobRoleMenuService jobRoleMenuService;

	/**
	 * 角色列表
	 */


    @GetMapping("/list")
    @ApiOperation("角色列表")
    public R list(@RequestParam Map<String, Object> params){
        IPage<JobRole> page = jobRoleService.queryPage(params);
        return R.ok(page);
    }

    /**
     * 角色列表
     */
    @GetMapping("/select")
    @ApiOperation("角色列表")
    public R select(HttpServletRequest request){
        Map<String, Object> map = new HashMap<>();

        //如果不是超级管理员，则只查询自己所拥有的角色列表
        if(getCurrentUserId(request) != 1){
            map.put("create_user_id", getCurrentUserId(request));
        }
        List<JobRole> list = (List<JobRole>) jobRoleService.listByMap(map);

        return R.ok(list);
    }


    @GetMapping("/info/{roleId}")
    @ApiOperation("角色详情")
    public R info(@PathVariable("roleId") Long roleId){
        JobRole role = jobRoleService.getById(roleId);

        //查询角色对应的菜单
        List<Long> menuIdList = jobRoleMenuService.queryMenuIdList(roleId);
        role.setMenuIdList(menuIdList);

        return R.ok(role);
    }

    /**
     * 保存角色
     */
    @PostMapping("/save")
    @ApiOperation("保存角色")
    public R save(@RequestBody JobRole role, HttpServletRequest request){
        role.setCreateUserId(getCurrentUserId(request).longValue());
        jobRoleService.saveRole(role);

        return R.ok("保存成功");
    }

    /**
     * 修改角色
     */
    @PostMapping("/update")
    @ApiOperation("修改角色")
    public R update(@RequestBody JobRole role, HttpServletRequest request){
        role.setCreateUserId(getCurrentUserId(request).longValue());
        jobRoleService.update(role);

        return R.ok("更新成功");
    }

    /**
     * 删除角色
     */

    @PostMapping("/delete")
    @ApiOperation("删除角色")
    public R delete(Long roleIds){
        jobRoleService.deleteBatch(new Long[]{roleIds});

        return R.ok("删除成功");
    }
}

