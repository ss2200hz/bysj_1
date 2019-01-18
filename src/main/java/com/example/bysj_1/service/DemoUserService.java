package com.example.bysj_1.service;

import com.example.bysj_1.dao.DemoUserMap;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.utils.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoUserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private DemoUserMap demoUserMap = sqlSession.getMapper(DemoUserMap.class);

    public List getUsers(){
        List<User> list = demoUserMap.getUsers();
        List<String> nameList = new ArrayList<>();
        if(!CollectionUtils.isEmpty(list)){
            for(User user : list){
                String name = user.getName();
                nameList.add(name);
            }
        }
        return nameList;
    }

}
