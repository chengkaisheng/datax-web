package com.wugui.datax.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wugui.datax.admin.entity.ContrastRecord;
import com.wugui.datax.admin.mapper.ContrastRecordMapper;
import com.wugui.datax.admin.service.ContrastRecordService;
import org.springframework.stereotype.Service;

/**
 * @author hf
 * @creat 2020-10-19-15:32
 */
@Service("contrastRecordService")
public class ContrastRecordServiceImpl extends ServiceImpl<ContrastRecordMapper, ContrastRecord> implements ContrastRecordService {
}
