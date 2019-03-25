package com.pan.auctionsystem.domin;


import com.pan.auctionsystem.model.AuctionUserAddress;
import com.pan.auctionsystem.util.myInterface.domin.CRUDDao;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auctionUserAddressDao")
public interface AuctionUserAddressDao extends CRUDDao<AuctionUserAddress> {
    public List<AuctionUserAddress> selectAllByUserId(@Param("modelId")int userId);
}
