package com.m.service.impl;

import com.m.dao.LostItemsDao;
import com.m.model.LostItems;
import com.m.service.LostItemsService;
import com.m.service.impl.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LostItemsServiceImpl extends BaseServiceImpl<LostItems> implements LostItemsService {
    @Autowired
    private LostItemsDao lostItemsDao;

    @Override
    public List<LostItems> getByKey(String keyWord) {
        List<LostItems> lostItemsList = this.lostItemsDao.getByKey(keyWord);
        return lostItemsList;
    }
}
