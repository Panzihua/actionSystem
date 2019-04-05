package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class AuctionItem {
    private int itemId;
    private String itemName;
    private String itemFrom;
    private Long itemShelfDate;
    private Long itemStartDate;
    private Long itemEndDate;
    private int itemShopId;
    private String shopName;
    private int itemStartingPrice;
    private int itemIncreasePrice;
    private String itemPicture;

    private int wasSubscribe;
    boolean auctioning;
}
