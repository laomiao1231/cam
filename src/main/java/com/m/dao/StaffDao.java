package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.dto.User;
import com.m.model.Staff;

public interface StaffDao extends BaseDao<Staff> {
    Staff getStaffByAccount(String account);
    void changeStaffPassword(User user);
}
