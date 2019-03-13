package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

import static com.example.bysj_1.service.UserService.userSession;

@Controller
public class LoginController {
    private UserService userService = new UserService();

    /**
     * 登录页面
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String Login(User user){
        return "/login";
    }

    /**
     * 校验用户
     * @param request
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request,@ModelAttribute("user") User user,Model model){
        if(userService.checkLogin(user)){
            userSession = request.getSession();
            userSession.setAttribute("user",user);
            System.out.println(userSession.getId());
            model.addAttribute("name",userSession.getAttribute("username"));
            model.addAttribute("logInfo",true);
            return this.index(model);
        }else{
            model.addAttribute("logInfo",false);
            return "/login";
        }
    }

    /**
     * 返回首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        return "/index";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/singup")
    public String singup(User user){
        return "/singup";
    }

    /**
     * 新用户注册
     * @return
     */
    @RequestMapping(value = "/inseruser",method = RequestMethod.POST)
    public String insertUser(@ModelAttribute("user")User user){
        if(userService.insertUser(user)){
            return "/login";
        }else{
            return "/inseruser";
        }
    }
}
