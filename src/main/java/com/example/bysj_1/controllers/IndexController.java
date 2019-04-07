package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class IndexController {
    private UserService userService = new UserService();

    /**
     * 用户信息页面
     * @return
     */
    @RequestMapping("/myInfo")
    public String userinfo(){
        return "/myInfo/myInfo";
    }

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
        return map;
    }


    /**
     * 功能选择页面
     * @return
     */
    @RequestMapping("/myTools")
    public String myTools(HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("roleid",user.getRoleid());
        return "mytools/mytools";
    }
}
