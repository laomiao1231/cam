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
     * Handler执行完成之后调用这个方法
     */
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception exc)
            throws Exception {

    }

    /**
     * Handler执行之后，ModelAndView返回之前调用这个方法
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    /**
     * Handler执行之前调用这个方法
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        //获取请求的URL
        String url = request.getRequestURI();
        //URL:login是公开的;这个demo是除了login.jsp是可以公开访问的，其它的URL都进行拦截控制
        if(url.indexOf("admin/login")>=0){
            return true;
        }
        //获取Session
        HttpSession session = request.getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if(admin != null){
            return true;
        }
        //不符合条件
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
