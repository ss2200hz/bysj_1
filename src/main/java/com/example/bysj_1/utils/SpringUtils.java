package com.example.bysj_1.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if(SpringUtils.applicationContext == null){
            SpringUtils.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static Object getBean(String beanName){
        return getApplicationContext().getBean(beanName);
    }

    public static <T>T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    public static <T>T getBean(Class<T> clazz,String beanName){
        return getApplicationContext().getBean(clazz,beanName);
    }
}
