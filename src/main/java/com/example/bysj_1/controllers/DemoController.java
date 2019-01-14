package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.DemoUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {
    DemoUserService demoUserService = new DemoUserService();
    @RequestMapping("/test1")
    public String doTest(){
        return demoUserService.addUser();
    }

    @RequestMapping("/test2")
    public List<User> getUsers(){
        return demoUserService.getUsers();
    }
}
