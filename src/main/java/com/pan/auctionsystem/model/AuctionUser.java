package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class AuctionUser {
    private int userId;
    private String userAccount;
    private String userPassword;
    private int isShop;
    private String userName;
}
