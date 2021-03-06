package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.dto.User;
import com.m.model.Admin;

import java.util.Map;

public interface AdminDao extends BaseDao<Admin> {
    Admin getAdminByAccount(String Account);
    void changeAdminStatus(Map<String,Integer> map);
    Integer getAdminCount();
    void changeAdminPassword(User user);
}
