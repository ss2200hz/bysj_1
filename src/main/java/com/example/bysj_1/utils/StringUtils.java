package com.example.bysj_1.utils;

public class StringUtils {
    public static boolean equals(String str1, String str2) {
        if (str1 == null || str2 == null) {
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

    public static boolean isNotBland(String str) {
        return !isBlank(str);
    }
}
