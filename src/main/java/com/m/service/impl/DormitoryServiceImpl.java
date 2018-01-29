package com.m.service.impl;

import com.m.dao.DormitoryDao;
import com.m.dto.DormitoryDto;
import com.m.model.Dormitory;
import com.m.service.DormitoryService;
import com.m.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DormitoryServiceImpl extends BaseServiceImpl<Dormitory> implements DormitoryService {
    @Autowired
    private DormitoryDao dormitoryDao;

    @Override
    public DormitoryDto getDormitoryManageById(Integer Id) {
        return this.dormitoryDao.getDormitoryManageById(Id);
    }

    @Override
    public List<DormitoryDto> loadAllDormitory(Map<String,Integer> map) {
        List<DormitoryDto> dormitoryDtoList = this.dormitoryDao.loadAllDormitory(map);
        return dormitoryDtoList;
    }

    @Override
    public Integer getDormitoryPersonnel(Integer dormId) {
        return this.dormitoryDao.getDormitoryPersonnel(dormId);
    }

    @Override
    public Integer getDormitoryFull(Integer dormId) {
        return this.dormitoryDao.getDormitoryFull(dormId);
    }

    @Override
    public DormitoryDto getDormitoryPersonnelDetail(Integer dormId) {
        return this.dormitoryDao.getDormitoryPersonnelDetail(dormId);
    }
}
