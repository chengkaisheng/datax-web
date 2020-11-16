package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 任务规则关系表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-13 18:14:52
 */
@Data
public class JobRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 项目ID
	 */
	private Integer jobInfoId;
	/**
	 * 数据源ID
	 */
	private Integer datasourceId;
	/**
	 * 表名
	 */
	private String tables;
	/**
	 * 字段名，用,分割
	 */
	private String columns;
	/**
	 * 规则名称，用,风格
	 */
	private String rules;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人ID
	 */
	private Integer createUserId;

}
