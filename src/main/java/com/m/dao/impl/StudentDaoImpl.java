package com.m.dao.impl;

import com.m.dao.StudentDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    public StudentDaoImpl() {
        this.setNameSpace("com.m.dao.StudentDao");
    }
}
