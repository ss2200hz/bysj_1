package com.example.bysj_1.controllers;

import com.example.bysj_1.service.AppointmentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 预约控制器类
 */
@Controller
@RequestMapping(value = "/appointment")
public class AppointmentController {
    private AppointmentService appointmentService = new AppointmentService();

    @RequestMapping(value = "/laboratory",method = RequestMethod.GET)
    public String appointLaboratory(HttpServletRequest request){
        HttpSession session = request.getSession();
        System.out.print(session.getId());
        return "/appointment";
    }

}
