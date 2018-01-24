package com.miao.dao.impl;

import com.miao.dao.AdminDao;
import com.miao.dao.impl.base.BaseDaoImpl;
import com.miao.model.Admin;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    public AdminDaoImpl() {
        this.setNameSpace("com.miao.dao.AdminDao");
    }
}
