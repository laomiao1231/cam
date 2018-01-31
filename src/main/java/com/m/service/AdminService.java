package com.m.service;

import com.m.model.Admin;
import com.m.service.base.BaseService;

public interface AdminService extends BaseService<Admin> {
    Admin getAdminByAccount(Admin admin) throws Exception;
}
