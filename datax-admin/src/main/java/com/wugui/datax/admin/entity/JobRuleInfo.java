package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-11-16 16:38:34
 */
@Data
@TableName("job_rule_info")
public class JobRuleInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 关系表ID
	 */
	private Integer jobRuleId;
	/**
	 * 字段名
	 */
	private String columns;
	/**
	 * 规则名称
	 */
	private String ruleCode;
	/**
	 * 创建人ID
	 */
	private Integer createUserId;
	/**
	 * 创建时间
	 */
	private Date createTime;

}
