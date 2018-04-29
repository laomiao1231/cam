package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.dto.User;
import com.m.model.Staff;
import com.m.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveStaff(Staff staff) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.staffService.save(staff);
            map.put("status",200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/remove/{Id}", produces = "application/json", method = RequestMethod.GET)
    @ResponseBody
    public String removeStaffById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.staffService.removeById(Id);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String updateStaff(@PathVariable("Id") Integer Id, Staff staff) {
        staff.setStaffId(Id);
        staff.setStaffAccount("300188");
        staff.setStaffPassword("123456");
        staff.setStaffName("mcf");
        staff.setStaffSex(0);
        staff.setStaffAge(35);
        staff.setStaffStatus(1);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.staffService.update(staff);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status",400);
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e1) {
                e1.printStackTrace();
            }
        }
        return string;
    }

    @RequestMapping(value = "/get/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Staff getStaffById(@PathVariable("Id") Integer Id) {
        Staff staff = this.staffService.getById(Id);
        return staff;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Staff> loadAllStaff(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);
        List<Staff> staffList = this.staffService.loadAll(map);
        return staffList;
    }

    @RequestMapping(value = "/changePassword/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String changeStaffPassword(@PathVariable("Id") Integer Id,
                                        @RequestParam("password") String password, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        user.setId(Id);
        user.setPassWord(password);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.staffService.changeStaffPassword(user);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
            e.printStackTrace();
        }finally {
            try {
                string = mapper.writeValueAsString(map);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return string;
    }
}