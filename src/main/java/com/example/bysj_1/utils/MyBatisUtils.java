package com.example.bysj_1.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {
    private static Reader reader;
    private static SqlSessionFactory factory;
    private static String cfg = "mybatis/mybatis.cfg.xml";
    private static void setSqlSessionFactory(){
        try {
            //读取配置文件
            reader = Resources.getResourceAsReader(cfg);
            factory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            System.out.println("找不到文件！");
            e.printStackTrace();
        }
    }

    public static SqlSession getSession(boolean yes){
        setSqlSessionFactory();
        return factory.openSession(yes);
    }
}
