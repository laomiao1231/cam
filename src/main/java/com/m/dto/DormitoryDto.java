package com.m.dto;

import com.m.model.Staff;

public class DormitoryDto {
    private Integer dormitoryId;
    private String dormitoryCode; //宿舍编号
    private String dormitoryBuilding; //所在楼宇
    private Integer dormitoryPersonnel; //实际人数
    private Integer dormitoryFull; //满员
    private Staff staff;
}
