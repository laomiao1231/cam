package com.m.service.impl;

import com.m.dao.NewsDao;
import com.m.model.News;
import com.m.service.NewsService;
import com.m.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
    @Autowired
    private NewsDao newsDao;

    @Override
    public List<News> getByKey(String keyWord) {
        List<News> newsList = this.newsDao.getByKey(keyWord);
        return newsList;
    }
}
