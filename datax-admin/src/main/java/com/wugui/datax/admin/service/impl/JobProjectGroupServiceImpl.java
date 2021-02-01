package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobProjectGroup;
import com.wugui.datax.admin.mapper.JobProjectGroupMapper;
import com.wugui.datax.admin.service.JobProjectGroupService;
import com.wugui.datax.admin.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author hf
 * @creat 2021-01-29-16:02
 */
@Service
public class JobProjectGroupServiceImpl extends ServiceImpl<JobProjectGroupMapper, JobProjectGroup> implements JobProjectGroupService {
    @Autowired
    private JobService jobService;

    @Autowired
    private JobProjectGroupMapper jobProjectGroupMapper;

    @Override
    public List<JobProjectGroup> getTree(Integer projectId) {
        //先查出该项目下的所有记录
        List<JobProjectGroup> jobProjectGroupList = this.list(new QueryWrapper<JobProjectGroup>().eq("project_id", projectId));
        //再使用递归对list设置层级结构
        //先拿到最顶级结构
        List<JobProjectGroup> parentList = jobProjectGroupList.stream().filter(item -> item.getParentId() == 0).collect(Collectors.toList());

        setChildren(jobProjectGroupList,parentList);

        return parentList;
    }

    @Override
    @Transactional
    public void deleteBatch(Integer id) {
        List<JobProjectGroup> jobProjectGroupList = jobProjectGroupMapper.selectList(new QueryWrapper<JobProjectGroup>().eq("parent_id", id));
        //递归删除
        deleteRecursion(jobProjectGroupList);
        //删除文件夹

        if(jobProjectGroupList != null && jobProjectGroupList.size() != 0){
            List<Integer> ids = jobProjectGroupList.stream().map(JobProjectGroup::getId).collect(Collectors.toList());
            this.removeByIds(ids);
        }
        this.removeById(id);
        //删除任务
    }

    @Override
    public void paste(JobProjectGroup jobProjectGroup, Integer pid) {
        jobProjectGroup.setParentId(pid);
        jobProjectGroup.setId(null);
        List<JobProjectGroup> childrenList = jobProjectGroup.getChildren();
        this.save(jobProjectGroup);
        if(jobProjectGroup.getJobId() != null){
            //TODO 如果是文件类型为任务，则需要根据job_id去新创建一个任务，并且创建一个版本
        }
        if(childrenList != null && childrenList.size() != 0){
            childrenList.forEach(children->paste(children, jobProjectGroup.getId()));
        }
    }

    private void deleteRecursion(List<JobProjectGroup> jobProjectGroupList) {
        if(jobProjectGroupList == null || jobProjectGroupList.size() == 0){
            return;
        }
        jobProjectGroupList.forEach(item->{
            if(item.getType().equals(1)){
                //删除文件
                this.removeById(item.getId());
                List<JobProjectGroup> childrenList = jobProjectGroupMapper.selectList(new QueryWrapper<JobProjectGroup>().eq("parent_id", item.getId()));
                deleteRecursion(childrenList);
            }else {
                //删除文件
                this.removeById(item.getId());
                //删除任务
                Integer jobId = item.getJobId();
                if(jobId != null){
                    jobService.deleteById(jobId);
                }
            }
        });
    }

    private void setChildren(List<JobProjectGroup> allJobProjectGroupList, List<JobProjectGroup> parentList) {
        if(parentList.size() == 0){
            return;
        }
        //遍历文件夹
        parentList.forEach(parentItem->{
            List<JobProjectGroup> childrenList = allJobProjectGroupList.stream().filter(item -> parentItem.getId().equals(item.getParentId())).collect(Collectors.toList());
            parentItem.setChildren(childrenList);
            List<JobProjectGroup> fileTypeList = childrenList.stream().filter(item -> item.getType() == 1).collect(Collectors.toList());
            setChildren(allJobProjectGroupList, fileTypeList);
        });

    }
}
