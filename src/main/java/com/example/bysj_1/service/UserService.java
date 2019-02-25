package com.example.bysj_1.service;

import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    public User getUser(User user) {
        List<User> userList = userMapper.checkUser(user.getLoginname());
        if (CollectionUtils.isEmpty(userList)) {
            return new User();
        }
        if(userList.size()>0){
            throw new RuntimeException("");
        }
        User user1 = userList.get(0);
        if(StringUtils.equals(user.getPassword(),user1.getPassword())){
            return user1;
        }else{
            throw new RuntimeException("密码错误");
        }
    }
}
