package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class UpdateAuctionPageModel {
    private String userName;
    private double price;

    public UpdateAuctionPageModel(String userName, double price) {
        this.userName = userName;
        this.price = price;
    }
}
