package com.bigdata.hdfs.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author
 * @date 2019-07-30 18:46
 */

@Aspect
@Component
public class LogAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 定义日志切入点
     */
    @Pointcut("execution(public * com.bigdata.hdfs.controller.*.*(..))")
    public void logCut() {
    }

    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 在切入点开始处切入内容
     *
     * @param joinPoint
     */
    @Before("logCut()")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("LogAspect logBefore begin-------------------------------------------------------");

        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //获取所有参数
        Enumeration<String> enu = request.getParameterNames();
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            System.out.println(paraName + ": " + request.getParameter(paraName));
        }
    }

    /**
     * 在切入点return内容之后切入内容
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "logCut()")
    public void logAfterReturning(Object object) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info(request.getRequestURL().toString() + "   接口用时：" + (System.currentTimeMillis() - startTime.get()) + "ms");
        logger.info("LogAspect logAfterReturning end------------------------------------------------");
    }


}
