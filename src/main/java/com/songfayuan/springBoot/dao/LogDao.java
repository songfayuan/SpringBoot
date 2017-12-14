/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.dao
 * 创建时间：2017年12月14日下午2:50:28
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.entity.UserEntity;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月14日下午2:50:28
 */
public interface LogDao {

	/**
	 * 描述：保存日志记录
	 * @param log
	 * @author songfayuan
	 * 2017年12月14日下午3:12:07
	 */
	@Insert("insert into log(log_type, content, user_id) values(#{logType}, #{content}, #{userId})")
	public void save(LogEntity log);

	/**
	 * 描述：分页查询用户请求日志
	 * @param offset
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:29:31
	 */
	@Select("select * from log order by id desc limit #{offset},#{pageSize}")
	public List<LogEntity> findLogListByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

	/**
	 * 描述：查询日志条数
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:30:54
	 */
	@Select("select count(id) from log")
	public Integer findRows();

	/**
	 * 描述：查看日志详情
	 * @param id
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:33:55
	 */
	@Select("select * from log where id = #{id}")
	public LogEntity findLogById(Integer id);

}
