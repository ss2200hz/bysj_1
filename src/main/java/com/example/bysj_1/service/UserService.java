package com.example.bysj_1.service;

import com.example.bysj_1.consts.DemoUserChecked;
import com.example.bysj_1.consts.UserConsts;
import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.User;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.util.CollectionUtils;

import java.util.List;

public class UserService {

    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
    public DemoUserChecked checkUser(User user){
        List<User> userList = userMapper.checkUser(user.getLoginname());
        if(CollectionUtils.isEmpty(userList)){
//            result = UserConsts.USER_EMPTY;
            return DemoUserChecked.USER_EMPTY;
        }else {
            if(userList.size()>1){
//                result = UserConsts.USER_MORE;
                return DemoUserChecked.USER_MORE;
            }else{
                User user1 = userList.get(0);
                String password = user1.getPassword();
                if(StringUtils.equals(password,user.getPassword())){
//                    result = UserConsts.PASSWORD_TRUE;
                    return DemoUserChecked.PASSWORD_TRUE;
                }else{
//                    result = UserConsts.PASSWORD_FALSE;
                    return DemoUserChecked.PASSWORD_FALSE;
                }
            }
        }
//        return result;
    }

    public User getUser(User user) {
        List<User> userList = userMapper.checkUser(user.getLoginname());
        if (CollectionUtils.isEmpty(userList)) {
//            result = UserConsts.USER_EMPTY;
            return new User();
        }
        User resultUser = userList.get(0);
        return resultUser;
    }
}
