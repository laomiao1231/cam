package com.m.dao.impl;

import com.m.dao.StudentDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.StudentDto;
import com.m.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    public StudentDaoImpl() {
        this.setNameSpace("com.m.dao.StudentDao");
    }

    @Override
    public List<StudentDto> loadStudentDetail(Map<String, Integer> map) {
        return this.getSqlSession().selectList(this.getNameSpace()+LOAD_ALL, map);
    }
}
