package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.service.CRUDService;
import com.pan.auctionsystem.util.translate.TimeTranslation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service("auctionItemService")
public class AuctionItemService implements CRUDService<AuctionItem> {

    @Resource(name = "auctionItemDao")
    private AuctionItemDao dao;

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate template;

    @Autowired
    private TimeTranslation translation;

    @Override
    public List<AuctionItem> selectAll() {
        List<AuctionItem> list = dao.selectAll();
        Long now = new Date().getTime();

        for (AuctionItem item : list){
            item.setAuctioning(false);

            if (item.getItemStartDate() != null) {
                if (item.getItemStartDate() <= now && item.getItemEndDate() >= now)
                    item.setAuctioning(true);
            }
        }

        return list;
    }

    @Override
    public List<AuctionItem> selectByCondition(AuctionItem model) {
        return dao.selectByCondition(model);
    }

    @Override
    public AuctionItem findOneById(int id, String ip) {
        AuctionItem result = dao.findOneById(id);
        Integer subscribe = dao.wasSubscribed(Integer.parseInt(template.opsForValue().get("ip_" + ip).toString()), id);

        if (subscribe == null) result.setWasSubscribe(0);
        else result.setWasSubscribe(1);

        Long now = new Date().getTime();

        result.setAuctioning(false);

        if (result.getItemStartDate() != null && result.getItemEndDate() !=null) {
            if (result.getItemStartDate() <= now && result.getItemEndDate() >= now) result.setAuctioning(true);
        }

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
        model.setItemShelfDate(new Date().getTime());
        return dao.addOneByModel(model);
    }

    public List<AuctionItem> selectAllByShopId(String ip){
        int shopId = Integer.parseInt(template.opsForValue().get("ip_" + ip).toString());

        List<AuctionItem> list = dao.selectAllByShopId(shopId);
        Long now = new Date().getTime();

        for (AuctionItem item : list){
            item.setAuctioning(false);

            if (item.getItemStartDate() != null)
                if (item.getItemStartDate() <= now && item.getItemEndDate() >= now) item.setAuctioning(true);
        }
        return list;
    }

    public void setDate(int itemId, String startTime, String endTime){
        startTime = startTime.replace("T", " ");
        endTime = endTime.replace("T", " ");

        dao.setDateForItem(itemId, translation.StringDateTime2Long(startTime), translation.StringDateTime2Long(endTime));
    }

}
