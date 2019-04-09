package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
import com.example.bysj_1.dao.LaboratoryMapper;
import com.example.bysj_1.moduls.response.LabAppointment;
import com.example.bysj_1.moduls.response.Laboratory;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.SpringUtils;
import com.example.bysj_1.utils.TimeUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 预约服务类
 */
@Service
public class LaboratoryService {
    private SqlSession sqlSession = MyBatisUtils.getSession(true);
    private LaboratoryMapper laboratoryMapper = sqlSession.getMapper(LaboratoryMapper.class);
    private ClassMapper classMapper = sqlSession.getMapper(ClassMapper.class);

    /**
     * 获取总分页数
     *
     * @return
     */
    public int getLabNum(int pageSize) {
        int total = laboratoryMapper.getLabNum();//总记录数
        int pageNum;//总页数
        if (total % pageSize == 0) {
            pageNum = total / pageSize;
        } else {
            pageNum = total / pageSize + 1;
        }
        return pageNum;
    }

    /**
     * 获得实验室列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Laboratory> getLabInfo(int pageNo, int pageSize) {
        int min = 1;
        if (pageNo > 0) {
            min = (pageNo - 1) * pageSize;
        }
        List<Laboratory> laboratoryList = laboratoryMapper.findLaboratory(min, pageSize);
        return laboratoryList;
    }

    /**
     * 预约实验室
     *
     * @param data
     * @return
     */
    public HashMap appointed(HashMap data) {
        HashMap result = new HashMap();
        try {
            String labNo = (String) data.get("laboratoryNo");
            String start = (String) data.get("startTime");
            String end = (String) data.get("endTime");
            String schoolNo = SpringUtils.getInfoByProp("school-cfg.propties", "school_no");
            Map<String, Time> timesMap = classMapper.getClasstime(schoolNo);
            LocalDate appointDate = TimeUtils.String2Date((String) data.get("appointDate"));
            LocalDateTime startDateTime = LocalDateTime.of(appointDate, timesMap.get(start + "_start_time").toLocalTime());
            LocalDateTime endDateTime = LocalDateTime.of(appointDate, timesMap.get(end + "_end_time").toLocalTime());
            if (this.isLabBeAppointed(labNo, startDateTime, endDateTime)) {
                LabAppointment labAppointment = new LabAppointment();
                labAppointment.setId(UUID.randomUUID().toString());
                labAppointment.setLaboratoryNo(labNo);
                labAppointment.setAppointedUser((String) data.get("userid"));
                labAppointment.setAppointedStartTime(startDateTime);
                labAppointment.setAppointedEndTime(endDateTime);
                laboratoryMapper.appointLab(labAppointment);
                result.put("result", true);
            } else {
                result.put("result", false);
                result.put("errorInfo", "该实验室在当前时间已被预约");
            }
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 实验室是否可预约
     *
     * @param labNo
     * @return
     */
    public boolean isLabBeAppointed(String labNo, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            throw new RuntimeException("请指定非空的时间段！");
        }
        List<LabAppointment> list = laboratoryMapper.findLabAppointInfoByLabNo(labNo);
        if (CollectionUtils.isEmpty(list) || list.size() == 0) {
            return true;
        }
        for (LabAppointment labAppointment : list) {
            LocalDateTime startTime = labAppointment.getAppointedStartTime();
            LocalDateTime endTime = labAppointment.getAppointedEndTime();
            if (TimeUtils.isTimeInPeriod(startTime, start, end) || TimeUtils.isTimeInPeriod(endTime, start, end)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 删除实验室
     *
     * @param labNo
     * @return
     */
    public HashMap deleteLab(String labNo) {
        HashMap map = new HashMap();
        try {
            laboratoryMapper.deleteLaboratory(labNo);
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据id查找实验室
     *
     * @param labNo
     * @return
     */
    public Laboratory getLabInfoById(String labNo) {
        return laboratoryMapper.getLaboratory(labNo);
    }

    /**
     * 保存实验室信息
     *
     * @param data
     * @return
     */
    public HashMap saveLabInfo(HashMap data) {
        HashMap map = new HashMap();
        try {
            Laboratory laboratory = new Laboratory();
            laboratory.setLaboratoryNo((String) data.get("laboratoryNo"));
            laboratory.setLaboratoryName((String) data.get("laboratoryName"));
            laboratory.setLaboratoryType((String) data.get("laboratoryType"));
            laboratory.setLastMaintenanceDate(TimeUtils.String2Date((String) data.get("lastMaintenanceDate")));
            laboratory.setPersonNum((Integer.parseInt((String) data.get("personNum"))));
            laboratory.setUseTimes((Integer.parseInt((String) data.get("useTimes"))));
            laboratory.setLaboratoryState((String) data.get("isCanUse"));
            laboratoryMapper.insertLaboratory(laboratory, (String) data.get("oldLabNo"));
            map.put("succeed", true);
        } catch (Exception e) {
            map.put("succeed", false);
            map.put("errorInfo", "unknow error");
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获得实验室预约记录条数
     *
     * @return
     */
    public HashMap getLabAppNum(String labNo, int pageSize) {
        HashMap map = new HashMap();
        int total = laboratoryMapper.getLabAppNum(labNo);//总记录数
        int pageNum;//总页数
        if (total % pageSize == 0) {
            pageNum = total / pageSize;
        } else {
            pageNum = total / pageSize + 1;
        }
        map.put("pageNum", pageNum);
        return map;
    }

    /**
     * 通过实验室编号获得实验室预约记录
     */
    public HashMap getLabAppointHisByLabNo(String labNo, int pageNo, int pageSize) {
        HashMap map = new HashMap();
        int min = 1;
        if (pageNo > 0) {
            min = (pageNo - 1) * pageSize;
        }
        //预约基本信息列表
        List<HashMap<String, Object>> labAppointHisList = laboratoryMapper.getLabAppointHis(labNo, min, pageSize);
        for (HashMap hashMap : labAppointHisList) {
            LocalDateTime start = TimeUtils.String2DateTime(hashMap.get("appointedStartTime").toString());
            LocalDateTime end = TimeUtils.String2DateTime(hashMap.get("appointedEndTime").toString());
            if (start != null && end != null) {
                hashMap.put("appointedStartTime", start.toString().replace("T", " "));
                hashMap.put("appointedEndTime", end.toString().replace("T", " "));
            }
        }
        map.put("resultList", labAppointHisList);
        return map;
    }
}
