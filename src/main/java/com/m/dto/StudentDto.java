package com.m.dto;

import com.m.model.Dormitory;
import com.m.model.Staff;

public class StudentDto {
    private Integer studentId;
    private String studentAccount;
    private String studentPassword;
    private String studentName;
    private Integer studentSex;
    private Integer studentAge;
    private String studentMajor;
    private String studentClass;
    private Dormitory dormitory;
    private Staff staff;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentAccount() {
        return studentAccount;
    }

    public void setStudentAccount(String studentAccount) {
        this.studentAccount = studentAccount;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword) {
        this.studentPassword = studentPassword;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentSex() {
        return studentSex;
    }

    public void setStudentSex(Integer studentSex) {
        this.studentSex = studentSex;
    }

    public Integer getStudentAge() {
        return studentAge;
    }

    public void setStudentAge(Integer studentAge) {
        this.studentAge = studentAge;
    }

    public String getStudentMajor() {
        return studentMajor;
    }

    public void setStudentMajor(String studentMajor) {
        this.studentMajor = studentMajor;
    }

    public String getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(String studentClass) {
        this.studentClass = studentClass;
    }

    public Dormitory getDormitory() {
        return dormitory;
    }

    public void setDormitory(Dormitory dormitory) {
        this.dormitory = dormitory;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
