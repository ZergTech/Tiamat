package com.zerg.tiamat.config;

import org.apache.commons.lang3.Validate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * SpringContextHolder<p>
 * 代码描述<p>
 * Copyright: Copyright (C) 2019 XXX, Inc. All rights reserved. <p>
 * Company: 金瓜子科技发展（北京）有限公司<p>
 *
 * @author czzhang
 * @since 2019/1/14 21:02
 */
@Component
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext = null;

    /**
     * 获取静态变量中的ApplicationContext.
     */
    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量applicationContext中得到Bean, 自动转型为所赋值对象的类型.
     */
    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 清除SpringContextHolder中的ApplicationContext为Null.
     */
    public static void clearHolder() {
        applicationContext = null;
    }

    /**
     * 实现ApplicationContextAware接口, 注入Context到静态变量中.
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextHolder.applicationContext = applicationContext;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void assertContextInjected() {
        Validate.validState(applicationContext != null,
                "applicaitonContext属性未注入, 请检查配置");
    }

}

