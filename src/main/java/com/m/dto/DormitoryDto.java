package com.m.dto;

import com.m.model.Staff;
import com.m.model.Student;

import java.util.List;

public class DormitoryDto {
    private Integer dormitoryId;
    private String dormitoryCode; //宿舍编号
    private String dormitoryBuilding; //所在楼宇
    private Integer dormitoryPersonnel; //实际人数
    private Integer dormitoryFull; //满员
    private Staff staff;
    private List<Student> studentList;

    public Integer getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(Integer dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getDormitoryCode() {
        return dormitoryCode;
    }

    public void setDormitoryCode(String dormitoryCode) {
        this.dormitoryCode = dormitoryCode;
    }

    public String getDormitoryBuilding() {
        return dormitoryBuilding;
    }

    public void setDormitoryBuilding(String dormitoryBuilding) {
        this.dormitoryBuilding = dormitoryBuilding;
    }

    public Integer getDormitoryPersonnel() {
        return dormitoryPersonnel;
    }

    public void setDormitoryPersonnel(Integer dormitoryPersonnel) {
        this.dormitoryPersonnel = dormitoryPersonnel;
    }

    public Integer getDormitoryFull() {
        return dormitoryFull;
    }

    public void setDormitoryFull(Integer dormitoryFull) {
        this.dormitoryFull = dormitoryFull;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
