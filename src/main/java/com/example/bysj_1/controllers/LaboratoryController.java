package com.example.bysj_1.controllers;

import com.example.bysj_1.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String laboratory(){
        return "laboratory/laboratory";
    }

    /**
     * 查询实验室信息
     */
    @ResponseBody
    @RequestMapping(value = "getLabInfo",method = RequestMethod.GET)
    public HashMap getLabInfo(int pageNo,int pageSize){
        HashMap result = new HashMap();
        List infoList = laboratoryService.getLabInfo(pageNo,pageSize);
        result.put("resultList",infoList);
        System.out.println(result);
        return result;
    }

    public HashMap getLabNum(){
        return laboratoryService.getLabNum();
    }

}
