package com.m.dto;

import com.m.model.Staff;

public class DormitoryDto {
    private Integer dormitoryId;
    private String dormitoryCode; //������
    private String dormitoryBuilding; //����¥��
    private Integer dormitoryPersonnel; //ʵ������
    private Integer dormitoryFull; //��Ա
    private Staff staff;

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
}
