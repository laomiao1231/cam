package com.m.service.impl;

import com.m.dao.AdminDao;
import com.m.dto.User;
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
    public User getAdminByAccount(User user) throws Exception{
        Admin admin = this.adminDao.getAdminByAccount(user.getAccount());
        user.setPassWord(encodeUtil.md5Encode(user.getAccount(), user.getPassWord()));
        if(admin == null) {
            throw new Exception("该用户不存在");
        }
        if(admin.getAdminStatus() == 0) {
            throw new Exception("该账户不存在");
        }
        if(!admin.getAdminPassword().equals(user.getPassWord())) {
            throw new Exception("账户或密码错误");
        }
        user.setId(admin.getAdminId());
        user.setAccount(admin.getAdminAccount());
        user.setIdentity("admin");
        user.setPower(admin.getAdminPower());
        return user;
    }

    @Override
    public void changeAdminStatus(Map<String, Integer> map) {
        this.adminDao.changeAdminStatus(map);
    }

    @Override
    public Integer getAdminCount() {
        return this.adminDao.getAdminCount();
    }

    @Override
    public void changeAdminPassword(User user) {
        try {
            user.setPassWord(encodeUtil.md5Encode(user.getAccount(), user.getPassWord()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.adminDao.changeAdminPassword(user);
    }
}
