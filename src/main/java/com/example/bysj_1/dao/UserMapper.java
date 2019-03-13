package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.User;
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
            " name as name" +
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
}
