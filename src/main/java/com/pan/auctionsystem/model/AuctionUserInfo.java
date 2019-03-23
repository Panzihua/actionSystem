package com.pan.auctionsystem.model;

import lombok.Data;

@Data
public class AuctionUserInfo {
    private int userId;
    private String userName;
    private int userCredentialType;
    private String userCredentialNum;
    private int userSex;
    private Long userBirth;
    private int userAge;
    private Long userRegisteredDate;
    private int userAccountYear;
}
