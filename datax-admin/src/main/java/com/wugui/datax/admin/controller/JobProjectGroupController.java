package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobProjectGroup;
import com.wugui.datax.admin.service.JobProjectGroupService;
import com.wugui.datax.admin.service.JobService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wugui.datax.admin.constans.Constant.FILE_TYPE;

/**
 * @author hf
 * @creat 2021-01-29-15:49
 */
@RestController
@RequestMapping("/api/jobProjectGroup")
public class JobProjectGroupController {

    @Autowired
    private JobService jobService;

    @Autowired
    private JobProjectGroupService jobProjectGroupService;


    @GetMapping("/tree")
    public ReturnT<List<JobProjectGroup>> tree(@RequestParam Integer projectId) {
        List<JobProjectGroup> list = jobProjectGroupService.getTree(projectId);
        return new ReturnT<>(list);
    }

    @PostMapping("save")
    public ReturnT<String> save(@RequestBody JobProjectGroup jobProjectGroup) {
        if (jobProjectGroup.getParentId() == null || jobProjectGroup.getProjectId() == null) {
            return ReturnT.FAIL;
        }
        JobProjectGroup listByName = jobProjectGroupService.getOne(new QueryWrapper<JobProjectGroup>().
                eq("project_id", jobProjectGroup.getProjectId()).
                eq("name", jobProjectGroup.getName()).
                eq("parent_id", jobProjectGroup.getParentId()));
        if (listByName != null) {
            return ReturnT.FAIL/*.setMsg("文件夹或文件已经存在")*/;
        }
        JobProjectGroup projectGroup = jobProjectGroupService.getById(jobProjectGroup.getParentId());
        if (projectGroup.getType().equals(FILE_TYPE)) {
            //如果是文件类型下新建文件或文件夹，那么就在当前目录下新建文件
            jobProjectGroup.setParentId(projectGroup.getParentId());
        }
        jobProjectGroupService.save(jobProjectGroup);
        return new ReturnT<>("保存成功");
    }


    @PostMapping("delete")
    public ReturnT<String> delete(@RequestParam Integer id) {
        jobProjectGroupService.deleteBatch(id);
        return new ReturnT<>("删除成功");
    }

    //移动文件或者文件夹
    @PostMapping("update")
    public ReturnT<String> update(@RequestBody JobProjectGroup jobProjectGroup) {
        //根据id查到该记录
        JobProjectGroup projectGroupById = jobProjectGroupService.getById(jobProjectGroup.getId());
        //检查是否有重名文件
        String name = jobProjectGroup.getName();
        if (StringUtils.isNotBlank(name)) {
            //查看同级目录下是否又同名文件
            JobProjectGroup projectGroupByName = jobProjectGroupService.getOne(new QueryWrapper<JobProjectGroup>().
                    eq("project_id", projectGroupById.getProjectId()).
                    eq("name", jobProjectGroup.getName()).
                    eq("parent_id", projectGroupById.getParentId()));
            if (projectGroupByName != null && !projectGroupByName.getId().equals(jobProjectGroup.getId())) {
                return ReturnT.FAIL/*.setMsg("文件夹或文件已经存在")*/;
            }
        }
        jobProjectGroupService.updateById(jobProjectGroup);
        return ReturnT.SUCCESS/*.setMsg("成功")*/;
    }


    @PostMapping("paste")
    public ReturnT<String> paste(@RequestBody JobProjectGroup jobProjectGroup, @RequestParam Integer pid) {
        //查看同级目录下是否又同名文件
        JobProjectGroup projectGroupByName = jobProjectGroupService.getOne(new QueryWrapper<JobProjectGroup>().
                eq("project_id", jobProjectGroup.getProjectId()).
                eq("name", jobProjectGroup.getName()).
                eq("parent_id", pid));
        if (projectGroupByName != null && !projectGroupByName.getId().equals(jobProjectGroup.getId())) {
            return ReturnT.FAIL/*.setMsg("文件夹或文件已经存在")*/;
        }

        jobProjectGroupService.paste(jobProjectGroup, pid);
        return ReturnT.SUCCESS/*.setMsg("复制成功")*/;
    }
}
