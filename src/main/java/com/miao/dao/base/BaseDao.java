package com.miao.dao.base;

import java.util.List;
import java.util.Map;

public interface BaseDao<T> {
    void save(T t);
    void removeById(Integer Id);
    void update(T t);
    T getById(Integer Id);
    List<T> loadAll(Map<String,Integer> params);
}
