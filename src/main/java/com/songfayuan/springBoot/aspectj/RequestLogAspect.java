/**
 * 项目名称：spring-boot
 * 项目包名：com.songfayuan.springBoot.aspectj
 * 创建时间：2017年12月14日上午11:26:24
 * 创建者：Administrator-宋发元
 * 创建地点：杭州
 */
package com.songfayuan.springBoot.aspectj;

import java.lang.reflect.Method;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.songfayuan.springBoot.annotation.ControllerMethodDescription;
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
@Order(0)
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
			logger.info("【请求方式】：{}", request.getMethod());
			logger.info("【请求 IP】：{}", request.getRemoteAddr());
			logger.info("【请求类名】：{}，【请求方法名】：{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
			logger.info("【方法描述】：{}", getControllerMethodDescription(joinPoint));
			Map parameterMap = request.getParameterMap();
			logger.info("【请求参数】：{}，", JSONObject.toJSONString(parameterMap));
			Long start = System.currentTimeMillis();
			request.setAttribute(START_TIME, start);
			// *========数据库日志=========*//
			LogEntity log = new LogEntity();
			if (getControllerMethodDescription(joinPoint)!=null) {
				log.setContent("【方法描述】:"+getControllerMethodDescription(joinPoint)); //己自定义注解，记录每个方法的描述
			}else {
				log.setContent("【请求类名】:"+joinPoint.getSignature().getDeclaringTypeName()+",【请求方法名】："+joinPoint.getSignature().getName()+"【方法描述】：none");
			}
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
			logger.info("【请求时间】:{}" + timestr);
			logger.info("【请求方式】：{}", request.getMethod());
			logger.info("【异常代码】:{}" + e.getClass().getName());
			logger.info("【异常信息】:{}" + e.getMessage());
			logger.info("【异常方法】:{}" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
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
	
	/**
	 * 描述：获取注解中对方法的描述信息-用于Controller层注解
	 * @param joinPoint
	 * @return
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @author songfayuan
	 * 2017年12月15日上午11:36:55
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws NoSuchMethodException, SecurityException {
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
		String description = null;
		if (annotation!=null) {
			description = annotation.description();
		}
		return description;
	}
	
}
