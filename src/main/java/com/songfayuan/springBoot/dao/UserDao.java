/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.dao
 * 创建时间：2017年12月13日下午5:11:36
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.songfayuan.springBoot.entity.UserEntity;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月13日下午5:11:36
 */
public interface UserDao {

	/**
	 * 描述：查询所有用户列表（不分页）
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午5:40:58
	 */
	@Select("select * from user")
	public List<UserEntity> findUserList();

	/**
	 * 描述：根据id获取一条用户数据
	 * @param userId
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午6:11:57
	 */
	@Select("select * from user where id = #{userId}")
	public UserEntity findUserById(Integer userId);

	/**
	 * 描述：添加用户
	 * @param user
	 * @author songfayuan
	 * 2017年12月13日下午6:12:25
	 */
	@Insert("insert into user(user_name, pass_word, sex, age, phone, email) values(#{userName}, #{passWord}, #{sex}, #{age}, #{phone}, #{email})")
	public void saveUser(UserEntity user);

	/**
	 * 描述：根据id更新用户数据
	 * @param user
	 * @author songfayuan
	 * 2017年12月13日下午6:20:15
	 */
	@Update("update user set user_name=#{userName}, pass_word=#{passWord}, sex=#{sex}, age=#{age}, phone=#{phone}, email=#{email} where id = #{id}")
	public void updateUser(UserEntity user);

	@Delete("delete from user where id = #{userId}")
	public void deleteUser(Integer userId);

}
