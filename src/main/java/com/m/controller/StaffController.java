package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.dto.User;
import com.m.model.Staff;
import com.m.service.StaffService;
import com.m.util.RandomUtil;
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
    protected static final String ACCOUNT_SUFFIX = "1698";
    protected static final String PASSWORD = "123456";

    @Autowired
    private StaffService staffService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveStaff(Staff staff) {
        staff.setStaffPassword(PASSWORD);
        staff.setStaffAccount(ACCOUNT_SUFFIX+ RandomUtil.Suffix());
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateStaff(@PathVariable("Id") Integer Id, Staff staff) {
        staff.setStaffId(Id);
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
    public PageInfo<Staff> loadAllStaff(@RequestParam("pageNumber") Integer pageNumber,
                                    @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        /*Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<Staff> staffList = this.staffService.loadAll(map);
        PageInfo<Staff> pageInfo = new PageInfo<>(staffList, 5);
        return pageInfo;
    }

    @RequestMapping(value = "/getByKey", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Staff> getStaffByKey(@RequestParam("pageNumber") Integer pageNumber,
                                        @RequestParam("pageSize") Integer pageSize, @RequestParam("keyWord") String keyWord) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Staff> staffList = this.staffService.getByKey(keyWord);
        PageInfo<Staff> pageInfo = new PageInfo<>(staffList, 5);
        return pageInfo;
    }

    @RequestMapping(value = "/change/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String changeAdmin(@PathVariable("Id") Integer Id) {
        Staff staff = this.staffService.getById(Id);
        Map<String, Integer> temp_map = new HashMap<>();
        temp_map.put("Id", Id);
        if(staff.getStaffStatus() == 1) {
            temp_map.put("state", 0);
        }else {
            temp_map.put("state", 1);
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.staffService.changeStaffStatus(temp_map);
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

}