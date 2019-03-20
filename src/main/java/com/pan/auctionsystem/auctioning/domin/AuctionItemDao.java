package com.pan.auctionsystem.auctioning.domin;

import com.pan.auctionsystem.model.AuctionItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auctionItemDao")
public interface AuctionItemDao {
    public List selectItemByCondition(AuctionItem auctionItem);
    public List<AuctionItem> selectItemDateTime2Redis(@Param("startTime")Long startTime, @Param("endTime")Long endTime);
}
