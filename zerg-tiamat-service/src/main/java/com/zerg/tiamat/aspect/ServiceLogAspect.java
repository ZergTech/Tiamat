package com.zerg.tiamat.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * FileName : ServiceLogAspect
 * <p>
 * ProjectName : xinche-after-insurance
 * <p>
 * PackageName : com.maodou.after.insurance.aspect
 * <p>
 * Description : service层切面日志
 *
 * @author : daisenrong
 * @version : 1.0.0
 * @date : 2018/11/16 22:29
 */
@Aspect
@Slf4j
@Order(0)
@Component
public class ServiceLogAspect  {
    @Pointcut("execution(public * com.zerg.tiamat.service..*ServiceImpl.*(..))")
    public void servicePoint() {
    }

    @Around("servicePoint()")
    public Object doServiceAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        try {
            Object[] params = joinPoint.getArgs();
            log.info("Service层 className={}, methodName={}, params={}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), Arrays.toString(params));
            result = joinPoint.proceed();
        }
        finally {
            log.info("Service层 耗时={}(ms), className={}, methodName={}, result={}", System.currentTimeMillis() - startTime, joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName(), result);
        }
        return result;
    }
}
