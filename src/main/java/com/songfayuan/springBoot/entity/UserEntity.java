/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.entity
 * 创建时间：2017年12月13日下午4:57:46
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.entity;

import java.io.Serializable;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月13日下午4:57:46
 */
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 505461756494370991L;
	
	private Integer id;
	
	private String userName;
	
	private String passWord;
	
	private Integer age;
	
	private Integer sex;
	
	private String phone;
	
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
