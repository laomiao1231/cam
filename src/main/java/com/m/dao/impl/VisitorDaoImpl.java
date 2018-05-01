package com.m.dao.impl;

import com.m.dao.VisitorDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.dto.VisitorDto;
import com.m.model.Visitor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VisitorDaoImpl extends BaseDaoImpl<Visitor> implements VisitorDao {
    public VisitorDaoImpl() {
        this.setNameSpace("com.m.dao.VisitorDao");
    }

    protected static final String GET_COUNT = ".getCount";
    protected static final String GET_VISITOR_DETAILS = ".getVisitorDetails";
    protected static final String GET_BY_KEY = ".getByKey";

    @Override
    public Integer getVisitorCount() {
        return this.getSqlSession().selectOne(this.getNameSpace()+GET_COUNT);
    }

    @Override
    public List<VisitorDto> getVisitorDetails() {
        return this.getSqlSession().selectList(this.getNameSpace()+GET_VISITOR_DETAILS);
    }

    @Override
    public List<Visitor> getByKey(String keyWord) {
        return this.getSqlSession().selectList(this.getNameSpace()+GET_BY_KEY, keyWord);
    }
}
