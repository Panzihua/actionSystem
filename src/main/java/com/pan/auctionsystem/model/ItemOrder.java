package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class ItemOrder {
    private int orderId;
    private int userId;
    private int itemId;
    private String itemName;
    private String shopName;
    private Long orderCreateTime;
    private Long itemPrice;
    private String address;
    private int orderStatus;
}
