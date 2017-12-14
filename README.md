# Spring Boot Demo （零基础搭建SpringBoot项目）
简介：Springboot modules

## 基础项目搭建
### maven构建项目
1、访问http://start.spring.io/

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

## mail模块
### 添加依赖包
在pom.xml中添加依赖包，配置如下：
```xml
		<!-- mail模块 -->
	    <dependency>
			<groupId>org.springframework</groupId>
			<artifactId>spring-context-support</artifactId>
			<version>RELEASE</version>
		</dependency>
        <dependency>
            <groupId>com.sun.mail</groupId>
            <artifactId>javax.mail</artifactId>
            <version>RELEASE</version>
        </dependency>
        <!-- 模板引擎 -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
```

###  添加mail相关配置
在application.properties中添加mail相关配置，如下：
```xml
spring.mail.host=smtp.163.com
spring.mail.username=songfayuan1993@163.com
spring.mail.password=123456(密码)
spring.mail.default-encoding=UTF-8

mail.fromMail.addr=songfayuan1993@163.com
```

### 创建邮件模板
在resources/templates下创建邮件模板emailTemplate.html，自定义样式如下：
```xml
<!DOCTYPE html>
<html lang="zh" xmlns:th="http://www.thymeleaf.org">
    <head>
        <meta charset="UTF-8"/>
        <title>邮件模板</title>
    </head>
    <body>
       	 您好,这是验证邮件,请点击下面的链接完成验证,<br/>
        <a href="#" th:href="@{ http://www.songfayuan.com/mail/{id}(id=${id}) }">激活账号</a>
    </body>
</html>
```

### 编辑mail接口和具体实现
MailService
```java
package com.songfayuan.springBoot.service;

/**
 * 描述：发送邮件接口
 * @author songfayuan
 * 2017年12月13日上午10:51:53
 */
public interface MailService {

	/**
	 * 描述：发送普通（文本）邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @author songfayuan
	 * 2017年12月13日上午11:08:12
	 */
	public void sendSimpleMail(String to, String subject, String content);
	
	/**
	 * 描述：发送html邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @author songfayuan
	 * 2017年12月13日上午11:07:57
	 */
	public void sendHtmlMail(String to, String subject, String content);
	
	/**
	 * 描述：发送附件邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param filePath
	 * @author songfayuan
	 * 2017年12月13日上午11:07:41
	 */
	public void sendAttachmentMail(String to, String subject, String content, String filePath);
	
	/**
	 * 描述：发送正文中有静态资源（图片）的邮件
	 * @param to
	 * @param subject
	 * @param content
	 * @param resourcePath
	 * @param contentId
	 * @author songfayuan
	 * 2017年12月13日上午11:25:31
	 */
	public void sendInlineResourceMail(String to, String subject, String content, String resourcePath, String contentId);
	
}

```

MailServiceImpl
```java
package com.songfayuan.springBoot.service.impl;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.service.MailService;

/**
 * 描述：发送邮件具体实现类
 * @author songfayuan
 * 2017年12月13日上午11:28:56
 */
@Service
public class MailServiceImpl implements MailService {
	
	private final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mail.fromMail.address}")
	private String from;

	@Override
	public void sendSimpleMail(String to, String subject, String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		try {
			mailSender.send(message);
			logger.info("发送普通邮件成功...");
		} catch (Exception e) {
			logger.error("发送普通邮件时发生异常...", e);
		}
	}

	@Override
	public void sendHtmlMail(String to, String subject, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true); //true表示需要创建一个multipart message
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			mailSender.send(message);
			logger.info("发送html邮件成功...");
		} catch (Exception e) {
			logger.error("发送html邮件时发生异常...", e);
		}
	}

	@Override
	public void sendAttachmentMail(String to, String subject, String content, String filePath) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource file = new FileSystemResource(new File(filePath));
			String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
			helper.addAttachment(fileName, file);
			mailSender.send(message);
			logger.info("发送附件邮件成功...");
		} catch (Exception e) {
			logger.error("发送附件邮件时发生异常...", e);
		}
	}

	@Override
	public void sendInlineResourceMail(String to, String subject, String content, String resourcePath, String contentId) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(content, true);
			FileSystemResource resource = new  FileSystemResource(new File(resourcePath));
			helper.addInline(contentId, resource);
			mailSender.send(message);
			logger.info("发送嵌入静态资源的邮件成功...");
		} catch (Exception e) {
			logger.error("发送嵌入静态资源的邮件时发生异常...", e);
		}
	}

}

```

