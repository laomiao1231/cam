package com.m.service.impl;

import com.m.dao.StudentDao;
import com.m.dto.StudentDto;
import com.m.dto.User;
import com.m.model.Student;
import com.m.service.StudentService;
import com.m.service.impl.base.BaseServiceImpl;
import com.m.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private EncodeUtil encodeUtil;

    @Override
    public List<StudentDto> loadStudentDetail(Map<String, Integer> map) {
        return this.studentDao.loadStudentDetail(map);
    }

    @Override
    public void save(Student student) {
        try {
            student.setStudentPassword(encodeUtil.md5Encode(student.getStudentAccount(), student.getStudentPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.save(student);
    }

    @Override
    public void update(Student student) {
        try {
            student.setStudentPassword(encodeUtil.md5Encode(student.getStudentAccount(), student.getStudentPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.update(student);
    }

    @Override
    public User getStudentByAccount(User user) throws Exception {
        Student student = this.studentDao.getStudentByAccount(user.getAccount());
        user.setPassWord(encodeUtil.md5Encode(user.getAccount(), user.getPassWord()));
        if(student == null) {
            throw new Exception("此用户不存在");
        }
        if(student.getStudentStatus() == 0) {
            throw new Exception("该账户已锁定");
        }
        if(!student.getStudentPassword().equals(user.getPassWord())) {
            throw new Exception("账户或密码错误");
        }
        user.setId(student.getStudentId());
        user.setAccount(student.getStudentAccount());
        user.setIdentity("student");
        user.setPower(student.getStudentPower());
        return user;
    }

    @Override
    public void changeStudentPassword(User user) {
        try {
            user.setPassWord(encodeUtil.md5Encode(user.getAccount(), user.getPassWord()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.studentDao.changeStudentPassword(user);
    }

    @Override
    public List<Student> loadNoDorm() {
        List<Student> studentList = this.studentDao.loadNoDorm();
        return studentList;
}
}
