package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.Laboratory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 实验室相关dao
 */
public interface LaboratoryMapper {

    /**
     * 获取实验室数量
     */
    @Select("select" +
            " count(*) from laboratory")
    int getLabNum();

    /**
     * 实验室列表
     * @param min 开始记录数
     * @param total 记录总数
     * @return
     */
    @Select("select " +
            " laboratory_no as laboratoryNo," +
            " laboratory_name as laboratoryName," +
            " laboratory_type as laboratoryType," +
            " person_num as personNum," +
            " last_maintenance_date as lastMaintenanceDate," +
            " use_times as useTimes " +
            " from laboratory" +
            " order by 'laboratory_no'" +
            " limit #{min},#{total} ")
    List<Laboratory> findLaboratory(@Param("min") int min, @Param("total") int total);

}
