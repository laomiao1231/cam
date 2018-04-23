package com.m.dao.impl;

import com.m.dao.NewsDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.News;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() {
        this.setNameSpace("com.m.dao.NewsDao");
    }

    protected final static String GET_BY_KEY = ".getByKey";

    @Override
    public List<News> getByKey(String keyWord) {
        return this.getSqlSession().selectList(this.getNameSpace()+GET_BY_KEY, keyWord);
    }
}
