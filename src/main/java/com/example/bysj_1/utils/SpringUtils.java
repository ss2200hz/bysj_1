package com.example.bysj_1.utils;

import org.apache.ibatis.io.Resources;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Enumeration;
import java.util.Properties;

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

//    public static Object getInfoByYml(String resource,Object key){
//        Yaml yaml = new Yaml();
//        File f = new File(resource);
//        try {
//            Map map = yaml.load(new FileInputStream(f));
//            Object result = map.get(key);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    public static String getInfoByProp(String resource,String key){
        Properties prop = new Properties();
        try {
//            prop.load(new FileInputStream(resource));
            Reader reader = Resources.getResourceAsReader(resource);
            prop.load(reader);
            String result = prop.getProperty(key);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("找不到文件！");
        }
    }
}
