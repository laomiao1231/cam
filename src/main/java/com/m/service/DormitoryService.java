package com.m.service;

import com.m.dto.DormitoryDto;
import com.m.model.Dormitory;
import com.m.service.base.BaseService;

import java.util.List;
import java.util.Map;

public interface DormitoryService extends BaseService<Dormitory> {
    DormitoryDto getDormitoryManageById(Integer Id);
    List<DormitoryDto> loadAllDormitory(Map<String,Integer> map);
    Integer getDormitoryPersonnel(Integer dormId);
    Integer getDormitoryFull(Integer dormId);
    DormitoryDto getDormitoryPersonnelDetail(Integer dormId);
}
