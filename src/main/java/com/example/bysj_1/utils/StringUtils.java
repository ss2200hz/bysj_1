package com.example.bysj_1.utils;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
            if (str1 == null && str2 == null) {
                throw new RuntimeException("无法比较两个未定义的字符串!");
            }
            return false;
        } else {
            return str1.equals(str2);
        }
    }

    public static boolean isEmpty(Object str) {
        return str == null || "".equals(str);
    }

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    public static boolean isBlank(Object str) {
        if (isEmpty(str)) {
            return true;
        }
        return ("" + str).replace(" ", "").equals("");
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 根据指定字符将字符串分解为List
     *
     * @param str
     * @param regex
     * @return
     */
    public static List String2List(String str, String regex) {
        List<String> list = new ArrayList();
        if (isNotEmpty(str) && isNotEmpty(regex)) {
            String[] strs = str.split(regex);
            for (String s : strs) {
                list.add(s);
            }
        }
        return list;
    }

    /**
     * List元素转为字符串，用逗号分隔
     * @param list
     * @return
     */
    public static String List2String(List list){
        if(CollectionUtils.isEmpty(list) || list.size()==0){
            return "";
        }
        if(list.size()==1){
            return list.get(0).toString();
        }
        String str = "";
        for(int i=0;i<list.size()-1;i++){
            Object o =list.get(i);
            str+=o.toString()+",";
        }
        str+=list.get(list.size()-1).toString();
        return str;
    }
}
