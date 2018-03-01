package com.m.dao.impl;

import com.m.dao.LostItemsDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.LostItems;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LostItemsDaoImpl extends BaseDaoImpl<LostItems> implements LostItemsDao {
    public LostItemsDaoImpl() {
        this.setNameSpace("com.m.dao.LostItemsDao");
    }

    protected final static String GET_BY_KEY = ".getByKey";

    @Override
    public List<LostItems> getByKey(String keyWord) {
        return this.getSqlSession().selectList(this.getNameSpace()+GET_BY_KEY, keyWord);
    }
}
