package com.wugui.datax.admin.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class QualityConfDto {

    private int id;

    private int jobGroup;

    private String jobCron;

    private String jobDesc;

    private Date addTime;

    private Date updateTime;

    private int userId;

    private String alarmEmail;

    private String executorRouteStrategy;

    private String executorHandler;

    private String executorParam;

    private String executorBlockStrategy;

    private int executorTimeout;

    private int executorFailRetryCount;

    private String glueType;

    private String glueSource;

    private String glueRemark;

    private Date glueUpdatetime;

    private String childJobId;

    private int triggerStatus;

    private long triggerLastTime;

    private long triggerNextTime;

    private String jobJson;

    private String replaceParam;

    private String replaceParamType;

    private String jvmParam;

    private Date incStartTime;

    private String partitionInfo;

    private int lastHandleCode;

    private int projectId;

    private String primaryKey;

    private Long incStartId;

    private int incrementType;

    private  String readerTable;

    private int datasourceId;

    private String dataSourceName;

    private String projectName;

    private String userName;

    private String jobType;

    private String tableName;

    private String tableComment;

}
