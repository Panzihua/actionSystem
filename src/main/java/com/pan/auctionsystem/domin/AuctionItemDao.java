package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("auctionItemDao")
public interface AuctionItemDao extends CRUDDao<AuctionItem> {
    public void setDateForItem(@Param("itemId")int itemId, @Param("startDate")Long startDate,
                               @Param("endDate")Long endDate);
    public List<AuctionItem> selectAllSubscribeItem(@Param("userId")int userId);
    public List<AuctionItem> selectAllByShopId(@Param("shopId")int shopId);
    public Integer wasSubscribed(@Param("userId")int userId, @Param("itemId")int itemId);
}
