package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.ClassService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * 课程管理控制类
 */
@Controller
@RequestMapping("/class")
public class ClassController {
    private ClassService classService = new ClassService();

    /**
     * 跳转页面
     */
    @RequestMapping("/class_info")
    public String toClassPage(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("userid",user.getLoginname());
        model.addAttribute("roleid",user.getRoleid());
        return "/class/class";
    }

    /**
     * 获得所有课程信息数量
     */
    @ResponseBody
    @RequestMapping(value = "/getclassNum",method = RequestMethod.GET)
    public HashMap getClassNum(int pageSize){
        return classService.getClassNum(pageSize);
    }

    /**
     * 获得所有课程信息
     */
    @ResponseBody
    @RequestMapping(value = "/classList",method = RequestMethod.GET)
    public HashMap getClassList(int pageNo, int pageSize){
        return classService.getClassList(pageNo,pageSize);
    }

    /**
     * 按条件查询课程信息
     */
    @ResponseBody
    @RequestMapping(value = "/classListByCondition",method = RequestMethod.GET)
    public HashMap getClassListByCondition(@RequestBody HashMap data){
        return classService.getClassListByCondition(data);
    }

    /**
     * 删除课程
     */
    @ResponseBody
    @RequestMapping(value = "/editClass",method = RequestMethod.DELETE)
    public HashMap deleteClass(@RequestParam("classNo")String classNo){
        return classService.deleteClass(classNo);
    }

    /**
     * 加入课程
     */
    @ResponseBody
    @RequestMapping(value = "/editClassTeacher",method = RequestMethod.POST)
    public HashMap joinClass(@RequestBody HashMap data){
        return classService.joinClass(data);
    }

    /**
     * 退出课程
     */
    @ResponseBody
    @RequestMapping(value = "/editClassTeacher",method = RequestMethod.DELETE)
    public HashMap exitClass(@RequestBody HashMap data){
        return classService.exitClass(data);
    }

    /**
     * 查询某教师的所有课程
     */
    @ResponseBody
    @RequestMapping(value = "/classListByUser",method = RequestMethod.GET)
    public HashMap getClassByUserId(@RequestParam("id") String userid) {
        return classService.getClassByUserId(userid);
    }
}
