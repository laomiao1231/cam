package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.m.dto.StudentDto;
import com.m.model.Student;
import com.m.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String saveStudent(Student student) {
        student.setStudentAccount("1415925080");
        student.setStudentPassword("123456");
        student.setStudentMajor("RJ");
        student.setStudentName("mx");
        student.setStudentAge(23);
        student.setStudentSex(0);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.studentService.save(student);
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

    @RequestMapping(value = "/remove/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String removeStudentById(@PathVariable("Id") Integer Id) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.studentService.removeById(Id);
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
    public String updateStudent(@PathVariable("Id") Integer Id, Student student){
        student.setStudentId(Id);
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.studentService.update(student);
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

    @RequestMapping(value = "/changePassword/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String changeStudentPassword(@PathVariable("Id") Integer Id, Student student) {
        student.setStudentId(Id);
        student.setStudentPassword("121212");
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.studentService.changeStudentPassword(student);
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

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public List<StudentDto> loadAllStudent(@RequestParam("pageNumber") Integer pageNumber,
                                           @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
        Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);
        List<StudentDto> studentDtoList = this.studentService.loadStudentDetail(map);
        return studentDtoList;
    }

}
