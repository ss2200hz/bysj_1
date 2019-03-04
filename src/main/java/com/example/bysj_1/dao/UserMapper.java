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
            "(userid," +
            " loginname," +
            " password," +
            " name," +
            " roleid," +
            " email," +
            " phone)" +
            " values (" +
            " #{userid}," +
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
            " userid as userid," +
            " loginname as loginname," +
            " password as password ," +
            " name as name" +
            " from user_ " +
            " where loginname=#{loginname} ")
    List<User> findUserById(@Param("loginname")String userName);
}
