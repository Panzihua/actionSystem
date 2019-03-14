package com.pan.auctionsystem.util.domin;

import com.pan.auctionsystem.auctioning.model.AuctionItem;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auctionItemDao")
public interface AuctionItemDao {
    public List selectItemByCondition(AuctionItem auctionItem);
}
