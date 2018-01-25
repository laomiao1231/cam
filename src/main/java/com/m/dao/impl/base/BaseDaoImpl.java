package com.m.dao.impl.base;

import com.m.dao.base.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BaseDaoImpl<T> extends SqlSessionDaoSupport implements BaseDao<T> {
    protected static final String SAVE = ".save";
    protected static final String REMOVE_BY_ID = ".removeById";
    protected static final String UPDATE = ".update";
    protected static final String GET_BY_ID = ".getById";
    protected static final String LOAD_ALL = ".loadAll";

    private String nameSpace;

    public String getNameSpace() {
        return nameSpace;
    }

    public void setNameSpace(String nameSpace) {
        this.nameSpace = nameSpace;
    }

    @Override
    @Autowired
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }

    public void save(T t) {
        this.getSqlSession().insert(getNameSpace()+SAVE, t);
    }

    public void removeById(Integer Id) {
        this.getSqlSession().delete(getNameSpace()+REMOVE_BY_ID, Id);
    }

    public void update(T t) {
        this.getSqlSession().update(getNameSpace()+UPDATE, t);
    }

    public T getById(Integer Id) {
        return this.getSqlSession().selectOne(getNameSpace()+GET_BY_ID, Id);
    }

    public List<T> loadAll(Map<String, Integer> params) {
        return this.getSqlSession().selectList(getNameSpace()+LOAD_ALL, params);
    }
}
