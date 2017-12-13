/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.controller
 * 创建时间：2017年12月13日下午5:05:47
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songfayuan.springBoot.entity.UserEntity;
import com.songfayuan.springBoot.service.UserService;
import com.songfayuan.springBoot.utils.Response;

/**
 * 描述：用户
 * @author songfayuan
 * 2017年12月13日下午5:05:47
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 描述：查询所有用户列表（不分页）
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午5:40:01
	 */
	@RequestMapping("findUserList")
	public Response findUserList(){
		List<UserEntity> list =  this.userService.findUserList();
		return Response.success(list);
	}
	
	/**
	 * 描述：根据id获取一条用户数据
	 * @param userId
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午5:54:41
	 */
	@RequestMapping("findUserById")
	public Response findUserById(Integer userId){
		UserEntity user = this.userService.findUserById(userId);
		return Response.success(user);
	}
	
	/**
	 * 描述：添加用户
	 * @param user
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午6:12:05
	 */
	@RequestMapping("addUser")
	public Response saveUser(UserEntity user){
		this.userService.saveUser(user);
		return Response.successResponse("添加成功");
	}
	
	/**
	 * 描述：根据id更新用户数据
	 * @param user
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午6:19:52
	 */
	@RequestMapping("updateUser")
	public Response updateUser(UserEntity user){
		this.userService.updateUser(user);
		return Response.successResponse("修改成功");
	}
	
	@RequestMapping("deleteUserById")
	public Response deleteUser(Integer userId){
		this.userService.deleteUser(userId);
		return Response.successResponse("删除成功");
	}
	
}
