package com.m.service;

import com.m.model.LostItems;
import com.m.service.base.BaseService;

import java.util.List;

public interface LostItemsService extends BaseService<LostItems> {
    List<LostItems> getByKey(String keyWord);
}
