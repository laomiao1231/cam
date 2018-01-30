package com.m.service.impl;

import com.m.model.Admin;
import com.m.service.AdminService;
import com.m.service.impl.base.BaseServiceImpl;
import com.m.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private EncodeUtil encodeUtil;

    @Override
    public void save(Admin admin) {
        try {
            admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.save(admin);
    }
}
