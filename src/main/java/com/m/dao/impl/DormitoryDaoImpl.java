package com.m.dao.impl;

import com.m.dao.DormitoryDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Dormitory;
import org.springframework.stereotype.Repository;

@Repository
public class DormitoryDaoImpl extends BaseDaoImpl<Dormitory> implements DormitoryDao {
    public DormitoryDaoImpl() {
        this.setNameSpace("com.m.dao.DormitoryDao");
    }
}
