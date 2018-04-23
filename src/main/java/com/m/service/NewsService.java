package com.m.service;

import com.m.model.News;
import com.m.service.base.BaseService;

import java.util.List;

public interface NewsService extends BaseService<News> {
    List<News> getByKey(String keyWord);
}
