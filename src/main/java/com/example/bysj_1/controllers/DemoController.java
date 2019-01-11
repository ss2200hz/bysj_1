package com.example.bysj_1.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @RequestMapping("/Test")
    public String doTest(){
        return "Hello World";
    }
}
