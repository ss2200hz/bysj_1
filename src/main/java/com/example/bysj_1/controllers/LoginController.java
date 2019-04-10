package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class LoginController {
    private UserService userService = new UserService();
//    PeopleCount peopleCount = PeopleCount.getInstence();

    /**
     * 登录页面
     *
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String Login(User user) {
        return "/login";
    }

    /**
     * 校验用户
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/checkUser", method = RequestMethod.GET)
    public HashMap checkUser(HttpServletRequest request, String loginname,String password) {
        User user = new User();
        user.setLoginname(loginname);
        user.setPassword(password);
        return userService.checkUser(request,user);
    }

    /**
     * 返回首页
     *
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("name", user.getName());
        model.addAttribute("roleid", user.getRoleid());
        model.addAttribute("logInfo", true);
//        System.out.println(peopleCount.loginCount);
        return "/index";
    }

    /**
     * 注册页面
     *
     * @return
     */
    @RequestMapping(value = "/singup")
    public String singup(User user) {
        return "/singup";
    }

    /**
     * 新用户注册
     *
     * @return
     */
    @RequestMapping(value = "/inseruser", method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("user") User user) {
        if (userService.insertUser(user)) {
            return "/login";
        } else {
            return "/inseruser";
        }
    }

    @RequestMapping(value = "/exitLogin",method = RequestMethod.GET)
    public String exitLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if(user != null){
            session.setAttribute("user",null);
            return "/login";
        }
        return "";
    }
}
