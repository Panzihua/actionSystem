package com.pan.auctionsystem.auctioning.service;

import com.pan.auctionsystem.model.OfferAPrice;
import com.pan.auctionsystem.model.UpdateAuctionPageModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service("auctioningService")
public class AuctioningService {

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Getter @Setter
    private RedisTemplate template;

    private BoundHashOperations<String, String,String> userList;

    public OfferAPrice updateRedisItemPrice(OfferAPrice offerAPrice){
        userList = template.boundHashOps("userList");

        BoundHashOperations<String, String,String> itemRedisOp = template.boundHashOps("item_" + offerAPrice.getItemId() + "_" + offerAPrice.getItemName());
        double price = Double.valueOf(itemRedisOp.get("itemPrice"));

        if (price < offerAPrice.getPrice()){
            String userId = template.opsForValue().get(userList.get(offerAPrice.getUserAccount())).toString();
            itemRedisOp.put("userId", userId);
            itemRedisOp.put("itemPrice", String.valueOf(offerAPrice.getPrice()));

            return offerAPrice;
        }else{
            return null;
        }



    }

}