### 编写测试用例

```java
package com.songfayuan.springBoot.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * 描述：邮件模板单元测试
 * @author songfayuan
 * 2017年12月13日下午2:39:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService mailService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	//接受者账户
	private String to = "1414798079@qq.com"; 
	
	@Test
	public void testSimpleMail() throws Exception{
		String subject = "测试发送普通（文本）邮件";
		String content = "这是一封用于测试发送普通文本邮件的信息...";
		mailService.sendSimpleMail(to, subject, content);
	}
	
	@Test
	public void testHtmlMail() throws Exception{
		String subject = "测试发送html邮件";
		String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
		mailService.sendHtmlMail(to, subject, content);
	}
	
	@Test
	public void sendAttachmentMail() throws Exception{
		String subject = "测试发送带附件的邮件";
		String content = "这是一封用于测试发送带附件文件邮件的信息...";
		String filePath="D:\\Icon Manager.rar";
		mailService.sendAttachmentMail(to, subject, content, filePath);
	}
	
	@Test
	public void sendInlineResourceMail() throws Exception{
		String contentId = "demo01";
		String subject = "测试发送有图片的邮件";
		String content = "<html><body>这是一封用于测试发送有图片邮件的信息：<img src=\'cid:" + contentId + "\' ></body></html>";
		String resourcePath = "D:\\123456.png";
		mailService.sendInlineResourceMail(to, subject, content, resourcePath, contentId);
	}
	
	@Test 
	public void sendTemplateMail() throws Exception{
		String subject = "测试发送自定义模板邮件";
		Context context = new Context();
		context.setVariable("id", "demo002");
		String content = templateEngine.process("emailTemplate", context);
		mailService.sendHtmlMail(to, subject, content);
	}
	
}

```

## SpringBoot整合mybatis及简单案例实现
### 添加依赖包
在pom.xml中添加mybatis的依赖包，如下：
```xml
		<!-- 整合mybatis -->
		<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>1.3.1</version>
		</dependency>
		<!-- mysql jdbc驱动 -->
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<scope>runtime</scope>
		</dependency> 
```

### 添加相关配置
在application.properties添加如下配置：
```xml
# mybatis配置
mybatis.type-aliases-package=com.songfayuan.springBoot.entity
# mybatis设置自动驼峰命名转换
mybatis.configuration.mapUnderscoreToCamelCase=true
# mysql配置
spring.datasource.driverClassName = com.mysql.jdbc.Driver
spring.datasource.url = jdbc:mysql://localhost:3306/spring_boot?useUnicode=true&characterEncoding=utf-8
spring.datasource.username = root
spring.datasource.password = 123456
```

在启动类Application中添加对包的扫描配置@MapperScan如下：
```java
@SpringBootApplication
@MapperScan("com.songfayuan.springBoot.dao")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
```

### 创建数据库
新建数据库spring_boot，并创建user表，字段名称如下：
```
id	int	11	0	0	-1	0	0	0		0					-1	0
user_name	varchar	50	0	-1	0	0	0	0		0	用户名	utf8	utf8_general_ci		0	0
pass_word	varchar	255	0	-1	0	0	0	0		0	用户密码	utf8	utf8_general_ci		0	0
sex	int	11	0	-1	0	0	0	0		0	性别（0 男 ，1女）				0	0
age	int	11	0	-1	0	0	0	0		0	年龄				0	0
phone	varchar	50	0	-1	0	0	0	0		0	电话号码	utf8	utf8_general_ci		0	0
email	varchar	50	0	-1	0	0	0	0		0	电子邮件	utf8	utf8_general_ci		0	0
```

### 创建数据库映射实体
在com.songfayuan.springBoot.entity下创建UserEntity实体类，如下：
```java
package com.songfayuan.springBoot.entity;

import java.io.Serializable;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月13日下午4:57:46
 */
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 505461756494370991L;
	
	private Integer id;
	
	private String userName;
	
	private String passWord;
	
	private Integer age;
	
	private Integer sex;
	
	private String phone;
	
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
```
### 创建dao接口
在com.songfayuan.springBoot.dao下创建UserDao接口，且通过注解方式实现对数据库的增删改查操作，具体代码如下：
```java
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
```

### 创建service接口和其实现类
在com.songfayuan.springBoot.service下创建Service接口，在com.songfayuan.springBoot.service.impl下创建Serviceimpl实现类：

UserService
```java
package com.songfayuan.springBoot.service;

import java.util.List;

import com.songfayuan.springBoot.entity.UserEntity;

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

	public void deleteUser(Integer userId);

}
```

