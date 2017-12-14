/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.service
 * 创建时间：2017年12月14日下午2:49:42
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.service;

import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.utils.Response;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月14日下午2:49:42
 */
public interface LogService {

	/**
	 * 描述：保存日志记录
	 * @param log
	 * @author songfayuan
	 * 2017年12月14日下午3:10:34
	 */
	public void save(LogEntity log);

	/**
	 * 描述：分页查询用户请求日志
	 * @param page
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:28:02
	 */
	public Response findLogListByPage(Integer page, Integer pageSize);

	/**
	 * 描述：查看日志详情
	 * @param id
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:33:16
	 */
	public LogEntity findLogById(Integer id);

}
