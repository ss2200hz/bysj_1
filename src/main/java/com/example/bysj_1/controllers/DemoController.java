package com.example.bysj_1.controllers;

import com.example.bysj_1.service.DemoUserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    DemoUserService demoUserService = new DemoUserService();
    @RequestMapping("/Test")
    public String doTest(){
        return demoUserService.getUsername();
    }
}
