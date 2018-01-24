package com.miao.dao.impl;

import com.miao.dao.LostItemsDao;
import com.miao.dao.impl.base.BaseDaoImpl;
import com.miao.model.LostItems;
import org.springframework.stereotype.Repository;

@Repository
public class LostItemsDaoImpl extends BaseDaoImpl<LostItems> implements LostItemsDao {
    public LostItemsDaoImpl() {
        this.setNameSpace("com.miao.dao.LostItemsDao");
    }
}
