package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.JobProject;
import com.wugui.datax.admin.entity.JobProjectUserEntity;
import com.wugui.datax.admin.mapper.JobProjectUserMapper;
import com.wugui.datax.admin.service.JobProjectUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author hf
 * @creat 2020-12-14-17:28
 */
@Service
public class JobProjectUserServiceImpl extends ServiceImpl<JobProjectUserMapper, JobProjectUserEntity> implements JobProjectUserService {
    @Override
    public Boolean addUser(JobProject jobProject) {
        int projectId = jobProject.getId();
        //先全部删除，再添加
        this.deleteBatch(new Integer[]{projectId});
        List<Integer> userIds = jobProject.getUserIds();
        if(userIds == null || userIds.size() == 0){
            return false;
        }
        for (Integer userId : userIds) {
            JobProjectUserEntity jobProjectUserEntity = new JobProjectUserEntity();
            jobProjectUserEntity.setUserId(userId);
            jobProjectUserEntity.setProjectId(projectId);
            this.save(jobProjectUserEntity);
        }
        return true;
    }

    @Override
    public void deleteBatch(Integer[] integers) {
        baseMapper.deleteBatch(integers);
    }
}
