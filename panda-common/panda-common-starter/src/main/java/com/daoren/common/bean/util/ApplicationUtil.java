package com.daoren.common.bean.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Application工具类
 *
 * @author peng_da
 * @date 2022/9/2 16:58
 */
@Component
public class ApplicationUtil implements ApplicationContextAware, InitializingBean {
    private ApplicationContext applicationContext = null;
    private DefaultListableBeanFactory beanFactory = null;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        this.beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();
    }

    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
