/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.aspectj
 * 创建时间：2017年12月15日上午10:41:44
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.aspectj;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import com.songfayuan.springBoot.annotation.ControllerMethodDescription;

/**
 * 描述：【声明切面】-用户添加Controller里面方法描述。
 * 		<strong>Order</strong> 定义切面执行的优先级，数字越低，优先级越高 <br>
 *		 在切入点之前执行：按order值有小到大的顺序执行  <br>
 * 		在切入点之后执行：按order值由大到小的顺序执行
 * 
 * @author songfayuan
 * 2017年12月15日上午10:41:44
 */
//【注意】本aop原本用来拦截Controller类方法描述，后将其整合到RequestLogAspect中，故需使用此aop，打开以下注释即可
//@Aspect   
//@Service
//@Order(1)
public class ControllerMethodDescriptionAspect {

	private static final Logger logger = LoggerFactory.getLogger(ControllerMethodDescriptionAspect.class);
	
	//声明切入点
	@Pointcut(value = "@annotation(com.songfayuan.springBoot.annotation.ControllerMethodDescription)")
	public void controllerMethodDescriptionPointcut(){
	}
	
	/**
	 * 描述：声明前置通知-获取方法描述
	 * @param joinPoint
	 * @throws NoSuchMethodException
	 * @author songfayuan
	 * 2017年12月15日上午10:48:04
	 */
	@Before("controllerMethodDescriptionPointcut()")
	public void doBefore(JoinPoint joinPoint) throws NoSuchMethodException{
		//获取拦截的方法名
		Signature signature = joinPoint.getSignature();
		MethodSignature mSignature = null;
		if (!(signature instanceof MethodSignature)) {
			logger.error("ControllerMethodDescription注解只能用于方法");
			throw new IllegalArgumentException("ControllerMethodDescription注解只能用于方法");
		}
		mSignature = (MethodSignature) signature;
		Object target = joinPoint.getTarget();
		//获取拦截方法的参数
		Method method = target.getClass().getMethod(mSignature.getName(), mSignature.getParameterTypes());
		//获取操作业务的名称
		ControllerMethodDescription annotation = method.getAnnotation(ControllerMethodDescription.class);
		String description = annotation.description();
		logger.info("***********************************************");
		logger.info("【方法描述】：进入{}方法...", description);
		logger.info("***********************************************");
	}
	
}
