package com.m.service.impl;

import com.m.dao.StudentDao;
import com.m.dto.StudentDto;
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
    public Student getStudentByAccount(Student student) throws Exception {
        Student stu = this.studentDao.getStudentByAccount(student.getStudentAccount());
        student.setStudentPassword(encodeUtil.md5Encode(student.getStudentAccount(), student.getStudentPassword()));
        if(stu == null) {
            throw new Exception("账户不存在");
        }
        if(!stu.getStudentPassword().equals(student.getStudentPassword())) {
            throw new Exception("账户或密码错误");
        }
        return stu;
    }
}
