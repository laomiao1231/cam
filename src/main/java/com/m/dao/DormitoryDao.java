package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.dto.DormitoryDto;
import com.m.model.Dormitory;

import java.util.List;
import java.util.Map;

public interface DormitoryDao extends BaseDao<Dormitory> {
    DormitoryDto getDormitoryManageById(Integer Id);
    List<DormitoryDto> loadAllDormitory(Map<String,Integer> map);
    Integer getDormitoryPersonnel(Integer dormId);
    Integer getDormitoryFull(Integer dormId);
    DormitoryDto getDormitoryPersonnelDetail(Integer dormId);
    List<Dormitory> loadUsableDorm();
}
