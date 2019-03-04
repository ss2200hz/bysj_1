package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.moduls.UserSession;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.bysj_1.service.UserService.userSession;

@Controller
public class LoginController {
    private UserService userService = new UserService();

    @RequestMapping("/login")
    public String Login(User user){
        return "/login";
    }

    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request,@ModelAttribute("user") User user,Model model){
        if(userService.checkLogin(user)){
            userSession = request.getSession();
            userSession.setAttribute("username",user.getLoginname());
            System.out.print(userSession.getId()+":"+userSession.getAttribute("username"));
        }else{
            throw new RuntimeException("密码错误！");
        }
        ProfileController profileController = new ProfileController();
        return profileController.demo(model);
    }
}
