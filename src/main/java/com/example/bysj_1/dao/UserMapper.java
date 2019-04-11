package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    /**
     * 新增角色
     */
    @Insert("insert into user_" +
            " (loginname," +
            " password," +
            " name," +
            " roleid," +
            " signup_date)" +
            " values (#{userid},#{password},#{name},#{roleid},#{signupDate})")
    void addUser(User user);

    /**
     * 查询用户
     */
    @Select("select " +
            " loginname as userid," +
            " loginname as loginname," +
            " password as password ," +
            " name as name," +
            " roleid as roleid" +
            " from user_ " +
            " where loginname=#{loginname} ")
    List<User> findUserById(@Param("loginname")String userName);

    /**
     * 是否为本校教师
     */
    @Select(" select" +
            " idcard as loginname," +
            " name as name," +
            " phone as phone," +
            " email as email " +
            " from teacher" +
            " where idcard=#{loginname}")
    List<User> isUserInTeacher(@Param("loginname") String userid);

    /**
     * 从教师表中根据id查找用户信息
     * @param userId
     * @return
     */
    @Select(" select " +
            " a.idcard as loginname," +
            " b.password as password," +
            " a.name as name," +
            " b.roleid as roleid," +
            " a.phone as phone," +
            " a.email as email," +
            " a.induct_date as signupDate" +
            " from teacher as a,user_ as b " +
            " where idcard = #{idcard}" +
            " and a.idcard = b.loginname")
    User getUserInfoById(@Param("idcard") String userId);

    /**
     * 查询教师任课信息
     */
    @Select("select" +
            " a.class_name as className" +
            " from class as a,class_teacher as b,user_ as c" +
            " where a.class_no = b.class_id" +
            " and b.user_id = c.loginname" +
            " and c.loginname = #{idcard}")
    List<String> getTeacherClass(@Param("idcard")String userId);

    /**
     * 更新教师表信息
     */
    @Update("update teacher" +
            " set " +
            " phone = #{phone}," +
            " email = #{email}" +
            " where idcard = #{id}")
    void updateTeacherInfo(@Param("id") String id,@Param("phone")String phone,@Param("email")String email);

    /**
     * 获取用户列表页数
     */
    @Select("select" +
            " count(*) from user_")
    int getUserListNum();

    /**
     * 获取用户列表
     */
    @Select("select " +
            " loginname as id," +
            " name as name," +
            " roleid as roleid," +
            " signup_date as signupDate" +
            " from user_" +
            " order by loginname" +
            " limit #{min},#{total}")
    List<HashMap<String,Object>> getUserList(@Param("min") int min, @Param("total") int total);

    /**
     * 删除用户
     */
    @Delete("delete from user_" +
            " where loginname = #{id}")
    void deleteUser(@Param("id")String id);

    /**
     * 新增用户
     */
    @Insert("insert into teacher" +
            "(idcard," +
            " name," +
            " phone," +
            " email," +
            " induct_date)" +
            " values (#{data.id},#{data.name},#{data.phone},#{data.email},#{data.inductDate})")
    void addUserByData(@Param("data") HashMap data);

    /**
     * 更新用户信息
     */
    @Update("update user_" +
            " set" +
            " name = #{data.name}," +
            " roleid = #{data.roleid}" +
            " where loginname = #{data.id}")
    void updateUser(@Param("data")HashMap data);

    /**
     * 更新教师表信息
     */
    @Update("update teacher" +
            " set " +
            " phone = #{data.phone}," +
            " email = #{data.email}," +
            " induct_date = #{data.inductDate}" +
            " where idcard = #{data.id}")
    void updateTeacherInfoByData(@Param("data")HashMap data);
}
