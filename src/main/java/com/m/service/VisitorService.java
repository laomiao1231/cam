package com.m.service;

import com.m.dto.VisitorDto;
import com.m.model.Visitor;
import com.m.service.base.BaseService;

import java.util.List;

public interface VisitorService extends BaseService<Visitor> {
    Integer getVisitorCount();
    List<VisitorDto> getVisitorDetails();
    List<Visitor> getByKey(String keyWord);
}
