package com.wugui.datax.admin.service.impl;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobFile;
import com.wugui.datax.admin.entity.JobProject;
import com.wugui.datax.admin.mapper.JobFileMapper;
import com.wugui.datax.admin.mapper.JobProjectMapper;
import com.wugui.datax.admin.service.JobFileService;
import com.wugui.datax.admin.util.UUIDUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;

/**
 * @author ：lxq
 * @description：TODO
 * @date ：2021/1/11 14:30
 */
@Service
public class JobFileServiceImpl implements JobFileService {
    @Resource
    private JobFileMapper jobFileMapper;
    @Resource
    private JobProjectMapper jobProjectMapper;


    @Override
    public ReturnT<String> add(JobFile jobFile) {
        JobFile checkJobFile=jobFileMapper.selectByProjectIdAndFileName(jobFile.getProjectId(),jobFile.getJobFileName());
        if(UUIDUtils.notEmpty(checkJobFile)){
            return new ReturnT<>(ReturnT.FAIL_CODE,"此项目下已存在该文件夹名称");
        }
        jobFile.setCreateTime(new Date());
        try{//异常处理
            /*JobProject jobProject=jobProjectMapper.selectById(jobFile.getProjectId());
            String fileNamePath=path+jobFile.getJobFileName();
            File file=new File(fileNamePath);
            if(!file.exists()){//如果文件夹不存在
                file.mkdir();//创建文件夹
            }*/
//            jobFile.setJobFilePath(fileNamePath);
            int n=jobFileMapper.save(jobFile);
            if(n>0){
                return new ReturnT<>(ReturnT.SUCCESS_CODE,"文件夹创建成功");
            }else {
                return new ReturnT<>(ReturnT.FAIL_CODE,"文件夹创建失败");
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ReturnT<>(ReturnT.FAIL_CODE,"文件夹创建失败,失败原因为:"+e.getMessage());
        }
    }
}
