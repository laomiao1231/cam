package com.miao.service.impl.base;

import com.miao.dao.base.BaseDao;
import com.miao.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class BaseServiceImpl<T> implements BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;

    @Override
    public void save(T t) {
        this.baseDao.save(t);
    }

    @Override
    public void removeById(Integer Id) {
        this.baseDao.removeById(Id);
    }

    @Override
    public void update(T t) {
        this.baseDao.update(t);
    }

    @Override
    public T getById(Integer Id) {
        return this.baseDao.getById(Id);
    }

    @Override
    public List<T> loadAll(Map<String, Integer> params) {
        return this.baseDao.loadAll(params);
    }
}
