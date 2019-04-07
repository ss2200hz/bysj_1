package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

/**
 * 预约控制器类
 */
@Controller
@RequestMapping("/laboratory")
public class LaboratoryController {
    private LaboratoryService laboratoryService = new LaboratoryService();

    @RequestMapping("/labInfo")
    public String laboratory() {
        return "laboratory/laboratory";
    }

    /**
     * 获取总页数
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getLabNum", method = RequestMethod.GET)
    public HashMap getLabNum(int pageSize) {
        HashMap result = new HashMap();
        result.put("pageNum", laboratoryService.getLabNum(pageSize));
        return result;
    }

    /**
     * 查询实验室信息
     */
    @ResponseBody
    @RequestMapping(value = "getLabInfo", method = RequestMethod.GET)
    public HashMap getLabInfo(int pageNo, int pageSize) {
        HashMap result = new HashMap();
        List infoList = laboratoryService.getLabInfo(pageNo, pageSize);
        result.put("resultList", infoList);
        return result;
    }

    /**
     * 打开预约页面
     *
     * @param labNo
     * @param model
     * @return
     */
    @RequestMapping("/appointment")
    public String toAppointment(@RequestParam(value = "laboratoryNo", required = false) String labNo, Model model) {
        model.addAttribute("laboratoryNo", labNo);
        return "/laboratory/appointment";
    }

    /**
     * 预约操作
     */
    @ResponseBody
    @RequestMapping(value = "/doAppointed",method = RequestMethod.POST)
    public HashMap doAppointed(HttpServletRequest request, @RequestBody HashMap data) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        data.put("userid",user.getLoginname());
        return laboratoryService.appointed(data);
    }
}
