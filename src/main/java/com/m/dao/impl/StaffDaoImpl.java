package com.m.dao.impl;

import com.m.dao.StaffDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.User;
import com.m.model.Staff;
import org.springframework.stereotype.Repository;

@Repository
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements StaffDao {
    protected static final String CHANGE_PASSWORD = ".changePassword";

    public StaffDaoImpl() {
        this.setNameSpace("com.m.dao.StaffDao");
    }

    protected static final String GET_BY_ACCOUNT = ".getByAccount";

    @Override
    public Staff getStaffByAccount(String account) {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_BY_ACCOUNT, account);
    }

    @Override
    public void changeStaffPassword(User user) {
        this.getSqlSession().update(this.getNameSpace()+CHANGE_PASSWORD, user);
    }
}
