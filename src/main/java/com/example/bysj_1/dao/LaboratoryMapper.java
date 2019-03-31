package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.Laboratory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 实验室相关dao
 */
public interface LaboratoryMapper {
    @Select("select " +
            " laboratory_no as laboratoryNo," +
            " laboratory_name as laboratoryName," +
            " laboratory_type as laboratoryType," +
            " person_num as personNum," +
            " last_maintenance_date as lastMaintenanceDate," +
            " use_times as useTimes " +
            " from laboratory")
    List<Laboratory> findLaboratory(@Param("max") int max, @Param("min") int min);

    /**
     * 获取实验室数量
     */
    @Select("")
    int getLabNum();
}
