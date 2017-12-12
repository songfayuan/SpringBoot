# Spring Boot Demo
简介：Springboot modules

## 基础项目搭建
### maven构建项目
1、访问http://start.spring.io/
2、选择构建工具Maven Project、Spring Boot版本1.3.6以及一些工程基本信息，点击“Switch to the full version.”java版本选择1.8，可参考下图所示：
http://www.ityouknow.com/assets/images/2016/springboot1.png

### 基础pom.xml
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

### 编写第一个程序Hello Word
