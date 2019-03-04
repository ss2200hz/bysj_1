package com.example.bysj_1.service;

import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.moduls.UserSession;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.SpringUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
public class UserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    public static HttpSession userSession;

    public boolean checkLogin(User user) {
        List<User> userList = userMapper.findUserById(user.getLoginname());
        if (CollectionUtils.isEmpty(userList)) {
            return false;
        }
        User result = userList.get(0);
        if (StringUtils.equals(result.getPassword(), user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public HttpSession getUserSession(){
        return userSession;
    }
}
