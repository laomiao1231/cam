package com.m.dao.impl;

import com.m.dao.AdminDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    public AdminDaoImpl() {
        this.setNameSpace("com.m.dao.AdminDao");
    }
}
