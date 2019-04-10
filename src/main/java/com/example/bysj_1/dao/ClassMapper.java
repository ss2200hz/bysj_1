package com.example.bysj_1.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.time.LocalTime;
import java.util.Map;

/**
 * 课程dao
 */
@Repository
public interface ClassMapper {
    /**
     * 根据课程id查找课程名称
     * @param classNo
     * @return
     */
    @Select(" select" +
            " class_name as className" +
            " from class" +
            " where class_no = #{classNo}")
    String getClassNameById(@Param("classNo") String classNo);

    /**
     * 课程时间表
     */
    @Select("select" +
            " class_1_start_time," +
            " class_1_end_time," +
            " class_2_start_time," +
            " class_2_end_time," +
            " class_3_start_time," +
            " class_3_end_time," +
            " class_4_start_time," +
            " class_4_end_time," +
            " class_5_start_time," +
            " class_5_end_time," +
            " class_6_start_time," +
            " class_6_end_time," +
            " class_7_start_time," +
            " class_7_end_time," +
            " class_8_start_time," +
            " class_8_end_time," +
            " class_9_start_time," +
            " class_9_end_time," +
            " class_10_start_time," +
            " class_10_end_time," +
            " class_11_start_time," +
            " class_11_end_time " +
            " from class_times " +
            " where school_no = #{schoolNo}")
    Map<String, Time> getClasstime(@Param("schoolNo")String schoolNo);
}
