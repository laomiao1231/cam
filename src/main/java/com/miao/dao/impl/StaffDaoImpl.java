package com.miao.dao.impl;

import com.miao.dao.StaffDao;
import com.miao.dao.impl.base.BaseDaoImpl;
import com.miao.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    public StaffDaoImpl() {
        this.setNameSpace("com.miao.dao.StaffDao");
    }
}
