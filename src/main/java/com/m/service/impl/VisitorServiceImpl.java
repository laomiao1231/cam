package com.m.service.impl;

import com.m.dao.VisitorDao;
import com.m.model.Visitor;
import com.m.service.VisitorService;
import com.m.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VisitorServiceImpl extends BaseServiceImpl<Visitor> implements VisitorService {
    @Autowired
    private VisitorDao visitorDao;

    @Override
    public Integer getVisitorCount() {
        return this.visitorDao.getVisitorCount();
    }
}
