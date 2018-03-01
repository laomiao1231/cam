package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.model.LostItems;

import java.util.List;

public interface LostItemsDao extends BaseDao<LostItems> {
    List<LostItems> getByKey(String keyWord);
}
