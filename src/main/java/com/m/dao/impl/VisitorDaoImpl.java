package com.m.dao.impl;

import com.m.dao.VisitorDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.Visitor;
import org.springframework.stereotype.Repository;

@Repository
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao {
    public VisitorDaoImpl() {
        this.setNameSpace("com.m.dao.VisitorDao");
    }

    protected static final String GET_COUNT = ".getCount";

    @Override
    public Integer getVisitorCount() {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_COUNT);
    }
}
