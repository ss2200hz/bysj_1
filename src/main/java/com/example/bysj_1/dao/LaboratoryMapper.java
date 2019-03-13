package com.example.bysj_1.dao;

import com.example.bysj_1.moduls.response.Laboratory;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * 实验室相关dao
 */
public interface LaboratoryMapper {
    @Select("")
    List<Laboratory> findAllLaboratory();
}