UserServiceImpl
```java
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
```

### 创建Controller
在com.songfayuan.springBoot.controller下创建UserController：
```java
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
```
Controller中用到的Response为自己封装，具体代码如下：
```java
package com.songfayuan.springBoot.utils;

public class Response {
	protected int code;
	protected String msg;
	protected Object data;

	private static final int SUCCESS_CODE = 200;
	private static final String SUCCESS_MSG = "success";
	private static final int ERROR_CODE = 500;
	private static final String ERROR_MSG = "服务器内部异常，请联系技术人员！";// 将error改成了内容信息

	public static Response success() {
		Response resp = new Response();
		resp.code = (SUCCESS_CODE);
		resp.msg = (SUCCESS_MSG);
		return resp;
	}

	public static Response successResponse(String msg) {
		Response resp = new Response();
		resp.code = SUCCESS_CODE;
		resp.msg = msg;
		return resp;
	}

	public static Response error() {
		Response resp = new Response();
		resp.code = (ERROR_CODE);
		resp.msg = (ERROR_MSG);
		return resp;
	}

	public static Response errorResponse(String msg) {
		Response resp = new Response();
		resp.code = ERROR_CODE;
		resp.msg = msg;
		return resp;
	}

	public static Response response(int code, String msg) {
		Response resp = new Response();
		resp.code = (code);
		resp.msg = (msg);
		return resp;
	}

	public static Response response(int code, String msg, Object data) {
		Response resp = new Response();
		resp.code = (code);
		resp.msg = (msg);
		resp.data = data;
		return resp;
	}

	public static Response success(Object data) {
		Response resp = new Response();
		resp.code = (SUCCESS_CODE);
		resp.msg = (SUCCESS_MSG);
		resp.data = data;
		return resp;
	}

	public static Response error(Object data) {
		Response resp = new Response();
		resp.code = (ERROR_CODE);
		resp.msg = (ERROR_MSG);
		resp.data = data;
		return resp;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

}
```

至此，我们成功的将springboot和mybatis整合在一起，并且实现了简单的增删改查。

### 自定义分页查询
自定义分页工具类：
Page
```java
package com.songfayuan.springBoot.utils;

import java.util.List;

/**
 * 描述：分页工具
 * @author songfayuan
 * 2017年12月13日下午9:11:45
 * @param <T>
 */
public class Page<T> {
	List<T> data;
	Integer total;
	Integer start;
	Integer pageSize = 10;
	Integer resultCount;

	public static final int DEFAULE_PAGESIZE = 10;

	public Page(Integer start, Integer pageSize, Integer resultCount) {
		super();
		this.total = resultCount / pageSize + (resultCount % pageSize > 0 ? 1 : 0);
		this.start = start;
		this.pageSize = pageSize;
		this.resultCount = resultCount;
	}

	public Page() {
		super();
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getResultCount() {
		return resultCount;
	}

	public void setResultCount(Integer resultCount) {
		this.resultCount = resultCount;
	}

}
```

在UserController中加入如下代码：
```java
   /**
	 * 描述：分页查询用户列表
	 * @param page
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:25:11
	 */
	@RequestMapping("/findUserListByPage")
	public Response findUserListByPage(Integer page, Integer pageSize){
		return this.userService.findUserListByPage(page, pageSize);
	}
```

在UserService接口中添加如下代码：
```java
   /**
	 * 描述：分页查询用户列表
	 * @param page
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:25:40
	 */
	public Response findUserListByPage(Integer page, Integer pageSize);
```

在UserServiceImpl实现类中添加如下代码：
```java
	@Override
	public Response findUserListByPage(Integer page, Integer pageSize) {
		Integer offset = page > 0 ? page * pageSize : 0;
		List<UserEntity> list = this.userDao.findUserListByPage(offset, pageSize);
		Integer rows = this.userDao.findRows();
		Page<UserEntity> pagelist = new Page<>(page, pageSize, rows);
		pagelist.setData(list);
		return Response.success(pagelist);
	}
```

在UserDao接口中添加如下代码：
```java
	/**
	 * 描述：分页查询用户列表
	 * @param offset
	 * @param pageSize
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:25:55
	 */
	@Select("select * from user order by id limit #{offset},#{pageSize}")
	public List<UserEntity> findUserListByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);

	/**
	 * 描述：查询用户数量
	 * @return
	 * @author songfayuan
	 * 2017年12月13日下午9:26:03
	 */
	@Select("select count(id) from user")
	public Integer findRows();
```
至此，分页查询功能搞定。

