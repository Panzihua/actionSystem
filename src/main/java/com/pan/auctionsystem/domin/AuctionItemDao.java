package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository("auctionItemDao")
public interface AuctionItemDao extends CRUDDao<AuctionItem> {
    public void setDateForItem(@Param("itemId")int itemId, @Param("startDate")Long startDate,
                               @Param("endDate")Long endDate);
}
