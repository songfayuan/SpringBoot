/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.controller
 * 创建时间：2017年12月12日下午3:52:52
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

/**
 * 描述：单元测试
 * @author songfayuan
 * 2017年12月12日下午3:52:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldControlerTests {

	private MockMvc mvc;
	
	@Before
	public void setUp() throws Exception{
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}
	
	@Test
	public void getHello() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/hello/world").accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print())
		.andReturn();
	}
	
}
