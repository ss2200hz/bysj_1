package com.example.bysj_1.service;

import com.example.bysj_1.dao.ClassMapper;
import com.example.bysj_1.dao.LaboratoryMapper;
import com.example.bysj_1.moduls.response.LabAppointment;
import com.example.bysj_1.moduls.response.Laboratory;
import com.example.bysj_1.utils.MyBatisUtils;
import com.example.bysj_1.utils.SpringUtils;
import com.example.bysj_1.utils.TimeUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.swing.text.DateFormatter;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
            if (TimeUtils.isTimeInPeriod(startTime,start,end) || TimeUtils.isTimeInPeriod(endTime,start,end)){
                return false;
            }
        }
        return true;
    }
}
