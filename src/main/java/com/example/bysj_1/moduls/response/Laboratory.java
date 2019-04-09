package com.example.bysj_1.moduls.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * 实验室信息
 */
public class Laboratory {
    /**
     * 实验室编号
     */
    private String laboratoryNo;
    /**
     * 实验室名称
     */
    private String laboratoryName;
    /**
     * 实验室类型
     */
    private String laboratoryType;
    /**
     * 实验室可容纳人数
     */
    private int personNum;
    /**
     * 维护时间
     */
    @DateTimeFormat(pattern = "yyyy-mm-dd hh24:mm:ss")
    private LocalDate lastMaintenanceDate;
    /**
     * 使用次数
     */
    private int useTimes;
    /**
     * 实验室状态
     */
    private String laboratoryState;

    public String getLaboratoryNo() {
        return laboratoryNo;
    }

    public void setLaboratoryNo(String laboratoryNo) {
        this.laboratoryNo = laboratoryNo;
    }

    public String getLaboratoryName() {
        return laboratoryName;
    }

    public void setLaboratoryName(String laboratoryName) {
        this.laboratoryName = laboratoryName;
    }

    public String getLaboratoryType() {
        return laboratoryType;
    }

    public void setLaboratoryType(String laboratoryType) {
        this.laboratoryType = laboratoryType;
    }

    public int getPersonNum() {
        return personNum;
    }

    public void setPersonNum(int personNum) {
        this.personNum = personNum;
    }

    public LocalDate getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    public void setLastMaintenanceDate(LocalDate lastMaintenanceDate) {
        this.lastMaintenanceDate = lastMaintenanceDate;
    }

    public int getUseTimes() {
        return useTimes;
    }

    public void setUseTimes(int useTimes) {
        this.useTimes = useTimes;
    }

    public String getLaboratoryState() {
        return laboratoryState;
    }

    public void setLaboratoryState(String laboratoryState) {
        this.laboratoryState = laboratoryState;
    }
}
