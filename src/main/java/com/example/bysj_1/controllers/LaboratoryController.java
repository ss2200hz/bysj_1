package com.example.bysj_1.controllers;

import com.example.bysj_1.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 预约控制器类
 */
@Controller
@RequestMapping(value = "/appointment")
public class LaboratoryController {
    private LaboratoryService laboratoryService = new LaboratoryService();

    @RequestMapping(value = "/laboratory",method = RequestMethod.GET)
    public String appointLaboratory(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.print(session.getId());
        return "/appointment";
    }

}
