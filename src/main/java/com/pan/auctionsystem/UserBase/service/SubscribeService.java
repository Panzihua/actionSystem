package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.domin.AuctionItemSubscribeDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionSubscribeModel;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("subscribeService")
public class SubscribeService{

    @Resource(name = "auctionItemSubscribeDao")
    private AuctionItemSubscribeDao dao;

    @Resource(name = "auctionItemDao")
    private AuctionItemDao itemDao;

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate template;

    public int deleteOneByUserIdNItemId(String ip, int itemId) {
        return dao.deleteOneByUserIdNItemId(Integer.parseInt(template.opsForValue().get(ip).toString()), itemId);
    }


    public int addOneByModel(AuctionSubscribeModel model, String ip) {
        int userId = Integer.parseInt(template.opsForValue().get(ip).toString());

        model.setUserId(userId);
        return dao.addOneByModel(model);
    }

    public List<AuctionItem> selectSubscribeItem(String ip){
        return itemDao.selectAllSubscribeItem(Integer.parseInt(template.opsForValue().get(ip).toString()));
    }
}
