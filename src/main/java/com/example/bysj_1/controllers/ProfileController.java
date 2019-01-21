package com.example.bysj_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @RequestMapping("/profilePage")
    public String displayProfile(){
        return "template/profilePage";
    }
}
