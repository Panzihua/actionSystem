package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class OfferAPrice {
    private int price;
    private String userName;
    private String userAccount;
    private int itemId;
    private String itemName;

    private String remark;

    public OfferAPrice setRemark(String remark){
        this.remark = remark;

        return this;
    }
}
