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
    private double itemStartingPrice;
    private double itemIncreasePrice;
    private String itemPicture;
}
