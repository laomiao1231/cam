package com.m.dao.impl;

import com.m.dao.NewsDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.News;
import org.springframework.stereotype.Repository;

@Repository
public class NewsDaoImpl extends BaseDaoImpl<News> implements NewsDao {
    public NewsDaoImpl() {
        this.setNameSpace("com.m.dao.NewsDao");
    }
}
