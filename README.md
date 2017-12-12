# Spring Boot Demo
简介：Springboot modules

## 基础项目搭建
### maven构建项目
1、访问http://start.spring.io
2、选择构建工具Maven Project、Spring Boot版本1.3.6以及一些工程基本信息，点击“Switch to the full version.”java版本选择1.8，可参考下图所示：
![](http://www.ityouknow.com/assets/images/2016/springboot1.png)
3、点击Generate Project下载项目压缩包
4、解压后，使用eclipse，Import -> Existing Maven Projects -> Next ->选择解压后的文件夹-> Finsh，OK done!

### 项目结构介绍
```
Spring Boot的基础结构共三个文件:
	src/main/java 程序开发以及主程序入口
	src/main/resources 配置文件
	src/test/java 测试程序
spingboot建议的目录结果如下：
	com
	  +- songfayuan
	    +- springBoot
	      +- Application.java
	      |
	      +- dao
	      |  
	      +- entity
	      |
	      +- service
	      |
	      +- controller
	      |  +- HelloWorldController.java
	      |
	1、Application.java 建议放到根目录下面,主要用于做一些框架配置
	2、entity和dao目录主要用于实体（Entity）与数据访问层（Repository）
	3、service 层主要是业务类代码
	4、controller 负责页面访问控制
```

### 引入web模块（基础pom.xml）
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>

	<groupId>com.songfayuan</groupId>
	<artifactId>spring-boot</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<packaging>jar</packaging>

	<name>spring-boot</name>
	<description>SongFayuan project for Spring Boot</description>

	<!-- Inherit defaults from Spring Boot -->
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>1.5.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>

	<!-- 设定java版本 -->
	<properties>
		<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
		<project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
		<java.version>1.8</java.version>
	</properties>

	<!-- Add typical dependencies for a web application -->
	<dependencies>
		<!-- 核心模块，包括自动配置支持、日志和YAML -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter</artifactId>
		</dependency> 
		<!-- 测试模块，包括JUnit、Hamcrest、Mockito -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>
		<!-- 支持web的模块 -->
		<dependency>
		    <groupId>org.springframework.boot</groupId>
		    <artifactId>spring-boot-starter-web</artifactId>
		</dependency>
		<dependency>
	        <groupId>org.springframework.boot</groupId>
	        <artifactId>spring-boot-devtools</artifactId>
	        <optional>true</optional>
	    </dependency>
		
	</dependencies>

	<!-- Package as an executable jar -->
	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
				<configuration>
	                <fork>true</fork>
	            </configuration>
			</plugin>
		</plugins>
	</build>
</project>

```

### 编写controller内容
```java
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

```

### 配置日志输出
直接在resource下添加logback.xml即可，具体配置内容如下：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <!--<include resource="org/springframework/boot/logging/logback/base.xml"/>-->
    
    <!-- 设置log日志存放地址（单环境设置） -->
    <property name="log.base" value="/spring-boot_log/logs" />
    
    <!-- 控制台日志 -->
    <appender name="STDOUT" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%class:%line] %-5level - %msg%n</pattern>
            <charset>UTF-8</charset> <!-- 此处设置字符集 -->
        </encoder>
    </appender>

    <!-- warn日志 appender -->
    <appender name="WARN_OUT" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.base}/warn.log</file>
        <!-- 日志格式 -->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%class:%line] %-5level %logger - %msg%n</pattern>
        </encoder>
        <!-- 日志级别过滤器 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 过滤的级别 -->
            <level>WARN</level>
            <!-- 匹配时的操作：接收（记录） -->
            <onMatch>ACCEPT</onMatch>
            <!-- 不匹配时的操作：拒绝（不记录） -->
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 按天回滚 daily -->
            <fileNamePattern>${log.base}/warn-%d{yyyy-MM-dd HH:mm:ss}.log</fileNamePattern>
            <!-- 最大保存时间：30天-->
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <!--日志文件最大的大小 -->
        <triggeringPolicy
                class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>256MB</MaxFileSize>
        </triggeringPolicy>
    </appender>

    <!-- error日志 appender -->
    <appender name="ERROR_OUT" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${log.base}/error.log</file>
        <!-- 日志格式 -->
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss} [%class:%line] %-5level %logger - %msg%n</pattern>
        </encoder>
        <!-- 日志级别过滤器 -->
        <filter class="ch.qos.logback.classic.filter.LevelFilter">
            <!-- 过滤的级别 -->
            <level>ERROR</level>
            <!-- 匹配时的操作：接收（记录） -->
            <onMatch>ACCEPT</onMatch>
            <!-- 不匹配时的操作：拒绝（不记录） -->
            <onMismatch>DENY</onMismatch>
        </filter>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <!-- 按天回滚 daily -->
            <fileNamePattern>${log.base}/error-%d{yyyy-MM-dd}.log</fileNamePattern>
            <!-- 最大保存时间：30天-->
            <maxHistory>30</maxHistory>
        </rollingPolicy>
        <!--日志文件最大的大小 -->
        <triggeringPolicy
                class="ch.qos.logback.core.rolling.SizeBasedTriggeringPolicy">
            <MaxFileSize>256MB</MaxFileSize>
        </triggeringPolicy>
    </appender>
    <!--
    <logger name="org.testMybatis" level="DEBUG" />
    <logger name="java.sql.Connection" level="DEBUG" />
    <logger name="java.sql.Statement" level="DEBUG" />
    <logger name="java.sql.PreparedStatement" level="DEBUG" />
    <logger name="java.sql.ResultSet" level="DEBUG" />
    <logger name="backend" level="DEBUG"/>
    -->
    <!-- 基于INFO级别处理日志：具体控制台或者文件对日志级别的处理还要看所在appender配置的filter，如果没有配置filter，则使用root配置 -->
    <root level="INFO">
        <appender-ref ref="STDOUT" />
        <appender-ref ref="WARN_OUT" />
        <appender-ref ref="ERROR_OUT" />
    </root>
</configuration>
```

最后，启动Application main方法，至此一个java项目搭建好了，打开浏览器访问http://localhost:8080/hello/world，就可以看到效果了。

### 单元测试
打开的src/test/下的测试入口，编写简单的http请求来测试。
```java
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

```
到现在为止，SpringBoot基本项目搭建完毕，你可以启动并成功运行程序，接下来，便可以在此基础上扩展更多的功能模块。

