package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.dto.VisitorDto;
import com.m.model.Visitor;
import com.m.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @Autowired
    private VisitorService visitorService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveVisitor(@RequestBody Visitor visitor) {
        System.out.println("**************");
        System.out.println(visitor);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.visitorService.save(visitor);
            map.put("status", 200);
        }catch (Exception e) {
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
    public String removeVisitorById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.visitorService.removeById(Id);
            map.put("status", 200);
        }catch (Exception e) {
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String updateVisitor(@PathVariable("Id") Integer Id, Visitor visitor) {
        visitor.setVisitorId(Id);
        System.out.println(Id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.visitorService.update(visitor);
            map.put("status", 200);
        }catch (Exception e) {
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
    public Visitor getVisitorById(@PathVariable("Id") Integer Id) {
        Visitor visitor = this.visitorService.getById(Id);
        return visitor;
    }

    @RequestMapping(value = "/getVisitorDetails", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<VisitorDto> loadAllVisitor(@RequestParam("pageNumber") Integer pageNumber,
                                               @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
       /* Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<VisitorDto> visitorList = this.visitorService.getVisitorDetails();
        PageInfo<VisitorDto> pageInfo = new PageInfo<>(visitorList, 4);
        return pageInfo;
    }
}
