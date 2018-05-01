package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.dto.DormitoryDto;
import com.m.model.Dormitory;
import com.m.model.Student;
import com.m.service.DormitoryService;
import com.m.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/dormitory")
public class DormitoryController {
    @Autowired
    private DormitoryService dormitoryService;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveDormitory(Dormitory dormitory) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.dormitoryService.save(dormitory);
            map.put("status", 200);
        }catch (Exception e){
            e.printStackTrace();
            map.put("status", 400);
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
    public String removeDormitoryById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.dormitoryService.removeById(Id);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
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
    public String updateDormitory(@PathVariable("Id") Integer Id, Dormitory dormitory) {
        dormitory.setDormitoryId(Id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.dormitoryService.update(dormitory);
            map.put("status", 200);
        }catch (Exception e){
            map.put("status", 400);
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
    public DormitoryDto getDormitoryById(@PathVariable("Id") Integer Id) {
        DormitoryDto dormitoryDto = this.dormitoryService.getDormitoryManageById(Id);
        return dormitoryDto;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<DormitoryDto> loadAllDormitory(@RequestParam("pageNumber") Integer pageNumber,
                                            @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
       /* Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<DormitoryDto> dormitoryDtoList = this.dormitoryService.loadAllDormitory(map);
        PageInfo<DormitoryDto> pageInfo = new PageInfo<>(dormitoryDtoList);
        return pageInfo;
    }

    @RequestMapping(value = "/allot", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> allotDormitory(@RequestParam("stuId") Integer stuId,
                                             @RequestParam("dormId") Integer dormId){
        Map<String,String> map = new HashMap<>();
        Student student = new Student();
        student.setStudentId(stuId);
        student.setDormitoryId(dormId);
        try {
            this.studentService.update(student);
            map.put("status", "200");
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "400");
        }
        return map;
    }

    @RequestMapping(value = "/loadUsableDorm", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Dormitory> loadUsableDormitory() {
        List<Dormitory> dormitoryList = this.dormitoryService.loadUsableDorm();
        return dormitoryList;
    }
    @RequestMapping(value = "/getPersonnelDetail", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public DormitoryDto getDormitoryPersonnelDetail() {
        Integer dormId = 2;
        DormitoryDto dormitoryDto = this.dormitoryService.getDormitoryPersonnelDetail(dormId);
        return dormitoryDto;
    }
}
