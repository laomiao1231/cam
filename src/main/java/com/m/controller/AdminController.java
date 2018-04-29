package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.dto.User;
import com.m.model.Admin;
import com.m.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    protected static final String PASSWORD = "123456";

    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveAdmin(Admin admin) {
        admin.setAdminPower(2);
        admin.setAdminPassword(PASSWORD);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.adminService.save(admin);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/remove/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String removeAdminById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.adminService.removeById(Id);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateAdmin(@PathVariable("Id") Integer Id, @RequestBody Admin admin) {
        admin.setAdminId(Id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.adminService.update(admin);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/get/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Admin getAdminById(@PathVariable("Id") Integer Id) {
        Admin admin = this.adminService.getById(Id);
        return admin;
    }

    @RequestMapping(value = "/getCount", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Integer getAdminCount() {
        Integer adminCount = this.adminService.getAdminCount();
        return adminCount;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Admin> loadAllAdmin(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        /*Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<Admin> adminList = this.adminService.loadAll(map);
        PageInfo<Admin> pageInfo = new PageInfo<>(adminList, 3);
        return pageInfo;
    }

    @RequestMapping(value = "/change/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String changeAdmin(@PathVariable("Id") Integer Id) {
        Admin admin = this.adminService.getById(Id);
        Map<String, Integer> temp_map = new HashMap<>();
        temp_map.put("Id", Id);
        if(admin.getAdminStatus() == 1) {
            temp_map.put("state", 0);
        }else {
            temp_map.put("state", 1);
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.adminService.changeAdminStatus(temp_map);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }


    @RequestMapping(value = "/logout", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String logoutAdmin(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        map.put("status", 200);
        String string = null;
        try {
            string = mapper.writeValueAsString(map);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return string;
    }

}