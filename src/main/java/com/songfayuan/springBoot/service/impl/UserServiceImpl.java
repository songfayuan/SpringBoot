/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service.impl
 * 创建时间：2017年12月13日下午5:09:45
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.dao.UserDao;
import com.songfayuan.springBoot.entity.UserEntity;
import com.songfayuan.springBoot.service.UserService;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月13日下午5:09:45
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public List<UserEntity> findUserList() {
		return this.userDao.findUserList();
	}

	@Override
	public UserEntity findUserById(Integer userId) {
		return this.userDao.findUserById(userId);
	}

	@Override
	public void saveUser(UserEntity user) {
		this.userDao.saveUser(user);
	}

	@Override
	public void updateUser(UserEntity user) {
		this.userDao.updateUser(user);
	}

	@Override
	public void deleteUser(Integer userId) {
		this.userDao.deleteUser(userId);
	}

}
