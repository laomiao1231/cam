package com.m.dao;

import com.m.dao.base.BaseDao;
import com.m.dto.VisitorDto;
import com.m.model.Visitor;

import java.util.List;

public interface VisitorDao extends BaseDao<Visitor> {
    Integer getVisitorCount();
    List<VisitorDto> getVisitorDetails();
    List<Visitor> getByKey(String keyWord);
}
