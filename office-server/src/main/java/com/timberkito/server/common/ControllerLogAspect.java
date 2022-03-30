package com.timberkito.server.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 废弃的请求日志输出
 *
 * @author Timber.Wang
 * @date 2022-01-25 2:32 PM
 */

@Aspect
@Component
@Slf4j
public class ControllerLogAspect {

    private final ObjectMapper mapper;

    @Autowired
    public ControllerLogAspect(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    /**
     * @param
     * @return void
     * @author Timber.Wang
     * @describe: controller包下所有请求设为切入点
     * @date 2022-01-25 2:48 PM
     */
    @Pointcut("execution(public * com.timberkito.server.controller.*.*(..))")
    public void webLog() {
    }

    /**
     * @param joinPoint
     * @return void
     * @author Timber.Wang
     * @describe: 切入点前织入
     * @date 2022-01-25 2:49 PM
     */
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        // 开始打印请求日志
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        // 打印请求相关参数
        log.info("========================================== Start ==========================================");
        // 打印请求 url
        log.info("URL           : {}", request.getRequestURL().toString());
        // 打印 Http method
        log.info("HTTP Method   : {}", request.getMethod());
        // 打印调用 controller 的全路径以及执行方法
        log.info("Class Method  : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    /**
     * @param proceedingJoinPoint
     * @return java.lang.Object
     * @author Timber.Wang
     * @describe: 环绕切入点
     * @date 2022-01-25 2:57 PM
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        if (result != null) {
            // 打印出参
            log.info("Response Args : {}", mapper.writeValueAsString(result));
        }
        // 执行耗时
        log.info("Time-Consuming: {} ms", System.currentTimeMillis() - startTime);
        log.info("=========================================== End ===========================================");
        return result;
    }
}
