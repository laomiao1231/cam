package com.m.service;

import com.m.dto.StudentDto;
import com.m.model.Student;
import com.m.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface StudentService extends BaseService<Student> {
    List<StudentDto> loadStudentDetail(Map<String,Integer> map);
    Student getStudentByAccount(Student student) throws Exception;
    void changeStudentPassword(Student student);
}
