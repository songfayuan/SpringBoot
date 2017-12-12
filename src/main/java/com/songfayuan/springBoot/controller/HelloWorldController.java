/**
 * 项目名称：springBoot
 * 项目包名：com.songfayuan.springBoot.controller
 * 创建时间：2017年12月12日上午11:56:12
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：Hello World测试代码
 * @author songfayuan
 * 2017年12月12日上午11:56:12
 */
@RestController
@RequestMapping("hello")
public class HelloWorldController {
	
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldController.class);

	@RequestMapping("/world")
	public String helloDemo() {
		logger.info("接口/hello/world正在被请求...");
		
		logger.info("-- This is a primary with logback., Current time {}.", new Date());
		logger.trace("This level is TRACE.");
		logger.debug("This level is DEBUG.");
		logger.debug("This level is DEBUG.", logger.isDebugEnabled());
		logger.info("This level is INFO.");
		logger.info("isInfoEnabled:" + logger.isInfoEnabled());
		logger.warn("This level is WARN.");
		logger.error("This level is ERROR.");
		return "Hello World";
	}
	
}
