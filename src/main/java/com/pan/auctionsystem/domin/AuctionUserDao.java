package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.AuctionUserInfo;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auctionUserDao")
public interface AuctionUserDao extends CRUDDao<AuctionUserInfo> {
    public AuctionUser findOneByAccount(@Param("account")String userAccount);
    public int addAuctionUserByModel(@Param("model")AuctionUser model);
    public int addAuctionUserInfoByModel(@Param("model")AuctionUserInfo userInfo);
    public AuctionUser findAccountById(@Param("userId") int userId);
}
