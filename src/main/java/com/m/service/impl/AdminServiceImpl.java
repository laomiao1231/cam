package com.m.service.impl;

import com.m.dao.AdminDao;
import com.m.model.Admin;
import com.m.service.AdminService;
import com.m.service.impl.base.BaseServiceImpl;
import com.m.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {
    @Autowired
    private EncodeUtil encodeUtil;
    @Autowired
    private AdminDao adminDao;

    @Override
    public void save(Admin admin) {
        try {
            admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.save(admin);
    }

    @Override
    public void update(Admin admin) {
        try {
            admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.update(admin);
    }

    @Override
    public Admin getAdminByAccount(Admin admin) throws Exception{
        Admin adn = this.adminDao.getAdminByAccount(admin.getAdminAccount());
        admin.setAdminPassword(encodeUtil.md5Encode(admin.getAdminAccount(), admin.getAdminPassword()));
        if(adn == null) {
            throw new Exception("账户不存在");
        }
        if(adn.getAdminStatus() == 0) {
            throw new Exception("账户已锁定");
        }
        if(!adn.getAdminPassword().equals(admin.getAdminPassword())) {
            throw new Exception("账户或密码错误");
        }
        return adn;
    }

    @Override
    public void changeAdminStatus(Map<String, Integer> map) {
        this.adminDao.changeAdminStatus(map);
    }

    @Override
    public Integer getAdminCount() {
        return this.adminDao.getAdminCount();
    }
}
