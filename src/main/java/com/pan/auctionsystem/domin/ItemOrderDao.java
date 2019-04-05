package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.ItemOrder;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("itemOrderDao")
public interface ItemOrderDao {
    public int addNewOrder(@Param("userId")int userId, @Param("itemId")int itemId,
                           @Param("createTime")Long createTime, @Param("itemPrice")double itemPrice);
    public int changeItemStatus(@Param("itemId") int itemId);
    public int updateAddressById(@Param("modelId")int orderId, @Param("address")String address);
    public List<ItemOrder> selectAll(@Param("userId") int userId);
}
