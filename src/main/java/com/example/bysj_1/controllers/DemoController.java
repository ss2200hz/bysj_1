package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.DemoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/html")
public class DemoController {
    private DemoUserService demoUserService = new DemoUserService();
    @RequestMapping("/test1")
    public String doTest(){
        return demoUserService.getUsername();
    }

    @RequestMapping("/test2")
    public List<User> getUsers(){
        return demoUserService.getUsers();
    }

    @RequestMapping("/hello")
    public String hello(Model model){
        model.addAttribute("name","world");
        return "template/hello";
    }
}
