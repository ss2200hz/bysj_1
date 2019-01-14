package com.example.bysj_1.service;

import com.example.bysj_1.dao.DemoUserMap;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class DemoUserService {

    @Autowired
    DemoUserMap demoUserMap;

    public String getUsername(){
        SqlSession sqlSession = MyBatisUtils.getSession(true);
        demoUserMap = sqlSession.getMapper(DemoUserMap.class);
        String name = "";
        List<User> list = demoUserMap.getUsers();
        if(!CollectionUtils.isEmpty(list)){
            User user = list.get(0);
            name = user.getName();
        }
        return name;
    }
}
