/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.entity
 * 创建时间：2017年12月14日下午2:37:57
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 描述：用户请求日志实体
 * @author songfayuan
 * 2017年12月14日下午2:37:57
 */
public class LogEntity implements Serializable {

	private static final long serialVersionUID = -3769434593346149607L;
	
	private Integer id;
	
	/**
	 * @description 创建时间
	 */
	private Date createTime;
	
	/**
	 * @description 日志类型（1601信息，1602异常）
	 */
	private Integer logType;
	
	/**
	 * @description 日志内容
	 */
	private String content;
	
	/**
	 * @description 操作人员
	 */
	private Integer userId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getLogType() {
		return logType;
	}

	public void setLogType(Integer logType) {
		this.logType = logType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
