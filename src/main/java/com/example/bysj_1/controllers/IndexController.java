package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.UserService;
import com.example.bysj_1.utils.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
public class IndexController {
    private UserService userService = new UserService();

    /**
     * 我的信息页面
     *
     * @return
     */
    @RequestMapping("/myInfo")
    public String userinfo() {
        return "/myInfo/myInfo";
    }

    /**
     * 获取我的信息
     *
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfo", method = RequestMethod.GET)
    public HashMap<String, Object> getUserInfo(HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap();
        try {
            User user = (User) request.getSession().getAttribute("user");
            String userId = user.getLoginname();
            map = userService.getUserInfo(userId);
        } catch (ClassCastException e) {
            map.put("error", true);
            map.put("errorInfo", "cast error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取我的信息
     */
    @ResponseBody
    @RequestMapping(value = "/getUserInfoById", method = RequestMethod.GET)
    public HashMap<String, Object> getUserInfo(@Param("userid") String userid) {
        return userService.getUserInfo(userid);
    }

    /**
     * 修改我的信息
     */
    @ResponseBody
    @RequestMapping(value = "/editUserInfo", method = RequestMethod.PUT)
    public HashMap changeUserInfo(HttpServletRequest request, @RequestBody HashMap data) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return null;
        }
        data.put("id", user.getLoginname());
        return userService.changeUserInfo(data);
    }

    /**
     * 跳转用户信息页面
     */
    @RequestMapping("/userInfo")
    public String toUserInfoPage(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        model.addAttribute("roleid", user.getRoleid());
        return "/userinfo/userinfo";
    }

    /**
     * 获取用户列表页数
     */
    @ResponseBody
    @RequestMapping(value = "/getUserListNum", method = RequestMethod.GET)
    public HashMap getUerListNum(int pageSize) {
        return userService.getUerListNum(pageSize);
    }

    /**
     * 获取用户列表
     */
    @ResponseBody
    @RequestMapping(value = "getUserList", method = RequestMethod.GET)
    public HashMap getUerList(int pageNo, int pageSize) {
        return userService.getUerList(pageNo, pageSize);
    }

    /**
     * 跳转用户信息编辑页面
     */
    @RequestMapping(value = "/userInfoEdit",method = RequestMethod.GET)
    public String toUserInfoEditPage(@RequestParam(value = "userid", required = false) String userid, Model model) {
        if (StringUtils.isNotEmpty(userid)) {
            model.addAttribute("userid", userid);
        }
        return "/userinfo/userinfo_edit";
    }

    /**
     * 删除用户
     */
    @ResponseBody
    @RequestMapping(value = "/editUser",method = RequestMethod.DELETE)
    public HashMap deleteUser(@RequestBody HashMap data){
        return userService.deleteUser(data);
    }

    /**
     * 新增用户
     */
    @ResponseBody
    @RequestMapping(value = "/editUser",method = RequestMethod.POST)
    public HashMap addUser(@RequestBody HashMap data){
        return userService.addUser(data);
    }

    /**
     * 修改用户
     */
    @ResponseBody
    @RequestMapping(value = "/editUser",method = RequestMethod.PUT)
    public HashMap edituser(@RequestBody HashMap data){
        return userService.edituser(data);
    }
}
