package com.m.dao.impl;

import com.m.dao.LostItemsDao;
import com.m.dao.impl.base.BaseDaoImpl;
import com.m.model.LostItems;
import org.springframework.stereotype.Repository;

@Repository
public class LostItemsDaoImpl extends BaseDaoImpl<LostItems> implements LostItemsDao {
    public LostItemsDaoImpl() {
        this.setNameSpace("com.m.dao.LostItemsDao");
    }
}
