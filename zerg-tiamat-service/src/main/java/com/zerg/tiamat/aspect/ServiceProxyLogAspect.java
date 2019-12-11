package com.zerg.tiamat.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Slf4j
@Order(1)
@Component
public class ServiceProxyLogAspect {
    @Pointcut("execution(public * com.zerg.tiamat.service..*RpcService.*(..))")
    public void serviceProxyPoint() {}

    @Around("serviceProxyPoint()")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            Object[] params = joinPoint.getArgs();
            log.info("ServiceProxy层 className={}, methodName={}, params={}",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(),
                Arrays.toString(params));
            result = joinPoint.proceed();
        } finally {
            log.info("ServiceProxy层 耗时={}(ms), className={}, methodName={}, result={}",
                System.currentTimeMillis() - startTime, joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), result);
        }
        return result;
    }
}
