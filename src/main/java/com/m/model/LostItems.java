package com.m.model;

import java.util.Date;

public class LostItems {
    private Integer lostItemsId;
    private Date lostItemsDate;
    private String lostItemsDescribe;
    private Integer lostItemsStatus;

    public Integer getLostItemsId() {
        return lostItemsId;
    }

    public void setLostItemsId(Integer lostItemsId) {
        this.lostItemsId = lostItemsId;
    }

    public Date getLostItemsDate() {
        return lostItemsDate;
    }

    public void setLostItemsDate(Date lostItemsDate) {
        this.lostItemsDate = lostItemsDate;
    }

    public String getLostItemsDescribe() {
        return lostItemsDescribe;
    }

    public void setLostItemsDescribe(String lostItemsDescribe) {
        this.lostItemsDescribe = lostItemsDescribe;
    }

    public Integer getLostItemsStatus() {
        return lostItemsStatus;
    }

    public void setLostItemsStatus(Integer lostItemsStatus) {
        this.lostItemsStatus = lostItemsStatus;
    }
}
