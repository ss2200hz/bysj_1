package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.LabAppointment;
import com.example.bysj_1.moduls.response.Laboratory;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
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
     *
     * @param min   开始记录数
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

    @Select("select " +
            " laboratory_no as laboratoryNo," +
            " laboratory_name as laboratoryName," +
            " laboratory_type as laboratoryType," +
            " person_num as personNum," +
            " last_maintenance_date as lastMaintenanceDate," +
            " use_times as useTimes," +
            " if(ifnull(laboratory_state,'Y')='','Y',laboratory_state) as laboratoryState " +
            " from laboratory" +
            " where laboratory_no=#{labNo}")
    Laboratory getLaboratory(@Param("labNo") String labNo);

    /**
     * 根据编号删除实验室
     *
     * @param no
     */
    @Delete("delete " +
            " from laboratory" +
            " where laboratory_no=#{labNo}")
    void deleteLaboratory(@Param("labNo") String no);

    /**
     * 更新实验室信息
     *
     * @param laboratory
     * @param id
     */
    @Update("update " +
            " laboratory" +
            " set laboratory_no = #{lab.laboratoryNo}," +
            " laboratory_name = #{lab.laboratoryName}," +
            " laboratory_type = #{lab.laboratoryType}," +
            " person_num = #{lab.personNum}," +
            " last_maintenance_date = #{lab.lastMaintenanceDate}," +
            " use_times = #{lab.useTimes}," +
            " laboratory_state = #{lab.laboratoryState}" +
            " where laboratory_no = #{id}")
    void insertLaboratory(@Param("lab") Laboratory laboratory, @Param("id") String id);

    /**
     * 通过实验室编号查询实验室未来的预约信息
     *
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
    List<LabAppointment> findLabAppointInfoByLabNo(@Param("labNo") String labNo);

    /**
     * 根据实验室编号查询所有的预约信息
     *
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
    List<LabAppointment> findAllLabAppointInfByLabNo(@Param("labNo") String labNo);

    /**
     * 实验室预约记录表新增数据
     */
    @Insert("insert into" +
            " laboratory_appointment " +
            "(appointed_id," +
            " laboratory_no," +
            " appointed_start_time," +
            " appointed_end_time," +
            " appointed_user," +
            " appointed_state )" +
            " values(#{id},#{laboratoryNo},#{appointedStartTime},#{appointedEndTime},#{appointedUser},'0')")
    void appointLab(LabAppointment labAppointment);

    /**
     * 获得实验室预约记录条数
     */
    @Select("select " +
            " count(*) from laboratory_appointment" +
            " where laboratory_no = #{labNo}")
    int getLabAppNum(@Param("labNo") String labNo);

    /**
     * 通过实验室编号获得实验室预约记录
     */
    @Select("select " +
            " appointed_id as id," +
            " appointed_start_time as appointedStartTime," +
            " appointed_end_time as appointedEndTime," +
            " name as appointedUser," +
            " appointed_state as appointedState" +
            " from laboratory_appointment,teacher" +
            " where laboratory_no = #{labNo}" +
            " and appointed_user = idcard")
    List<HashMap<String, Object>> getLabAppointHis(@Param("labNo") String labNo, @Param("min") int min, @Param("total") int total);

    /**
     * 删除实验室预约记录
     * @param id
     */
    @Delete("delete from" +
            " laboratory_appointment" +
            " where appointed_id = #{id}")
    void deleteLabAppointHis(@Param("id")String id);

    /**
     * 修改状态
     */
    @Update("update" +
            " laboratory_appointment" +
            " set appointed_state = #{state}" +
            " where appointed_id = #{id}")
    void changeLabAppointState(@Param("id")String id,@Param("state")String state);
}
