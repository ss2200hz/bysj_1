package com.example.bysj_1.service;

import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.moduls.UserSession;
import com.example.bysj_1.moduls.response.Response;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.SpringUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
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

    public Response<String> insertUser(User user){
        String userName = user.getLoginname();
        List<User> list = userMapper.findUserById(userName);
        if(StringUtils.isEmpty(user.getPassword())){
            return new Response(Response.FORMAT_ERROR_STATUS);
        }
        if(!CollectionUtils.isEmpty(list)){
            return new Response<String>(Response.FORMAT_ERROR_STATUS,"用户名重复！");
        }
        user.setSignupDate(LocalDate.now());
        user.setUserid(Integer.parseInt(userMapper.findAllUserId().get(0))+1+"");
        userMapper.addUser(user);
        return new Response<>();
    }
}
