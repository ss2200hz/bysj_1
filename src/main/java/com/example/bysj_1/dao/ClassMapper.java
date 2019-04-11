package com.example.bysj_1.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 课程dao
 */
@Repository
public interface ClassMapper {
    /**
     * 根据课程id查找课程名称
     */
    @Select(" select" +
            " class_name as className" +
            " from class" +
            " where class_no = #{classNo}")
    String getClassNameById(@Param("classNo") String classNo);

    /**
     * 根据课程id查找课程类型
     */
    @Select(" select" +
            " lab_type as labType" +
            " from class" +
            " where class_no = #{classNo}")
    String getClassTypeById(@Param("classNo") String classNo);

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

    @Select("select " +
            " count(*) " +
            " from class")
    int getClassNum();

    /**
     * 查询所有课程
     */
    @Select("select " +
            " class_no as classNo," +
            " class_name as className," +
            " class_type as classType" +
            " from class " +
            " order by class_no" +
            " limit #{min},#{total}")
    List<HashMap<String,Object>> getClassList(@Param("min") int min, @Param("total") int total);

    @SelectProvider(type = ClassSearchSqlProvider.class,method = "selectWhitParamSql")
    List<HashMap<String,Object>> getClassListByCondition(HashMap param);

    /**
     * 查找某课程的所有任课教师
     */
    @Select("select " +
            " b.idcard as teacherId," +
            " b.name as name" +
            " from class_teacher as a,teacher as b" +
            " where class_id = #{classNo}" +
            " and a.user_id = b.idcard")
    List<HashMap<String,String>> getTeacherByClassNo(@Param("classNo")String classNo);

    /**
     * 删除课程
     */
    @Delete("delete from class" +
            " where class_no=#{classNo}")
    void deleteClassByClassNo(@Param("classNo")String classNo);

    /**
     * 加入课程
     */
    @Insert("insert into class_teacher" +
            " (id,class_id,user_id)" +
            " values(#{id},#{classNo},#{userid})")
    void joinClass(@Param("id")String id,@Param("classNo")String classNo,@Param("userid")String userid);

    /**
     * 退出课程
     */
    @Delete("delete from class_teacher" +
            " where class_id=#{classNo}" +
            " and user_id=#{userid}")
    void exitClass(@Param("classNo")String classNo,@Param("userid")String userid);

    /**
     * 查询某教师的所有课程
     */
    @Select("select" +
            " a.class_id as classNo," +
            " b.class_name as className" +
            " from class_teacher as a,class as b" +
            " where a.class_id = b.class_no" +
            " and a.user_id=#{userid}")
    List<HashMap<String,String>> getClassListByTeacher(@Param("userid")String id);
}
