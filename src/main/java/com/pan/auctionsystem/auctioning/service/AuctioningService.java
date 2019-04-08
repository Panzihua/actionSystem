package com.pan.auctionsystem.auctioning.service;

import com.pan.auctionsystem.domin.AuctionUserDao;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.OfferAPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundKeyOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("auctioningService")
public class AuctioningService {


    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @Resource(name = "auctionUserDao")
    private AuctionUserDao userDao;

    public OfferAPrice updateRedisItemPrice(OfferAPrice offerAPrice){

        BoundHashOperations<String, String,String> itemRedisOp = template.boundHashOps("item_" + offerAPrice.getItemId() + "_" + offerAPrice.getItemName());
        int price = Integer.parseInt(itemRedisOp.get("itemPrice"));
        String ip = template.opsForValue().get(offerAPrice.getUserAccount());

        if (price < offerAPrice.getPrice()){
            String userId = template.opsForValue().get(ip);
            System.out.println(template.opsForValue().get("ip_" + ip));
            System.out.println(userId);
            itemRedisOp.put("userId", userId);
            itemRedisOp.put("itemPrice", String.valueOf(offerAPrice.getPrice()));

            return offerAPrice;
        }else{
            return null;
        }
    }

    public AuctionUser signUpInRedis(String ip){
        int userId = Integer.parseInt(template.opsForValue().get("ip_" + ip).toString());

        AuctionUser user = userDao.findAccountById(userId);

        template.opsForValue().set(user.getUserAccount(), "ip_" + ip);

        return user;
    }

}
