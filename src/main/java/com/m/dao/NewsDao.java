package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.model.News;

import java.util.List;

public interface NewsDao extends BaseDao<News> {
    List<News> getByKey(String keyWord);
}
