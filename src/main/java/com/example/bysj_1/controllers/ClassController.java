package com.example.bysj_1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 课程管理控制类
 */
@Controller
@RequestMapping("/class")
public class ClassController {
    /**
     * 跳转页面
     */
    @RequestMapping("/class_info")
    public String toClassPage(){
        return "/class/class";
    }

}
