package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class OfferAPrice {
    private double price;
    private String userName;
    private String userAccount;
    private int itemId;
    private String itemName;
}
