package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.model.Visitor;

public interface VisitorDao extends BaseDao<Visitor> {
    Integer getVisitorCount();
}
