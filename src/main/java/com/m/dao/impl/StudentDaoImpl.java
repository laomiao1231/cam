package com.m.dao.impl;

import com.m.dao.StudentDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.StudentDto;
import com.m.dto.User;
import com.m.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class StudentDaoImpl extends BaseDaoImpl<Student> implements StudentDao {
    public StudentDaoImpl() {
        this.setNameSpace("com.m.dao.StudentDao");
    }

    protected static final String GET_BY_ACCOUNT = ".getByAccount";
    protected static final String CHANGE_PASSWORD = ".changePassword";
    protected static final String LOAD_NO_DORM=".loadNoDorm";
    protected static final String GET_BY_KEY = ".getByKey";

    @Override
    public List<StudentDto> loadStudentDetail(Map<String, Integer> map) {
        return this.getSqlSession().selectList(this.getNameSpace()+LOAD_ALL, map);
    }

    @Override
    public Student getStudentByAccount(String Account) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_BY_ACCOUNT, Account);
    }

    @Override
    public void changeStudentPassword(User user) {
        this.getSqlSession().update(this.getNameSpace()+CHANGE_PASSWORD, user);
    }

    @Override
    public List<Student> loadNoDorm() {
        return this.getSqlSession().selectList(this.getNameSpace() + LOAD_NO_DORM);
    }

    @Override
    public List<Student> getByKey(String keyWord) {
        return this.getSqlSession().selectList(this.getNameSpace()+GET_BY_KEY, keyWord);
    }
}
