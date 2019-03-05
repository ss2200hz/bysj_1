package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.User;
import com.example.bysj_1.moduls.UserSession;
import com.example.bysj_1.moduls.response.Response;
import com.example.bysj_1.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.bysj_1.service.UserService.userSession;

@Controller
public class LoginController {
    private UserService userService = new UserService();

    /**
     * 登录页面
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public String Login(User user){
        return "/login";
    }

    /**
     * 校验用户
     * @param request
     * @param user
     * @param model
     * @return
     */
    @RequestMapping(value = "/checkUser",method = RequestMethod.POST)
    public String checkUser(HttpServletRequest request,@ModelAttribute("user") User user,Model model){
        if(userService.checkLogin(user)){
            userSession = request.getSession();
            userSession.setAttribute("username",user.getLoginname());
            model.addAttribute("name",userSession.getAttribute("username"));
            model.addAttribute("logInfo",true);
            return this.index(model);
        }else{
            model.addAttribute("error","密码错误");
            return "/login";
        }
    }

    /**
     * 返回首页
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        return "/index";
    }

    /**
     * 注册页面
     * @return
     */
    @RequestMapping(value = "/singup")
    public String singup(){
        return "/singup";
    }

    /**
     * 新增用户
     * @return
     */
    @RequestMapping(value = "/inseruser",method = RequestMethod.POST)
    public Response insertUser(@ModelAttribute("user")User user){
        return userService.insertUser(user);
    }
}
