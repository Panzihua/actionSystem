package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.AuctionSubscribeModel;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import org.springframework.stereotype.Repository;

@Repository("auctionItemSubscribeDao")
public interface AuctionItemSubscribeDao extends CRUDDao<AuctionSubscribeModel> {
}
