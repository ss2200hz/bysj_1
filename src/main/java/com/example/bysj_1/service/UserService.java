package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    private ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);

    public HashMap checkUser(HttpServletRequest request, User user) {
        HashMap map = new HashMap();
        List<User> userList = userMapper.findUserById(user.getLoginname());
        if (CollectionUtils.isEmpty(userList) || userList.size() == 0) {
            map.put("successed", false);
            map.put("errorInfo", "找不到该用户，请确认用户名是否正确");
            return map;
        }
        User result = userList.get(0);
        if (StringUtils.equals(DigestUtils.md5DigestAsHex(result.getPassword().getBytes()), user.getPassword())) {
            HttpSession session = request.getSession();
            session.setAttribute("user", result);
            map.put("successed", true);
        } else {
            map.put("successed", false);
            map.put("errorInfo", "密码不正确");
        }
        return map;
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
     *
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
//            result.put("classNo", user.getClassNo());
            List<String> classNos = StringUtils.String2List(user.getClassNo(), ",");
            List<String> classNames = new ArrayList<>();
            for (String classNo : classNos) {
                String className = classMapper.getClassNameById(classNo);
                classNames.add(className);
            }
            result.put("className", StringUtils.List2String(classNames));
            result.put("phone", user.getPhone());
            result.put("email", user.getEmail());
            result.put("inductDate", user.getSignupDate());
        } else {
            result.put("error", true);
            result.put("errorInfo", "can't find this user");
        }
        return result;
    }
}
