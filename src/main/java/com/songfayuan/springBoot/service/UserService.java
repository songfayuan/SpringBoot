/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service
 * 创建时间：2017年12月13日下午5:08:58
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service;

import java.util.List;

import com.songfayuan.springBoot.entity.UserEntity;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月13日下午5:08:58
 */
public interface UserService {

	/**
	 * 描述：查询所有用户列表（不分页）
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午5:40:32
	 */
	public List<UserEntity> findUserList();

	/**
	 * 描述：根据id获取一条用户数据
	 * @param userId
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午5:55:04
	 */
	public UserEntity findUserById(Integer userId);

	/**
	 * 描述：添加用户
	 * @param user
	 * @author songfayuan
	 * 2017年12月13日下午6:12:16
	 */
	public void saveUser(UserEntity user);

	/**
	 * 描述：根据id更新用户数据
	 * @param user
	 * @author songfayuan
	 * 2017年12月13日下午6:20:07
	 */
	public void updateUser(UserEntity user);

	public void deleteUser(Integer userId);

}
