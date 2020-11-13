package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobRole;
import com.wugui.datax.admin.mapper.JobRoleMapper;
import com.wugui.datax.admin.service.JobRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author hf
 * @creat 2020-11-13-18:53
 */
@RestController
@RequestMapping("api/jobRole")
@Api("角色管理接口")
public class JobRoleController {

    @Autowired
    JobRoleService jobRoleService;

    @Autowired
    JobRoleMapper jobRoleMapper;

    @GetMapping("/all")
    @ApiOperation("获取所有role")
    public R<List<JobRole>> allRole(String name){
        if(StringUtils.isEmpty(name)){
            return R.ok(jobRoleService.list());
        }else {
            QueryWrapper<JobRole> wrapper = new QueryWrapper<>();
            wrapper.like("name", name);
            return R.ok(jobRoleService.list(wrapper));
        }
    }

    @PostMapping("save")
    @ApiOperation("保存一个role")
    public ReturnT<String> save(@RequestBody JobRole jobRole){
        jobRoleService.save(jobRole);
        return new ReturnT<>("新增role成功");
    }

    @PostMapping("update")
    @ApiOperation("更新一个role")
    public ReturnT<String> update(@RequestBody JobRole jobRole){
        jobRoleMapper.updateById(jobRole);
        return new ReturnT<>("更新role成功");
    }

    @PostMapping("/delete")
    @ApiOperation("删除一个role")
    public ReturnT<String> delete(Integer id){
        jobRoleMapper.deleteById(id);
        return new ReturnT<>("删除成功");
    }

}
