package com.example.bysj_1.service;

import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

    public static HttpSession userSession;

    public User checkLogin(User user) {
        List<User> userList = userMapper.findUserById(user.getLoginname());
        if (CollectionUtils.isEmpty(userList)) {
            return null;
        }
        User result = userList.get(0);
        if (StringUtils.equals(result.getPassword(), user.getPassword())) {
            return result;
        } else {
            return null;
        }
    }

    public boolean insertUser(User user){
        String userName = user.getLoginname();
        //校验是否有重复
        List<User> list = userMapper.findUserById(userName);
        if(StringUtils.isEmpty(user.getPassword()) || !CollectionUtils.isEmpty(list)){
            return false;
        }
        //校验是否本校教师
        List<String> list1 = userMapper.isUserInTeacher(user);
        if(CollectionUtils.isEmpty(list1)){
            return false;
        }
        user.setSignupDate(LocalDate.now());
        userMapper.addUser(user);
        return true;
    }

    public HashMap getUserInfo(String userId) {
        HashMap result = new HashMap();
        User user = userMapper.getUserInfoById(userId);
        result.put("username",user.getName());
        result.put("idCard",user.getLoginname());
        result.put("roleid",user.getRoleid());
        result.put("classNo",user.getClassNo());
        result.put("phone",user.getPhone());
        result.put("email",user.getEmail());
        result.put("inductDate",user.getSignupDate());
        return result;
    }
}
