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
//            result = UserConsts.USER_EMPTY;
            return new User();
        }
        return userList.get(0);
    }
}
