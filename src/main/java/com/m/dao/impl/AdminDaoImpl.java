package com.m.dao.impl;

import com.m.dao.AdminDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.User;
import com.m.model.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao{
    protected static final String CHANGE_PASSWORD = ".changePassword";

    public AdminDaoImpl() {
        this.setNameSpace("com.m.dao.AdminDao");
    }

    protected static final String GET_BY_ACCOUNT = ".getByAccount";
    protected static final String CHANGE_STATUS = ".changeStatus";
    protected static final String GET_COUNT = ".getCount";

    @Override
    public Admin getAdminByAccount(String Account) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_BY_ACCOUNT, Account);
    }

    @Override
    public void changeAdminStatus(Map<String, Integer> map) {
        this.getSqlSession().update(this.getNameSpace()+CHANGE_STATUS, map);
    }

    @Override
    public Integer getAdminCount() {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_COUNT);
    }

    @Override
    public void changeAdminPassword(User user) {
        this.getSqlSession().update(this.getNameSpace()+CHANGE_PASSWORD, user);
    }
}
