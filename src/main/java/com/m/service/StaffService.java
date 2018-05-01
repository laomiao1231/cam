package com.m.service;

import com.m.dto.User;
import com.m.model.Staff;
import com.m.service.base.BaseService;

import java.util.List;

public interface StaffService extends BaseService<Staff> {
    User getStaffByAccount(User user) throws Exception;
    void changeStaffPassword(User user);
    List<Staff> getByKey(String keyWord);
}
