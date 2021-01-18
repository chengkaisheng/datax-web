package com.wugui.datax.admin.service;

import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.JobFile;

public interface JobFileService {
    /**
     * @author: lxq
     * @description: 添加任务文件夹
     * @date: 2021/1/11 14:33
     * @param jobFile
     * @return: ReturnT<String>
     */
    ReturnT<String> add(JobFile jobFile);
}
