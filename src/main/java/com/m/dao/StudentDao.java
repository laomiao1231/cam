package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.dto.StudentDto;
import com.m.model.Student;

import java.util.List;
import java.util.Map;

public interface StudentDao extends BaseDao<Student> {
    List<StudentDto> loadStudentDetail(Map<String,Integer> map);
    Student getStudentByAccount(String Account);
    void changeStudentPassword(Student student);
}
