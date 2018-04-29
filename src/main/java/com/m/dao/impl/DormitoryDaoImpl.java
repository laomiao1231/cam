package com.m.dao.impl;

import com.m.dao.DormitoryDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.DormitoryDto;
import com.m.model.Dormitory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class DormitoryDaoImpl extends BaseDaoImpl<Dormitory> implements DormitoryDao {
    public DormitoryDaoImpl() {
        this.setNameSpace("com.m.dao.DormitoryDao");
    }

    protected static final String GET_PERSONNEL = ".getPersonnel";
    protected static final String GET_FULL = ".getFull";
    protected static final String GET_PERSONNEL_DETAIL = ".getPersonnelDetail";
    protected static final String LOAD_USABLE_DORM = ".loadUsableDorm";

    @Override
    public DormitoryDto getDormitoryManageById(Integer Id) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_BY_ID, Id);
    }

    @Override
    public List<DormitoryDto> loadAllDormitory(Map<String,Integer> map) {
        return this.getSqlSession().selectList(this.getNameSpace()+LOAD_ALL, map);
    }

    @Override
    public Integer getDormitoryPersonnel(Integer dormId) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_PERSONNEL, dormId);
    }

    @Override
    public Integer getDormitoryFull(Integer dormId) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_FULL, dormId);
    }

    @Override
    public DormitoryDto getDormitoryPersonnelDetail(Integer dormId) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_PERSONNEL_DETAIL, dormId);
    }

    @Override
    public List<Dormitory> loadUsableDorm() {
        return this.getSqlSession().selectList(this.getNameSpace()+LOAD_USABLE_DORM);
    }
}
