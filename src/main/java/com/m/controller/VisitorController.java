package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.model.Visitor;
import com.m.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveVisitor(Visitor visitor) {
        visitor.setStaffId(2);
        visitor.setVisitorName("mxf");
        visitor.setVisitorCompany("sie");
        visitor.setVisitorDate(new Date());
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String updateVisitor(@PathVariable("Id") Integer Id, Visitor visitor) {
        visitor.setVisitorId(Id);
        visitor.setStaffId(2);
        visitor.setVisitorName("mm");
        visitor.setVisitorCompany("sie");
        visitor.setVisitorDate(new Date());
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

    @RequestMapping(value = "loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<Visitor> loadAllVisitor(@RequestParam("pageNumber") Integer pageNumber,
                                        @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);
        List<Visitor> visitorList = this.visitorService.loadAll(map);
        return visitorList;
    }
}
