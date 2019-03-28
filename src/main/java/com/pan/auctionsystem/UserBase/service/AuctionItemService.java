package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.service.CRUDService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("auctionItemService")
public class AuctionItemService implements CRUDService<AuctionItem> {

    @Resource(name = "auctionItemDao")
    private AuctionItemDao dao;

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Getter @Setter
    private RedisTemplate template;

    @Override
    public List<AuctionItem> selectAll() {
        return dao.selectAll();
    }

    @Override
    public List<AuctionItem> selectByCondition(AuctionItem model) {
        return dao.selectByCondition(model);
    }

    @Override
    public AuctionItem findOneById(int id, String ip) {
        AuctionItem result = dao.findOneById(id);
        Integer subscribe = dao.wasSubscribed(Integer.parseInt(template.opsForValue().get(ip).toString()), id);

        if (subscribe == null) result.setWasSubscribe(0);
        else result.setWasSubscribe(1);

        return result;
    }

    @Override
    public int updateOneByModel(AuctionItem model) {
        return dao.updateOneByModel(model);
    }

    @Override
    public int deleteOneById(int id) {
        return dao.deleteOneById(id);
    }

    @Override
    public int addOneByModel(AuctionItem model) {
        return addOneByModel(model);
    }

    public List<AuctionItem> selectAllByShopId(String ip){
        int shopId = Integer.parseInt(template.opsForValue().get(ip).toString());

        return dao.selectAllByShopId(shopId);
    }

    public void setDate(int itemId, Long startTime, Long endTime){
        dao.setDateForItem(itemId, startTime, endTime);
    }

}
