package com.example.bysj_1.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

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
    public String getClassNameById(@Param("classNo") String classNo);
}
