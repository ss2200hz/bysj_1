package com.example.bysj_1.utils;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtils {
    private String date,formatter;

    /**
     * 比较一个时间是否在时间段中
     *
     * @param time
     * @param start
     * @param end
     * @return
     */
    public static boolean isTimeInPeriod(@NotNull LocalDateTime time, LocalDateTime start, LocalDateTime end) {
        if ((time.isAfter(start) || time.isEqual(start)) && (time.isBefore(end) || time.isEqual(end))) {
            return true;
        }
        return false;
    }

    /**
     * 比较两个时间先后
     * @param time1
     * @param time2
     * @return 0:相同 1:time1>time2 -1:time1<time2
     */
    public static int compare2Times(LocalDateTime time1,LocalDateTime time2){
        if(time1==null||time2==null){
            throw new RuntimeException("时间不能为空");
        }
        if(time1.isEqual(time2)){
            return 0;
        }else if(time1.isAfter(time2)){
            return 1;
        }else{
            return -1;
        }
    }

    /**
     * String转LocalDate,自定义格式
     * @param date
     * @param dateFormate
     * @return
     */
    public static LocalDate String2Date(String date,String dateFormate){
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormate);
            return LocalDate.parse(date,formatter);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * String转LocalDate,默认格式
     * @param date
     * @return
     */
    public static LocalDate String2Date(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date,formatter);
    }
}
