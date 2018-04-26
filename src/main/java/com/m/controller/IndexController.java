package com.m.controller;

import com.m.dto.User;
import com.m.service.AdminService;
import com.m.service.StaffService;
import com.m.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/guide")
public class IndexController {

    protected static final String ID_STUDENT = "student";
    protected static final String ID_STAFF = "staff";
    protected static final String ID_ADMIN = "admin";

    @Autowired
    private AdminService adminService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private StudentService studentService;

    /**
     * 转到用户登录界面
     * */
    @RequestMapping("/index")
    public String toLogin() {
        return "user_login";
    }

    /**
     * 用户登录
     * */
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> login(User user) {
        System.out.println("ssssssssssssss");
        Map<String,Object> resultMap = new HashMap<>();
        if(user.getIdentity().equals(ID_ADMIN)) {
            try {
                this.adminService.getAdminByAccount(user);
                resultMap.put("status", "00");
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status", "01");
                resultMap.put("errorMessage", e.getMessage());
            }
        }else if(user.getIdentity().equals(ID_STAFF)) {
            try {
                this.staffService.getStaffByAccount(user);
                resultMap.put("status", "00");
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status", "01");
                resultMap.put("errorMessage", e.getMessage());
            }
        }else if(user.getIdentity().equals(ID_STUDENT)) {
            try {
                this.studentService.getStudentByAccount(user);
                resultMap.put("status", "00");
            } catch (Exception e) {
                e.printStackTrace();
                resultMap.put("status", "01");
                resultMap.put("errorMessage", e.getMessage());
            }
        }else {
            resultMap.put("status", "01");
            resultMap.put("errorMessage", "请为账户选择合适的身份");
        }
        return resultMap;
    }

    /**
     * 跳转登录成功页面
     * */
    @RequestMapping("/loggedIn")
    public String toLoginSuccess() {
        return "login_success";
    }

    /**
     * 跳转公告信息列表
     * */
    @RequestMapping("/toNewsList")
    public String toNewsList() {
        return "news/news_list";
    }

    /**
     * 跳转来访人员管理界面
     * */
    @RequestMapping("/toVisitorManage")
    public String toVisitorList() {
        return "visitor/visitor_manage";
    }

    /**
     * 跳转来访人员添加页面
     * */
    @RequestMapping("/toVisitorAdd")
    public String toVisitorAdd() {
        return "visitor/visitor_add";
    }

    /**
     * 跳转管理员添加界面
     * */
    @RequestMapping("/toAdminAdd")
    public String toAdminAdd() {
        return "admin/admin_add";
    }

    /**
     * 跳转管理员管理界面
     * */
    @RequestMapping("/toAdminManage")
    public String toAdminManage() {
        return "admin/admin_manage";
    }

    /**
     * 跳转公告添加页面
     * */
    @RequestMapping("/toNewsAdd")
    public String toNewsAdd() {
        return "news/news_add";
    }

    /**
     * 跳转公告管理界面
     * */
    @RequestMapping("/toNewsManage")
    public String toNewsManage() {
        return "news/news_manage";
    }

    /**
     * 跳转学生添加界面
     * */
    @RequestMapping("/toStudentAdd")
    public String toStudentAdd() {
        return "student/student_add";
    }

    /**
     * 跳转学生管理界面
     * */
    @RequestMapping("/toStudentManage")
    public String toStudentManage() {
        return "student/student_manage";
    }
}
