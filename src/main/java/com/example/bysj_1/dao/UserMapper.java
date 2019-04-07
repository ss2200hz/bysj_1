package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    /**
     * 新增角色
     */
    @Insert("insert into user_ " +
            "(loginname," +
            " password," +
            " name," +
            " roleid," +
            " email," +
            " phone)" +
            " values (" +
            " #{loginname}," +
            " #{password}," +
            " #{name}," +
            " #{roleid}," +
            " #{email}," +
            " #{phone})")
    void addUser(User user);

    /**
     * 查询用户
     */
    @Select("select " +
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
            " idcard" +
            " from teacher" +
            " where idcard=#{loginname}")
    List<String> isUserInTeacher(User user);

    /**
     * 从教师表中根据id查找用户信息
     * @param userId
     * @return
     */
    @Select(" select " +
            " a.idcard as loginname," +
            " a.name as name," +
            " b.roleid as roleid," +
            " a.class_no as classNo," +
            " a.phone as phone," +
            " a.email as email," +
            " a.induct_date as signupDate" +
            " from teacher as a,user_ as b" +
            " where idcard = #{idcard}" +
            " and a.idcard = b.loginname")
    User getUserInfoById(@Param("idcard") String userId);
}
