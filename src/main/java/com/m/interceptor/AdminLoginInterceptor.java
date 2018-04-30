package com.m.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.dto.User;
import com.m.model.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AdminLoginInterceptor implements HandlerInterceptor {

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }


    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 拦截访问
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //请求访问的URL
        String url = request.getRequestURI();
        if(url.indexOf("guide/index")>=0){
            return true;
        }
        if(url.indexOf("guide/login")>=0){
            return true;
        }
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user != null){
            return true;
        }
        //拦截跳转
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        request.getRequestDispatcher("/WEB-INF/view/user_login.jsp").forward(request, response);
        return false;
    }
}
