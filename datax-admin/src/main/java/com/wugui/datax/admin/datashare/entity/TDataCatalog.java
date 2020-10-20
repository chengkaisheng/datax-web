package com.wugui.datax.admin.datashare.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wugui.datax.admin.datashare.tools.PoiHandler;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class TDataCatalog {
    @PoiHandler(excelHeader ="id",excelIgnore=true)
    private Integer id;

    @PoiHandler(excelHeader = "信息资源id",excelIgnore=true)
    private String infoId;

    @PoiHandler(excelHeader ="区域")
    private String region;

    @PoiHandler(excelHeader="数源单位")
    @ApiModelProperty(value = "数源单位",name ="dataCompany",required = true)
    private String dataCompany;

    @PoiHandler(excelHeader ="数源单位代码" ,excelIgnore=true)
    private String companyCode;

    @PoiHandler(excelHeader="信息资源名称")
    @ApiModelProperty(value = "信息资源名称",name ="infoName",required = true)
    private String infoName;

    @PoiHandler(excelHeader ="信息资源代码",excelIgnore=true)
    private String infoCode;

    @PoiHandler(excelHeader="归集表英文名称")
    private String tableEnglish;

    @PoiHandler(excelHeader="信息资源摘要")
    @ApiModelProperty(value = "信息资源摘要",name ="infoExtract",required = true)
    private String infoExtract;

    @PoiHandler(excelHeader="所属系统名称")
    @ApiModelProperty(value = "所属系统名称",name ="infoSystemName",required = true)
    private String infoSystemName;

    @PoiHandler(excelHeader="信息资源格式")
    @ApiModelProperty(value = "信息资源格式",name ="infoFormat",required = true)
    private String infoFormat;

    @PoiHandler(excelHeader="重点领域分类")
    @ApiModelProperty(value = "重点领域分类",name ="importSort",required = true)
    private String importSort;

    @PoiHandler(excelHeader="归集更新频率")
    @ApiModelProperty(value ="归集更新频率",name ="updateFrequency",required = true)
    private String updateFrequency;

    @PoiHandler(excelHeader="修改日期")
    @ApiModelProperty(value ="修改日期",name ="updateDate",required = true)
    private String updateDate;

    @PoiHandler(excelHeader="是否引用上级")
    @ApiModelProperty(value ="是否引用上级",name ="isQuote")
    private String isQuote;

    @PoiHandler(excelHeader ="目录状态")
    private String catalogState;

    @PoiHandler(excelHeader = "归集状态")
    private String isNotionalPool;//归集状态 是否已归集

    @PoiHandler(excelHeader = "来源",excelIgnore=true)
    private String source;

    @PoiHandler(excelHeader = "关联责任清单",excelIgnore=true)
    private String detailedList;

    @PoiHandler(excelHeader = "归集业务审核人",excelIgnore=true)
    private String businessExamine;

    @PoiHandler(excelHeader = "归集业务技术审核人",excelIgnore=true)
    private String technologyExamine;


    @PoiHandler(excelHeader = "创建时间",excelIgnore=true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date createTime;

    @PoiHandler(excelHeader = "修改时间",excelIgnore=true)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8",locale = "zh")
    private Date updateTime;

    @PoiHandler(excelHeader = "共享属性",excelIgnore=true)
    private String shareAttribute;

    @PoiHandler(excelHeader = "共享条件",excelIgnore=true)
    private String shareCondition;

    @PoiHandler(excelHeader = "开放属性",excelIgnore=true)
    private String openAttribute;

    @PoiHandler(excelHeader ="开放条件", excelIgnore=true)
    private String openCondition;

    @PoiHandler(excelHeader ="数据存储服务器名称")
    private String dataServerName;

    @PoiHandler(excelHeader ="删除标识", excelIgnore=true)
    private String isDelete;

    @PoiHandler(excelIgnore=true)
    private List<TDataItem> dataItemList;

    private  int totalLimit;


}