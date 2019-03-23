package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class AuctionItemForRedis {
    int itemId;
    String itemName;
    String itemFrom;
    Long itemShelfDate;
    Long itemStartDate;
    Long itemEndDate;
    double itemStartingPrice;
    double itemIncreasePrice;
    String itemPicture;

    int itemShopId;
    String shopName;
}
