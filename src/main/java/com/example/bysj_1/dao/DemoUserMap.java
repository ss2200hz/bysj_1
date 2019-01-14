package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DemoUserMap {
    @Select("select name as name from user")
    List<User> getUsers();
}
