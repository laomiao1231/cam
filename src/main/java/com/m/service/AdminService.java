package com.m.service;

import com.m.dto.User;
import com.m.model.Admin;
import com.m.service.base.BaseService;

import java.util.Map;

public interface AdminService extends BaseService<Admin> {
    User getAdminByAccount(User user) throws Exception;
    void changeAdminStatus(Map<String,Integer> map);
    Integer getAdminCount();
    void changeAdminPassword(User user);
}
