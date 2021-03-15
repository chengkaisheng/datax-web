package com.wugui.datax.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wugui.datatx.core.biz.model.ReturnT;
import com.wugui.datax.admin.entity.SqlHistory;
import com.wugui.datax.admin.service.SqlHistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * @author hf
 * @creat 2021-03-10-16:47
 */
@RestController
@RequestMapping("/api/sqlhis")
public class SqlHistoryController {


    @Resource
    private SqlHistoryService sqlHistoryService;

    @GetMapping("/page")
    public ReturnT<Page<SqlHistory>> page(@RequestParam Map<String, String> params){
        Page<SqlHistory> page = sqlHistoryService.queryPage(params);
        return new ReturnT<>(page);
    }

    @PostMapping("/tmpsave")
    public ReturnT<String> tmpsave(@RequestBody SqlHistory sqlHistory){
        sqlHistory.setSubmitTime(new Date());
        sqlHistory.setIsSaved(0);
        sqlHistoryService.save(sqlHistory);
        return ReturnT.SUCCESS.setOkMsg("保存成功");
    }

    @PostMapping("/save")
    public ReturnT<String> save(@RequestBody SqlHistory sqlHistory){
        sqlHistory.setSubmitTime(new Date());
        sqlHistory.setIsSaved(1);
        sqlHistoryService.save(sqlHistory);
        return ReturnT.SUCCESS.setOkMsg("保存成功");
    }

    @PostMapping("/delete")
    public ReturnT<String> delete(@RequestParam Integer id){
        sqlHistoryService.removeById(id);
        return ReturnT.SUCCESS.setOkMsg("删除成功");
    }
}
