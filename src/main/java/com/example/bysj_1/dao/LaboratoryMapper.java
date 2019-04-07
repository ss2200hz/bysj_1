package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.LabAppointment;
import com.example.bysj_1.moduls.response.Laboratory;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
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

    /**
     * 通过实验室编号查询实验室未来的预约信息
     * @param labNo
     * @return
     */
    @Select("select " +
            " appointed_id as id," +
            " laboratory_no as laboratoryNo," +
            " appointed_start_time as appointedStartTime," +
            " appointed_end_time as appointedEndTime" +
            " from laboratory_appointment " +
            " where laboratory_no = #{labNo}" +
            " and appointed_start_time >= now()")
    List<LabAppointment> findLabAppointInfoByLabNo(@Param("labNo")String labNo);

    /**
     * 根据实验室编号查询所有的预约信息
     * @param labNo
     * @return
     */
    @Select("select " +
            " appointed_id as id," +
            " laboratory_no as laboratoryNo," +
            " appointed_start_time as appointedStartTime," +
            " appointed_end_time as appointedEndTime" +
            " from laboratory_appointment " +
            " where laboratory_no = #{labNo}")
    List<LabAppointment> findAllLabAppointInfByLabNo(@Param("labNo")String labNo);

    /**
     * 实验室预约记录表新增数据
     */
    @Insert("insert into" +
            " laboratory_appointment " +
            "(appointed_id," +
            " laboratory_no," +
            " appointed_start_time," +
            " appointed_end_time," +
            " appointed_user )" +
            " values(#{id},#{laboratoryNo},#{appointedStartTime},#{appointedEndTime},#{appointedUser})")
    void appointLab(LabAppointment labAppointment);

}
