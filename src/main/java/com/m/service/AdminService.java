package com.m.service;

import com.m.model.Admin;
import com.m.service.base.BaseService;

import java.util.Map;

public interface AdminService extends BaseService<Admin> {
    Admin getAdminByAccount(Admin admin) throws Exception;
    void changeAdminStatus(Map<String,Integer> map);
    Integer getAdminCount();
}
