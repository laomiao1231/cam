package com.miao.model;

public class Dormitory {
    private Integer dormitoryId;
    private String dormitoryCode; //宿舍编号
    private String dormitoryBuliding; //所在楼宇
    private Integer dormitoryPersonnel; //实际人数
    private Integer dormitoryFull; //满员

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

    public String getDormitoryBuliding() {
        return dormitoryBuliding;
    }

    public void setDormitoryBuliding(String dormitoryBuliding) {
        this.dormitoryBuliding = dormitoryBuliding;
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
}
