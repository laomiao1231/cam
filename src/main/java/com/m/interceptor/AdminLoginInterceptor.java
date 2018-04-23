package com.m.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.model.Admin;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class AdminLoginInterceptor implements HandlerInterceptor {
    /**
     * Handlerִ�����֮������������
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handlerִ��֮��ModelAndView����֮ǰ�����������
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handlerִ��֮ǰ�����������
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //��ȡ�����URL
        String url = request.getRequestURI();
        //URL:login�ǹ�����;���demo�ǳ���user-login.jsp�ǿ��Թ������ʵģ�������URL���������ؿ���
        if(url.indexOf("admin/login")>=0){
            return true;
        }
        //��ȡSession
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin != null){
            return true;
        }
        //����������
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> map = new HashMap<>();
            map.put("success","false");
            map.put("msg","xxxx");
            response.getWriter().write(mapper.writeValueAsString(map));
        }
        catch (Exception e){
            e.printStackTrace();
            response.sendError(500);
        }
        return false;
    }
}
