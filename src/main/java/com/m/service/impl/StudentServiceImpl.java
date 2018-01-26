package com.m.service.impl;

import com.m.dao.StudentDao;
import com.m.dto.StudentDto;
import com.m.model.Student;
import com.m.service.StudentService;
import com.m.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;

    @Override
    public List<StudentDto> loadStudentDetail(Map<String, Integer> map) {
        return this.studentDao.loadStudentDetail(map);
    }
}
