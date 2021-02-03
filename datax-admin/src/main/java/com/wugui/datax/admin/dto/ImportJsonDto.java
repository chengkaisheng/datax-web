package com.wugui.datax.admin.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;

/**
 * @author ：lxq
 * @description：引入任务参数
 * @date ：2021/1/29 11:08
 */
@Data
public class ImportJsonDto extends DataXJsonBuildDto implements Serializable {

    private ReaderSync ReaderSync;

    private WriterPartition writerPartition;

    private String impalaHdfs;

    private String hiveHdfs;
}
