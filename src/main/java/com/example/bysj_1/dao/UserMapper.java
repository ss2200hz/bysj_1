package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.User;
import org.apache.ibatis.annotations.Insert;

public interface UserMapper {
    /**
     * 新增角色
     * @param user
     */
    @Insert("insert into user_" +
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
}
