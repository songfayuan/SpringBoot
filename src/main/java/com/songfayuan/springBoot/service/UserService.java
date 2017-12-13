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
import com.songfayuan.springBoot.utils.Response;

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

	/**
	 * 描述：根据id删除用户数据
	 * @param userId
	 * @author songfayuan
	 * 2017年12月13日下午8:14:25
	 */
	public void deleteUser(Integer userId);

	/**
	 * 描述：查询用户数
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:05:20
	 */
	public Integer getUserCount();

	/**
	 * 描述：分页查询用户列表
	 * @param page
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:25:40
	 */
	public Response findUserListByPage(Integer page, Integer pageSize);

}
