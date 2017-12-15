/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.controller
 * 创建时间：2017年12月14日下午3:23:18
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.songfayuan.springBoot.annotation.ControllerMethodDescription;
import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.entity.UserEntity;
import com.songfayuan.springBoot.service.LogService;
import com.songfayuan.springBoot.utils.Response;

/**
 * 描述：用户请求日志
 * @author songfayuan
 * 2017年12月14日下午3:23:18
 */
@RestController
@RequestMapping("/log")
public class LogController {
	
	Logger logger = LoggerFactory.getLogger(LogController.class);
	
	@Autowired
	private LogService logService;
	
	/**
	 * 描述：分页查询用户请求日志
	 * @param page
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:27:41
	 */
	@ControllerMethodDescription(description="分页查询用户请求日志")
	@RequestMapping("/findLogListByPage")
	public Response findLogListByPage(Integer page, Integer pageSize){
		return this.logService.findLogListByPage(page, pageSize);
	}
	
	/**
	 * 描述：查看日志详情
	 * @param id
	 * @return
	 * @author songfayuan
	 * 2017年12月14日下午3:33:00
	 */
	@ControllerMethodDescription(description="查看日志详情")
	@RequestMapping("/findLogById")
	public Response findLogById(Integer id){
		LogEntity logEntity = this.logService.findLogById(id);
		return Response.success(logEntity);
	}
	
}
