package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.UserSession;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProfileController {
    @RequestMapping("/profilePage")
    public String displayProfile(){
        return "template/profilePage";
    }

    public String demo(Model model){
        System.out.print(UserService.userSession.getId()+":"+ UserService.userSession.getAttribute("username"));
        model.addAttribute("name",UserService.userSession.getAttribute("username"));
        return "/index";
    }
}
