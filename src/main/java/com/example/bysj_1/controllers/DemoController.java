package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.service.DemoUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/html")
public class DemoController {
    private DemoUserService demoUserService = new DemoUserService();

    @RequestMapping(value = "/hello",method = RequestMethod.POST)
    public String hello(@ModelAttribute(value = "User")User user, Model model){
        model.addAttribute("name",user.getLoginname());
        return "template/hello";
    }
}
