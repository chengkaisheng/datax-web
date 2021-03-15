package com.wugui.datax.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wugui.datax.admin.entity.SqlHistory;

import java.util.Map;

/**
 * @author hf
 * @creat 2021-03-10-16:48
 */
public interface SqlHistoryService extends IService<SqlHistory> {
    Page<SqlHistory> queryPage(Map<String, String> params);
}
