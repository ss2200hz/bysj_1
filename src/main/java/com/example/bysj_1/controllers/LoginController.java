package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    private UserService userService = new UserService();

    @RequestMapping("/index")
    public String login(@ModelAttribute("user") User user){
        return "login";
    }

    @RequestMapping("/login")
    public String checkUser(@ModelAttribute("user") User user, Model model){
//        String userName = user.getName();
//        String password = user.getPassword();
//        if(userService.checkUser(user)== DemoUserChecked.PASSWORD_TRUE){
//            model.addAttribute("name",user.getName());
//            return "/template/hello";
//        }else{
//            return "";
//        }
        model.addAttribute("name",userService.getUser(user).getName());
        return "/template/hello";
    }
}
