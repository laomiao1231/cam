package com.m.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.m.dto.StudentDto;
import com.m.dto.User;
import com.m.model.Student;
import com.m.service.StudentService;
import com.m.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {
    protected static final String ACCOUNT_SUFFIX = "14412";
    protected static final String PASSWORD = "123456";

    @Autowired
    private StudentService studentService;

    @RequestMapping(value = "/save", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(Student student) {
        student.setStudentPassword(PASSWORD);
        student.setStudentAccount(ACCOUNT_SUFFIX+RandomUtil.Suffix());
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try {
            this.studentService.save(student);
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

    @RequestMapping(value = "/update/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
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

    @RequestMapping(value = "/get/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public Student getStudentById(@PathVariable("Id") Integer Id) {
        Student student = this.studentService.getById(Id);
        return student;
    }

    @RequestMapping(value = "/loadAll", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Student> loadAllStudent(@RequestParam("pageNumber") Integer pageNumber,
                                               @RequestParam("pageSize") Integer pageSize) {
        Map<String, Integer> map = new HashMap<>();
       /* Integer start = (pageNumber-1)*pageSize;
        Integer end = pageSize;
        map.put("start", start);
        map.put("end", end);*/
        PageHelper.startPage(pageNumber, pageSize);
        List<Student> studentList = this.studentService.loadAll(map);
        PageInfo<Student> pageInfo = new PageInfo<>(studentList,5);
        return pageInfo;
    }

    @RequestMapping(value = "/loadNoDorm", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Student> loadStudentNoDorm(@RequestParam("pageNumber") Integer pageNumber,
                                               @RequestParam("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Student> studentList = this.studentService.loadNoDorm();
        PageInfo<Student> pageInfo = new PageInfo<>(studentList, 5);
        return pageInfo;
    }

    @RequestMapping(value = "/getByKey", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public PageInfo<Student> getStudentByKey(@RequestParam("pageNumber") Integer pageNumber,
                                               @RequestParam("pageSize") Integer pageSize, @RequestParam("keyWord") String keyWord) {
        PageHelper.startPage(pageNumber, pageSize);
        List<Student> studentList = this.studentService.getByKey(keyWord);
        PageInfo<Student> pageInfo = new PageInfo<>(studentList, 5);
        return pageInfo;
    }

    @RequestMapping(value = "/getDetail/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    public String getStudentDetailById(@PathVariable("Id") Integer Id, Map<String,Object> map) {
        StudentDto studentDto = this.studentService.getDetail(Id);
        map.put("studentDetail", studentDto);
        return "student/student_detail";
    }

    @RequestMapping(value = "/change/{Id}", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String changeAdmin(@PathVariable("Id") Integer Id) {
        Student student = this.studentService.getById(Id);
        Map<String, Integer> temp_map = new HashMap<>();
        temp_map.put("Id", Id);
        if(student.getStudentStatus() == 1) {
            temp_map.put("state", 0);
        }else {
            temp_map.put("state", 1);
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Integer> map = new HashMap<>();
        String string = null;
        try{
            this.studentService.changeStudentStatus(temp_map);
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
