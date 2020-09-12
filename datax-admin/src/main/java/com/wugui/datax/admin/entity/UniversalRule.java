package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用规则表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
@Data
@TableName("universal_rule")
public class UniversalRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 规则名称
	 */
	private String name;
	/**
	 * 规则编码
	 */
	private String code;
	/**
	 * 规则描述
	 */
	private String description;
	/**
	 * 规则类别 1完整 2准确 3规范 4唯一 5一致 6关联性
	 */
	private Integer type;
	/**
	 * 个性化规则ID
	 */
	private Integer personaliseId;
	/**
	 *  是否被删除  0未删  1已删
	 */
	private Integer isDelete;
	/**
	 * 创建人ID
	 */
	private Integer createUserId;
	/**
	 * 创建人姓名
	 */
	private String createUserName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新人ID
	 */
	private Integer updateUserId;
	/**
	 * 更新人姓名
	 */
	private String updateUserName;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	@TableField(exist = false)
	private Integer personaliseJoinType;

	@TableField(exist = false)
	private String personaliseCode;


}
