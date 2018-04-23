package com.m.service.impl;

import com.m.dao.StaffDao;
import com.m.dto.User;
import com.m.model.Staff;
import com.m.service.StaffService;
import com.m.service.impl.base.BaseServiceImpl;
import com.m.util.EncodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffServiceImpl extends BaseServiceImpl<Staff> implements StaffService {
    @Autowired
    private EncodeUtil encodeUtil;
    @Autowired
    private StaffDao staffDao;

    @Override
    public User getStaffByAccount(User user) throws Exception {
        Staff staff = this.staffDao.getStaffByAccount(user.getAccount());
        staff.setStaffPassword(encodeUtil.md5Encode(user.getAccount(),user.getPassWord()));
        if(staff == null) {
            throw new Exception("账户不存在");
        }
        if(staff.getStaffStatus() == 0) {
            throw new Exception("账户已锁定");
        }
        if(!staff.getStaffPassword().equals(user.getPassWord())) {
            throw new Exception("账户或密码错误");
        }
        return user;
    }
}
