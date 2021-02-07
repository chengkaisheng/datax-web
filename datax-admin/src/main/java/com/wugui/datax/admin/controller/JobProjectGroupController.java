package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.constans.Constant;
import com.wugui.datax.admin.entity.JobInfo;
import com.wugui.datax.admin.entity.JobProjectGroup;
import com.wugui.datax.admin.service.JobProjectGroupService;
import com.wugui.datax.admin.service.JobService;
import com.wugui.datax.admin.util.UUIDUtils;
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
            return new ReturnT<>("请选择父级目录");
        }
        JobProjectGroup listByName = jobProjectGroupService.getOne(new QueryWrapper<JobProjectGroup>().
                eq("project_id", jobProjectGroup.getProjectId()).
                eq("name", jobProjectGroup.getName()).
                eq("parent_id", jobProjectGroup.getParentId()));
        if (listByName != null) {
            return ReturnT.FAIL.setFailMsg("文件夹或文件已经存在");
        }
        JobProjectGroup projectGroup = jobProjectGroupService.getById(jobProjectGroup.getParentId());
        if (projectGroup.getType().equals(FILE_TYPE)) {
            //如果是文件类型下新建文件或文件夹，那么就在当前目录下新建文件
            jobProjectGroup.setParentId(projectGroup.getParentId());
        }
        if(Constant.DIR_TYPE.equals(jobProjectGroup.getType())){
            jobProjectGroup.setJobType(Constant.DIR);
        }
        jobProjectGroupService.save(jobProjectGroup);
        return new ReturnT<>(String.valueOf(jobProjectGroup.getId()));
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
                return ReturnT.FAIL.setFailMsg("文件夹或文件已经存在");
            }
        }
        jobProjectGroupService.updateById(jobProjectGroup);
        if(UUIDUtils.notEmpty(jobProjectGroup.getJobId())){
            JobInfo jobInfo=jobService.getJobInfo(jobProjectGroup.getJobId());
            if(UUIDUtils.notEmpty(jobInfo)){
                jobInfo.setJobDesc(jobProjectGroup.getName());
                jobService.update(jobInfo);
            }
        }
        return ReturnT.SUCCESS.setOkMsg("成功");
    }


    @PostMapping("paste")
    public ReturnT<String> paste(@RequestBody JobProjectGroup jobProjectGroup, @RequestParam Integer pid) {
        //查看同级目录下是否又同名文件
        JobProjectGroup projectGroupByName = jobProjectGroupService.getOne(new QueryWrapper<JobProjectGroup>().
                eq("project_id", jobProjectGroup.getProjectId()).
                eq("name", jobProjectGroup.getName()).
                eq("parent_id", pid));
        if (projectGroupByName != null && !projectGroupByName.getId().equals(jobProjectGroup.getId())) {
            return ReturnT.FAIL.setFailMsg("文件夹或文件已经存在");
        }

        jobProjectGroupService.paste(jobProjectGroup, pid);
        return ReturnT.SUCCESS.setOkMsg("复制成功");
    }
}
