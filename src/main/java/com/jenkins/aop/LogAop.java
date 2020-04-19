package com.jenkins.aop;

import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component  //加入到IoC容器
@Aspect  //指定当前类为切面类
public class LogAop {

    private static final Logger log = LoggerFactory.getLogger(LogAop.class);

    //指定切入点表达式，拦截那些方法，即为那些类生成代理对象
    @Pointcut("execution(public * com.jenkins.controller..*.*(..))")
    public void controllerAspect(){}

    @Pointcut("execution(public * com.jenkins.exception..*.*(..))")
    public void exceptionAspect(){}

    @Before("controllerAspect()")
    public void begin(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        Object[] args = joinPoint.getArgs();
        System.out.println();
        String classType = joinPoint.getTarget().getClass().getName();
        Class<?> clazz = Class.forName(classType);
        String clazzName = clazz.getName();
        String methodName = joinPoint.getSignature().getName(); // 获取方法名称
        // 获取参数名称和值
        StringBuffer sb = LogAopUtil.getNameAndArgs(this.getClass(), clazzName, methodName, args);
        log.info("请求参数：" + request.getServletPath() + "?" + sb + ",IP:" + getRealIP(request));

    }
    public String getRealIP(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    @After("controllerAspect()")
    public void close(){}

    @AfterReturning(returning = "o", pointcut = "controllerAspect()")
    public void methodAfterReturing(Object o) {
        log.info("响应参数:" + JSON.toJSONString(o));
    }

    @AfterReturning(returning = "o", pointcut = "exceptionAspect()")
    public void exceptionAfterReturing(Object o) {
        log.info("响应参数:" + JSON.toJSONString(o));
    }
}