## SpringBoot整合AOP（案例：使用 AOP 切面的方式记录 web 请求日志）
###添加依赖
pom.xml
```xml
		<!-- aop -->
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-aop</artifactId>
		</dependency>
		<!-- json -->
		<dependency>
			<groupId>com.alibaba</groupId>
			<artifactId>fastjson</artifactId>
			<version>1.2.8</version>
		</dependency>
		<!-- UserAgent工具类：https://mvnrepository.com/artifact/nl.bitwalker/UserAgentUtils -->
		<dependency>
		    <groupId>nl.bitwalker</groupId>
		    <artifactId>UserAgentUtils</artifactId>
		    <version>1.2.4</version>
		</dependency>
```

### 编辑切面
新建数据库log表：
```
id	int	11	0	0	-1	0	0	0		0	日志id				-1	0
create_time	timestamp	0	0	-1	0	0	0	0	CURRENT_TIMESTAMP	0	日志产生时间				0	0
log_type	int	11	0	-1	0	0	0	0		0	日志类型（1601信息，1602异常）				0	0
content	text	0	0	-1	0	0	0	0		0	日志内容	utf8	utf8_general_ci		0	0
user_id	int	11	0	-1	0	0	0	0		0	操作人员				0	0
```

### 编辑切面
在com.songfayuan.springBoot.aspectj下编辑切面，按自己的需求造出自己的代码：
```java
package com.songfayuan.springBoot.aspectj;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.service.LogService;

import nl.bitwalker.useragentutils.UserAgent;



/**
 * 描述：【声明切面】记录请求日志
 * @author songfayuan
 * 2017年12月14日上午11:26:24
 */
@Aspect
@Service
public class RequestLogAspect {
	
	private static final String START_TIME = "start_request_time";

	private static final Logger logger = LoggerFactory.getLogger(RequestLogAspect.class);
	
	//注入Service用于把日志保存数据库
	@Autowired
	private LogService logService;

	//声明controller层切入点
	@Pointcut("execution(* com.songfayuan.springBoot.controller.*.*(..))")
	public void controllerAspect(){
	}
	
	//声明service层切入点
	@Pointcut("execution(* com.songfayuan.springBoot.service.*.*(..)) && !execution(* com.songfayuan.springBoot.service.LogService.*(..) )")
	public void serviceAspect(){
	}
	
	/**
	 * 描述：声明前置通知-用于拦截Controller请求日志
	 * @param joinPoint
	 * @author songfayuan
	 * 2017年12月14日下午1:31:12
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint){
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		try {
			logger.info("【请求 URL】：{}", request.getRequestURL());
			logger.info("【请求 IP】：{}", request.getRemoteAddr());
			logger.info("【请求类名】：{}，【请求方法名】：{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			Map parameterMap = request.getParameterMap();
			logger.info("【请求参数】：{}，", JSONObject.toJSONString(parameterMap));
			Long start = System.currentTimeMillis();
			request.setAttribute(START_TIME, start);
			// *========数据库日志=========*//
			LogEntity log = new LogEntity();
			log.setContent("【请求类名】:"+joinPoint.getSignature().getDeclaringTypeName()+",【请求方法名】："+joinPoint.getSignature().getName()); //此处记录请求类名和方法名，用户还可以自己自定义注解，记录每个方法的描述
			log.setLogType(1061); //日志类型（1601信息，1602异常）
			log.setUserId(0); //用户id-若完成登录功能后在此获取用户的id
			// *========保存数据库=========*//
			logger.info(".............Controller操作日志保存开始.............");
			this.logService.save(log);
			logger.info(".............Controller操作日志保存结束.............");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==前置通知异常==");
			logger.error("【异常信息】：{}", e.getMessage());
		}
	}
	
	/**
	 * 描述：声明环绕通知-用于返回请求数据
	 * @param proceedingJoinPoint
	 * @return
	 * @throws Throwable
	 * @author songfayuan
	 * 2017年12月14日下午1:31:51
	 */
	@Around("controllerAspect()")
	public Object arroundLog(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
		Object result = proceedingJoinPoint.proceed();
		logger.info("【返回值】：{}", JSONObject.toJSONString(result));
		return result;
	}
	
	/**
	 * 描述：声明后置通知-用于记录请求时长
	 * @param joinPoint
	 * @author songfayuan
	 * 2017年12月14日下午1:32:31
	 */
	@AfterReturning("controllerAspect()")
	public void afterReturning(JoinPoint joinPoint){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		Long start = (Long) request.getAttribute(START_TIME);
		Long end = System.currentTimeMillis();
		try {
			logger.info("【请求耗时】：{}毫秒", end - start);
			String header = request.getHeader("User-Agent");
			UserAgent userAgent = UserAgent.parseUserAgentString(header);
			logger.info("【浏览器类型】：{}，【操作系统】：{}，【原始User-Agent】：{}", userAgent.getBrowser().toString(), userAgent.getOperatingSystem().toString(), header);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("==后置通知异常==");
			logger.error("【异常信息】：{}", e.getMessage());
		}
	}
	
	/**
	 * 描述：异常通知-用户拦截service层操作异常
	 * @param joinPoint
	 * @param e
	 * @author songfayuan
	 * 2017年12月14日下午2:01:12
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e){
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		BigInteger time = new BigInteger(System.currentTimeMillis()+"");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String timestr = df.format(new Date(new Long(time+"")));
		try {
			logger.info("【请求时间】:" + timestr);
			logger.info("【异常代码】:" + e.getClass().getName());
			logger.info("【异常信息】:" + e.getMessage());
			logger.info("【异常方法】:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
			Map parameterMap = request.getParameterMap();
			logger.info("【请求参数】：{}，", JSONObject.toJSONString(parameterMap));
			// *========数据库日志=========*//
			LogEntity log = new LogEntity();
			log.setContent("【异常代码】:" + e.getClass().getName()+",【异常信息】:" + e.getMessage()+",【异常方法】:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()")+"。");
			log.setLogType(1061); //日志类型（1601信息，1602异常）
			log.setUserId(0); //用户id-若完成登录功能后在此获取用户的id
			// *========保存数据库=========*//
			logger.info(".............Controller操作日志保存开始.............");
			this.logService.save(log);
			logger.info(".............Controller操作日志保存结束.............");			
		} catch (Exception exception) {
			exception.printStackTrace();
			logger.error("==异常通知异常==");
			logger.error("【异常信息】：{}", exception.getMessage());
		}
	}
	
}
```

