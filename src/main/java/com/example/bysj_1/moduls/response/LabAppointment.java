package com.example.bysj_1.moduls.response;

import java.time.LocalDateTime;

/**
 * 实验室预约记录数据体
 */
public class LabAppointment {
    /**
     * 主键id
     */
    private String id;
    /**
     * 实验室编号
     */
    private String laboratoryNo;
    /**
     * 预约开始时间
     */
    private LocalDateTime appointedStartTime;
    /**
     * 预约结束时间
     */
    private LocalDateTime appointedEndTime;
    /**
     * 预约人
     */
    private String appointedUser;
    /**
     * 状态
     */
    private String appointedState;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLaboratoryNo() {
        return laboratoryNo;
    }

    public void setLaboratoryNo(String laboratoryNo) {
        this.laboratoryNo = laboratoryNo;
    }

    public LocalDateTime getAppointedStartTime() {
        return appointedStartTime;
    }

    public void setAppointedStartTime(LocalDateTime appointedStartTime) {
        this.appointedStartTime = appointedStartTime;
    }

    public LocalDateTime getAppointedEndTime() {
        return appointedEndTime;
    }

    public void setAppointedEndTime(LocalDateTime appointedEndTime) {
        this.appointedEndTime = appointedEndTime;
    }

    public String getAppointedUser() {
        return appointedUser;
    }

    public void setAppointedUser(String appointedUser) {
        this.appointedUser = appointedUser;
    }

    public String getAppointedState() {
        return appointedState;
    }

    public void setAppointedState(String appointedState) {
        this.appointedState = appointedState;
    }
}
