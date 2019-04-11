package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
import com.example.bysj_1.dao.UserMapper;
import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.StringUtils;
import com.example.bysj_1.utils.TimeUtils;
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

    /**
     * 用户注册
     */
    public HashMap signupUser(HashMap data) {
        HashMap map = new HashMap();
        String loginname = (String) data.get("id");
        User user = isSchoolTeacher(loginname);
        if (!isRepeatUser(loginname) && user != null) {
            String name = user.getName();
            data.put("name",name);
            data.put("signupDate",LocalDate.now());
            try{
                user.setLoginname(loginname);
                user.setUserid(loginname);
                user.setRoleid((String) data.get("roleid"));
                user.setPassword((String) data.get("password"));
                user.setSignupDate(LocalDate.now());
                userMapper.addUser(user);
                userMapper.updateTeacherInfo(loginname,(String)data.get("phone"), (String) data.get("email"));
                map.put("succeed",true);
            }catch (Exception e){
                map.put("succeed", false);
                map.put("errorInfo", "unknow error");
                e.printStackTrace();
            }
        }else{
            map.put("succeed", false);
            map.put("errorInfo", "用户名重复或不是本校教师，请联系管理员");
        }
        return map;
    }

    /**
     * 校验是否有重复
     */
    private boolean isRepeatUser(String userid) {
        List<User> list = userMapper.findUserById(userid);
        if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否本校教师
     */
    private User isSchoolTeacher(String userid) {
        List<User> list = userMapper.isUserInTeacher(userid);
        if (!CollectionUtils.isEmpty(list) && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
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
            result.put("password", user.getPassword());
            result.put("roleid", user.getRoleid());
            List<String> classNames = userMapper.getTeacherClass(userId);
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

    /**
     * 修改我的信息
     */
    public HashMap changeUserInfo(HashMap data) {
        String id = (String) data.get("id");
        HashMap map = new HashMap();
        if (StringUtils.isNotEmpty(id)) {
            String phone = (String) data.get("phone");
            String email = (String) data.get("email");
            try {
                userMapper.updateTeacherInfo(id, phone, email);
                map.put("succeed", true);
            } catch (Exception e) {
                map.put("succeed", false);
                map.put("errorInfo", "unknow error");
                e.printStackTrace();
            }
        } else {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
        }
        return map;
    }

    /**
     * 获取用户列表页数
     */
    public HashMap getUerListNum(int pageSize) {
        HashMap map = new HashMap();
        int total = classMapper.getClassNum();//总记录数
        int pageNum;//总页数
        if (total % pageSize == 0) {
            pageNum = total / pageSize;
        } else {
            pageNum = total / pageSize + 1;
        }
        map.put("pageNum", pageNum);
        return map;
    }

    /**
     * 获取用户列表
     */
    public HashMap getUerList(int pageNo, int pageSize) {
        HashMap map = new HashMap();
        int min = 1;
        if (pageNo > 0) {
            min = (pageNo - 1) * pageSize;
        }
        List<HashMap<String, Object>> list = userMapper.getUserList(min, pageSize);
        for (HashMap hashMap : list) {
            String id = (String) hashMap.get("id");
            User user = userMapper.getUserInfoById(id);
            if (user != null) {
                hashMap.put("inductDate", user.getSignupDate());
            }
        }
        map.put("resultList", list);
        return map;
    }

    /**
     * 删除用户
     */
    public HashMap deleteUser(HashMap data) {
        HashMap<String, Object> map = new HashMap<>();
        boolean succeed = true;
        List<String> idList = (List) data.get("dataList");
        List<String> errorList = new ArrayList();
        if (!CollectionUtils.isEmpty(idList) && idList.size() > 0) {
            for (String id : idList) {
                try {
                    userMapper.deleteUser(id);
                } catch (Exception e) {
                    succeed = false;
                    errorList.add(id);
                    map.put("errorInfo", "unknow error");
                    e.printStackTrace();
                }
            }
        }
        map.put("succeed", succeed);
        map.put("errorList", errorList);
        return map;
    }

    /**
     * 新增用户
     */
    public HashMap addUser(HashMap data) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            userMapper.addUserByData(data);
            User user = new User();
            user.setLoginname((String) data.get("id"));
            user.setPassword((String) data.get("password"));
            user.setName((String) data.get("name"));
            user.setRoleid((String) data.get("roleid"));
            user.setSignupDate(TimeUtils.String2Date((String) data.get("signupDate")));
            userMapper.addUser(user);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 修改用户
     */
    public HashMap edituser(HashMap data) {
        HashMap<String, Object> map = new HashMap<>();
        try {
            userMapper.updateUser(data);
            userMapper.updateTeacherInfoByData(data);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }
}
