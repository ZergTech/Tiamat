package com.zerg.tiamat.aspect;

import com.alibaba.dubbo.rpc.RpcContext;
import com.zerg.tiamat.common.utils.RequestUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Order(-10)
@Component
@Slf4j
public class ServiceRequestIdAspect {
    @Pointcut("execution(public * com.zerg.tiamat.service..*ServiceImpl.*(..))")
    public void serviceImplPoint() {
    }

    /**
     * service的requestId处理策略
     * <p>
     * 1.Controller调用：MDC中有requestId。使用MDC中。不需要清除MDC
     * 2.dubbo调用：MDC中没有requestId，RpcContext中有requestId。把RpcContext的requestId放入MDC。调用完成后清除MDC中requestId
     * 3.其他项目dubbo调用：MDC，RpcContext都没有。需要生成requestId。调用完成后清除MDC中requestId
     *
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("serviceImplPoint()")
    public Object doServiceImplPointAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        // Controller调用
        if (MDC.get(RequestUtils.REQUEST_ID_KEY) != null) {
            result = joinPoint.proceed();
        } else {
            // 检查dubbo上下文中是否有requestId
            String requestId = RpcContext.getContext().getAttachment(RequestUtils.REQUEST_ID_KEY);
            if (requestId == null) {
                // 其他项目dubbo调用
                requestId = RequestUtils.generateRequestId();
            }
            // 调用，并在完成后清除MDC的requestId
            MDC.put(RequestUtils.REQUEST_ID_KEY, requestId);
            try {
                result = joinPoint.proceed();
            } finally {
                MDC.remove(RequestUtils.REQUEST_ID_KEY);
            }
        }
        return result;
    }
}
