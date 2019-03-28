package com.pan.auctionsystem.domin;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("itemOrderDao")
public interface ItemOrderDao {
    public int addNewOrder(@Param("userId")int userId, @Param("itemId")int itemId,
                           @Param("createTime")Long createTime, @Param("itemPrice")double itemPrice);
    public int changeItemStatus(@Param("itemId") int itemId);
    public int updateAddressById(@Param("modelId")int orderId, @Param("address")String address);
}
