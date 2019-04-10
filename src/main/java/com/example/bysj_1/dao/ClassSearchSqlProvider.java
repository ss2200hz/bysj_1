package com.example.bysj_1.dao;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class ClassSearchSqlProvider {
    public String selectWhitParamSql(HashMap param){
        return new SQL(){
            {
                SELECT("count(*) as num,class_no as classNo,class_name as className,class_type as classType ");
                FROM(" class");
                if(param.get("classNo") !=null){
                    WHERE("class_no=#{classNo}");
                }
                if(param.get("classType") !=null){
                    WHERE("class_type=#{classType}");
                }
                if(param.get("className")!=null){
                    WHERE("class_name=#{className}");
                }
                ORDER_BY("class_no");
            }
        }.toString();
    }
}
