package com.wugui.datax.admin.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: datax-web
 * @description
 * @author: lxq
 * @create: 2020-12-16 15:32
 **/
@Data
public class HiveParameter {
    @ApiModelProperty("hive版本")
    private String targetVersion;

    @ApiModelProperty("isTemporary")
    private boolean isTemporary;

    @ApiModelProperty("isExternal")
    private boolean isExternal;

    @ApiModelProperty("是否添加删除语句")
    private boolean dropAdded;

    @ApiModelProperty("与目标源相同的")
    private String dbNameType;

    @ApiModelProperty("自定义schema")
    private String dbNamePattern;

    @ApiModelProperty("与目标源相同")
    private String tableNameType;

    @ApiModelProperty("自定义表名")
    private String tableNamePattern;

    @ApiModelProperty("comment")
    private boolean comment;

    @ApiModelProperty("表备注信息")
    private boolean tableComment;

    @ApiModelProperty("是否分区")
    private String partitionKey;

    @ApiModelProperty("是否分桶")
    private String bucketKey;

    @ApiModelProperty("分桶数")
    private int bucketNum;

    @ApiModelProperty("是否桶内排序")
    private String  bucketSortKey;

    @ApiModelProperty("桶内排序方式")
    private String bucketSortOrder;

    @ApiModelProperty("是否进行分割")
    private String rowformat;

    @ApiModelProperty("列分割")
    private String fieldTerm;

    @ApiModelProperty("行分割")
    private String rowformatLineTerm;

    @ApiModelProperty("collect分割")
    private String rowformatCollectTerm;

    @ApiModelProperty("map分割")
    private String rowformatMapKeyTerm;

    @ApiModelProperty("空值替换字符")
    private String rowformatNullDefindAs;

    @ApiModelProperty("serder类名")
    private String rowformatSerdeName;

    @ApiModelProperty("hdfs路径")
    private String  location;

    @ApiModelProperty("文件格式,TEXTFILE、SEQUENCEFILE、RCFILE、ORCFILE")
    private String storedAs;

    private Long datasourceId;

    private String schema;

    private String tableName;

    @ApiModelProperty("转义字符替换")
    private String escaped;

    @ApiModelProperty("指定键值对")
    private String withSerdeproperties;

    @ApiModelProperty("指定元数据属性")
    private String tblProperties;
}