### 读取用户操作日志
创建LogController类
```java
package com.songfayuan.springBoot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@RequestMapping("/findLogById")
	public Response findLogById(Integer id){
		LogEntity logEntity = this.logService.findLogById(id);
		return Response.success(logEntity);
	}
	
}
```

创建LogService接口
```java
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
```

创建LogServiceImpl类
```java
package com.songfayuan.springBoot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.dao.LogDao;
import com.songfayuan.springBoot.entity.LogEntity;
import com.songfayuan.springBoot.service.LogService;
import com.songfayuan.springBoot.utils.Page;
import com.songfayuan.springBoot.utils.Response;

/**
 * 描述：
 * @author songfayuan
 * 2017年12月14日下午2:50:02
 */
@Service
public class LogServiceImpl implements LogService {
	
	@Autowired
	private LogDao logDao;

	@Override
	public void save(LogEntity log) {
		this.logDao.save(log);
	}

	@Override
	public Response findLogListByPage(Integer page, Integer pageSize) {
		Integer offset = page > 0 ? page * pageSize : 0;
		List<LogEntity> list = this.logDao.findLogListByPage(offset, pageSize);
		Integer rows = this.logDao.findRows();
		Page<LogEntity> pagelist = new Page<>(page, pageSize, rows);
		pagelist.setData(list);
		return Response.success(pagelist);
	}

	@Override
	public LogEntity findLogById(Integer id) {
		return this.logDao.findLogById(id);
	}

}
```

创建LogDao接口
```java
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
```

===========【end】============
* * *
### 更多的功能正在编辑中...
更多功能正在更新中.....敬请期待！
<a target="_blank" href="http://wpa.qq.com/msgrd?v=3&amp;uin=1414798079&amp;site=qq&amp;menu=yes"><img border="0" src="http://wpa.qq.com/pa?p=2:1414798079:51" alt="点击这里给我发消息" title="点击这里给我发消息" /></a>

[示例代码-github](https://github.com/songfayuan/spring-boot)

### 打赏
分享不易，开心的话，可以请我喝杯咖啡哦O(∩_∩)O~

支付宝：

![](http://images.cnblogs.com/cnblogs_com/songfayuan/1048721/o_o_alipay.jpg)

微信

![](http://images.cnblogs.com/cnblogs_com/songfayuan/1048721/o_o_webChat.png)

