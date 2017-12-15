/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.annotation
 * 创建时间：2017年12月15日上午10:36:37
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target(METHOD)
/**
 * 描述：自定义注解-用于添加Controller方法描述
 * @author songfayuan
 * 2017年12月15日上午10:36:37
 */
public @interface ControllerMethodDescription {
	
	/**
	 * 描述：方法描述
	 * @return
	 * @author songfayuan
	 * 2017年12月15日上午10:40:56
	 */
	String description() default "controller method default description...";
	
}
