package com.example.bysj_1.adpters;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器,判断当前是否有用户登录
 */
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    /**
     * 在请求处理之前调用,只有返回true才会执行请求
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        HttpSession session = request.getSession();
        Object admin = session.getAttribute("user");
        if (admin != null) {
            return true;
        } else {
            //如果未登录则返回登录页面
            response.sendRedirect("/login");
            return false;
        }
    }
}
