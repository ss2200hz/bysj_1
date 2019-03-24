package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
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
    private ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);

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

    public boolean insertUser(User user) {
        String userName = user.getLoginname();
        //校验是否有重复
        List<User> list = userMapper.findUserById(userName);
        if (StringUtils.isEmpty(user.getPassword()) || !CollectionUtils.isEmpty(list)) {
            return false;
        }
        //校验是否本校教师
        List<String> list1 = userMapper.isUserInTeacher(user);
        if (CollectionUtils.isEmpty(list1)) {
            return false;
        }
        user.setSignupDate(LocalDate.now());
        userMapper.addUser(user);
        return true;
    }

    /**
     * 查询当前用户的所有信息
     * @param userId
     * @return
     */
    public HashMap<String, Object> getUserInfo(String userId) {
        HashMap<String, Object> result = new HashMap();
        User user = userMapper.getUserInfoById(userId);
        if (user != null) {
            result.put("username", user.getName());
            result.put("idCard", user.getLoginname());
            result.put("roleid", user.getRoleid());
            result.put("classNo", user.getClassNo());
            String className = classMapper.getClassNameById(user.getClassNo());
            result.put("className", className);
            result.put("phone", user.getPhone());
            result.put("email", user.getEmail());
            result.put("inductDate", user.getSignupDate());
        }else{
            result.put("error",true);
            result.put("errorInfo","can't find this user");
        }
        return result;
    }
}
