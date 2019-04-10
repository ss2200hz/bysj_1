package com.example.bysj_1.controllers;

import com.example.bysj_1.moduls.response.Laboratory;
import com.example.bysj_1.moduls.response.User;
import com.example.bysj_1.service.LaboratoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = "labList", method = RequestMethod.GET)
    public HashMap getLabInfo(int pageNo, int pageSize) {
        HashMap result = new HashMap();
        List infoList = laboratoryService.getLabInfo(pageNo, pageSize);
        result.put("resultList", infoList);
        return result;
    }

    /**
     * 根据id查询实验室详细信息
     */
    @ResponseBody
    @RequestMapping(value = "getLabInfoById", method = RequestMethod.GET)
    public Laboratory getLabInfoById(@RequestParam(value = "laboratoryNo") String labNo) {
        return laboratoryService.getLabInfoById(labNo);
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
    @RequestMapping(value = "/doAppointed", method = RequestMethod.POST)
    public HashMap doAppointed(HttpServletRequest request, @RequestBody HashMap data) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        data.put("userid", user.getLoginname());
        return laboratoryService.appointed(data);
    }

    /**
     * 打开实验室编辑页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String openEdit(@RequestParam(value = "laboratoryNo") String labNo, Model model) {
        model.addAttribute("laboratoryNo", labNo);
        return "/laboratory/laboratory_edit";
    }

    /**
     * 删除
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.DELETE)
    public HashMap deleteLab(@RequestParam(value = "laboratoryNo") String labNo) {
        return laboratoryService.deleteLab(labNo);
    }

    /**
     * 保存实验室信息
     *
     * @param data
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public HashMap saveLab(@RequestBody HashMap data) {
        return laboratoryService.saveLabInfo(data);
    }

    /**
     * 获取实验室预约记录条数
     *
     * @param labNo
     * @param pageSize
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getLabAppointNum", method = RequestMethod.GET)
    public HashMap getLabAppNum(@RequestParam("laboratoryNo") String labNo, int pageSize) {
        return laboratoryService.getLabAppNum(labNo, pageSize);
    }

    /**
     * 通过实验室编号获得实验室预约记录
     *
     * @param pageNo
     * @param pageSize
     */
    @ResponseBody
    @RequestMapping(value = "/labAppointHistory", method = RequestMethod.GET)
    public HashMap getLabAppointHisByLabNo(@RequestParam("laboratoryNo") String labNo, int pageNo, int pageSize) {
        return laboratoryService.getLabAppointHisByLabNo(labNo, pageNo, pageSize);
    }

    /**
     * 删除实验室预约记录
     */
    @ResponseBody
    @RequestMapping(value = "/labAppointHistory",method = RequestMethod.DELETE)
    public HashMap deleteLabAppointHisByLabNos(@RequestBody HashMap<String,List<String>> data){
        List dataList = data.get("dataList");
        if(CollectionUtils.isEmpty(dataList)){
            HashMap map = new HashMap();
            map.put("succeed",false);
            map.put("errorInfo","empty data");
            return map;
        }
        return laboratoryService.deleteLabAppointHisByLabNos(dataList);
    }

    /**
     * 退回预约
     */
    @ResponseBody
    @RequestMapping(value = "/labAppointHistory",method = RequestMethod.PUT)
    public HashMap backLabAppoint(@RequestBody HashMap<String,List<String>> data){
        List dataList = data.get("dataList");
        if(CollectionUtils.isEmpty(dataList)){
            HashMap map = new HashMap();
            map.put("succeed",false);
            map.put("errorInfo","empty data");
            return map;
        }
        return laboratoryService.backLabAppointment(dataList);
    }

    /**
     * 预约设为已完成
     */
    @ResponseBody
    @RequestMapping(value = "/setoverlabAppointment",method = RequestMethod.PUT)
    public HashMap setoverLabAppointment(@RequestBody HashMap<String,List<String>> data){
        List dataList = data.get("dataList");
        if(CollectionUtils.isEmpty(dataList)){
            HashMap map = new HashMap();
            map.put("succeed",false);
            map.put("errorInfo","empty data");
            return map;
        }
        return laboratoryService.setoverLabAppointment(dataList);
    }

    /**
     * 预约设为未完成
     */
    @ResponseBody
    @RequestMapping(value = "/setnotoverlabAppointment",method = RequestMethod.PUT)
    public HashMap setNotOverLabAppointment(@RequestBody HashMap<String,List<String>> data){
        List dataList = data.get("dataList");
        if(CollectionUtils.isEmpty(dataList)){
            HashMap map = new HashMap();
            map.put("succeed",false);
            map.put("errorInfo","empty data");
            return map;
        }
        return laboratoryService.setnotoverlabAppointment(dataList);
    }
}
