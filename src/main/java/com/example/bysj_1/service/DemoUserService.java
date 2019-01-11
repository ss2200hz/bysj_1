package com.example.bysj_1.service;

import com.example.bysj_1.dao.DemoUserMap;
import com.example.bysj_1.moduls.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class DemoUserService {
    @Autowired
    DemoUserMap demoUserMap = new DemoUserMap() {
        @Override
        public List<User> getUsers() {
            return new ArrayList<>();
        }
    };
    public String getUsername(){
        String name = "";
        List<User> list = demoUserMap.getUsers();
        if(!CollectionUtils.isEmpty(list)){
            User user = list.get(0);
            name = user.getName();
        }
        return name;
    }
}
