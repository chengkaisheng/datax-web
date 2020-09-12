package com.wugui.datax.admin.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 个性化规则表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2020-09-11 14:30:02
 */
@Data
@TableName("personalise_rule")
public class PersonaliseRule implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 入参名称
	 */
	private String name;
	/**
	 * 入参编码
	 */
	private String code;
	/**
	 * 入参类型 1格式入参集 2长度入参集 3特殊字符入参集 4编码入参集 5数据范围入参集
	 */
	private Integer joinType;
	/**
	 * 类别 1代码入参集 2 其他入参集
	 */
	private Integer type;
	/**
	 * 入参表达式（正则表达式）
	 */
	private String regular;
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

	/**
	 *  是否被删除  0未删  1已删
	 */
	private Integer isDelete;

}
