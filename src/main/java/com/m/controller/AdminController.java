package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    private AdminService adminService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveAdmin(Admin admin) {
        admin.setAdminAccount("141525");
        admin.setAdminPassword("123456");
        admin.setAdminPower(1);
        admin.setAdminStatus(0);
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String updateAdmin(@PathVariable("Id") Integer Id, Admin admin) {
        admin.setAdminId(Id);
        admin.setAdminPassword("121318");
        admin.setAdminPower(2);
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

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Admin> loadAllAdmin(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);
        List<Admin> adminList = this.adminService.loadAll(map);
        return adminList;
    }

    @RequestMapping(value = "/enable/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String enableAdmin(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.changeStatus(Id, 1);
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

    @RequestMapping(value = "/disable/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String disableAdmin(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.changeStatus(Id, 0);
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

    private void changeStatus(Integer Id, Integer state) {
        Map<String, Integer> map = new HashMap<>();
        map.put("Id", Id);
        map.put("state", state);
        this.adminService.changeAdminStatus(map);
    }

    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String loginAdmin(@RequestBody Admin admin, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("***********"+admin.getAdminAccount());
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Object> map = new HashMap<>();
        String string = null;
        try{
            Admin adn = this.adminService.getAdminByAccount(admin);
            request.getSession().setAttribute("admin", adn);
            map.put("status", 200);
        } catch (Exception e) {
            response.setStatus(400);
            map.put("message", e.getMessage());
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