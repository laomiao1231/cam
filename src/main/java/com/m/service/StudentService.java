package com.m.service;

import com.m.dto.StudentDto;
import com.m.dto.User;
import com.m.model.Student;
import com.m.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface StudentService extends BaseService<Student> {
    List<StudentDto> loadStudentDetail(Map<String,Integer> map);
    User getStudentByAccount(User user) throws Exception;
    void changeStudentPassword(User user);
    List<Student> loadNoDorm();
}
