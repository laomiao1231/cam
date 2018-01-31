package com.m.dao.impl;

import com.m.dao.AdminDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    public AdminDaoImpl() {
        this.setNameSpace("com.m.dao.AdminDao");
    }

    protected static final String GET_BY_ACCOUNT = ".getByAccount";

    @Override
    public Admin getAdminByAccount(String Account) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_BY_ACCOUNT, Account);
    }
}
