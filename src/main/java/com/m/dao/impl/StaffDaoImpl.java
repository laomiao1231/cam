package com.m.dao.impl;

import com.m.dao.StaffDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    public StaffDaoImpl() {
        this.setNameSpace("com.m.dao.StaffDao");
    }
}
