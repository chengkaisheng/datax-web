package com.wugui.datax.admin.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Dashboard {

    /**项目**/
    //所有项目总数
    private Integer item;
    //所有项目数据源总数
    private Integer itemDataSource;
    //所有项目用户总数
    private Integer itemUser;
    //所有项目任务总数
    private Integer itemTask;
    //所有项目任务按类别统计数量
    private List<Map<String,Object>> taskTypeDistribution;

    /**数据源**/
    //所有数据源数量
    private Integer connectDataSource;
    //所有数据源的schema或者database数量
    private Integer database;
    //所有数据源的所有表的数量
    private Integer table;
    //所有库的所有表的所有字段数量
    //private Integer field;
    //所有库的所有表的总记录数之和
    //private Integer rows;

    /**元数据**/
    //实体统计
    //private Integer entity;
    //分类统计
    //private Integer classify;

    /**任务**/
    //项目任务数量分布
    private List<Map<String,Object>> itemTaskDistribution;
    //项目任务运行状态分布
    private List<Map<String,Object>> itemTaskRunStateDistribution;
    //项目任务类型分布
    private List<Map<String,Object>> itemTaskTypeDistribution;
    //任务按照执行器统计分布
    private List<Map<String,Object>> taskExecutorDistribution;

    /**数据质量**/
    //通用规则总数
    private Integer generalRule;
    //个性化规则总数
    private Integer personalRule;
    //质量任务总数
    private Integer configedRule;
    //对于已经应用到质量任务中的规则统计数量，维度是质量规则
    private List<Map<String,Object>> usedRule;

    /**数据共享**/
    //接口列表里的接口总数
    private Integer interfaceNum;
    //提交注册但未审核的接口数量
    private Integer approvingInterface;
    //提交注册后已经审核通过的接口数量
    private Integer passInterface;
    //提交注册后但是拒绝的接口数量
    private Integer rejectInterface;

}
