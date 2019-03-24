package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

@Controller
public class IndexController {
    private UserService userService = new UserService();

    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo",method = RequestMethod.GET)
    public HashMap<String, Object> getUserInfo(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap();
        try {
            User user = (User) request.getSession().getAttribute("user");
            String userId = user.getLoginname();
            map = userService.getUserInfo(userId);
        } catch (ClassCastException e) {
            map.put("error", true);
            map.put("errorInfo","cast error");
            e.printStackTrace();
        }
        System.out.println(map);
        return map;
    }

    /**
     * 用户信息页面
     * @return
     */
    @RequestMapping("/myInfo")
    public String userinfo(){
        return "/myInfo/myInfo";
    }
}
