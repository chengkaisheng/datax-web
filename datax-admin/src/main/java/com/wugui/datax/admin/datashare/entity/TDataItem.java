package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wugui.datax.admin.datashare.tools.PoiHandler;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TDataItem {
    @PoiHandler(excelHeader ="id",excelIgnore=true)
    private Integer id;

    @PoiHandler(excelHeader ="数据项itemId",excelIgnore=true)
    private String itemId;

    @PoiHandler(excelHeader ="信息资源infoId",excelIgnore=true)
    private String infoId;

    @PoiHandler(excelHeader ="内部标识符")
    private String identifier;

    @PoiHandler(excelHeader ="中文名称")
    private String chineseName;

    @PoiHandler(excelHeader ="英文名称")
    private String englishName;

    @PoiHandler(excelHeader ="数据类型")
    private String dataType;

    @PoiHandler(excelHeader ="字段描述")
    private String remarks;

    @PoiHandler(excelHeader ="数据长度")
    private String dataLength;

    @PoiHandler(excelHeader = "所在信息资源名称")
    private String infoName;

    @PoiHandler(excelHeader ="默认值")
    private String itemDefault;

    @PoiHandler(excelHeader ="数源单位")
    private String dataCompany;

    @PoiHandler(excelHeader ="是否字典项")
    private String isDictionaries;

    @PoiHandler(excelHeader ="共享属性")
    private String shareAttribute;

    @PoiHandler(excelHeader ="共享条件")
    private String shareCondition;

    @PoiHandler(excelHeader ="开放属性")
    private String openAttribute;

    @PoiHandler(excelHeader ="开放条件")
    private String openCondition;

    @PoiHandler(excelHeader ="是否为空")
    private String isNull;

    @PoiHandler(excelHeader ="是否主键")
    private String isPrimarykey;

    @PoiHandler(excelHeader ="是否归集")
    private String isNotionalPool;

    @PoiHandler(excelHeader ="归集状态",excelIgnore = true)
    private String notionalPoolState;

    @PoiHandler(excelHeader ="是否采用标准",excelIgnore = true)
    private String standard;

    @PoiHandler(excelHeader = "来源",excelIgnore=true)
    private String source;

    @PoiHandler(excelHeader = "创建时间",excelIgnore=true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @PoiHandler(excelHeader = "修改时间",excelIgnore=true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

    @PoiHandler(excelHeader = "数据存储服务器名称")
    private String dataServerName;

    @PoiHandler(excelHeader = "是否已删除",excelIgnore = true)
    private String isDelete;


}