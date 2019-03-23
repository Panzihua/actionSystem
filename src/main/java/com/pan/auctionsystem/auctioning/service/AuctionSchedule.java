package com.pan.auctionsystem.auctioning.service;

import com.pan.auctionsystem.domin.ItemOrderDao;
import com.pan.auctionsystem.model.AuctionItemForRedis;
import com.pan.auctionsystem.domin.AuctionItemForRedisDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class AuctionSchedule {
    @Resource(name = "auctionItemForRedisDao")
    @Setter @Getter
    private AuctionItemForRedisDao auctionItemForRedisDao;

    @Resource(name = "itemOrderDao")
    private ItemOrderDao itemOrderDao;

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Setter @Getter
    private RedisTemplate template;


//    @Scheduled(fixedRate = 3600000)
    public void putActionScheduleInNextHour() {
        Long now = new Date().getTime();
        Long future = now  + 3600000;
        //调试特殊参数
        List<AuctionItemForRedis> list = auctionItemForRedisDao.selectItemDateTime2Redis(Long.valueOf(1), Long.valueOf(100));

        for (AuctionItemForRedis item : list){
            BoundHashOperations<String, String, String> hmOp = template.boundHashOps("item_" + item.getItemId() + "_" + item.getItemName());
            hmOp.put("startTime", item.getItemStartDate().toString());
            hmOp.put("endTime", item.getItemEndDate().toString());
            hmOp.put("itemId", String.valueOf(item.getItemId()));
            hmOp.put("itemPrice", String.valueOf(item.getItemStartingPrice()));

            //调试用
            String aaa = hmOp.get("startTime");
            String bbb = hmOp.get("endTime");

            System.out.println(aaa + "  " + bbb);
        }

    }

//    @Scheduled(fixedRate = 3600000)
    public void updateActioningFinalPriceSchedule(){
        Set<String> keySet = template.keys("item_*");
        Long now = new Date().getTime();

        for (String key : keySet){
            BoundHashOperations<String, String, String> hmOp = template.boundHashOps(key);
            Long endTime  = Long.valueOf(hmOp.get("endTime"));
            String userId = hmOp.get("userId");

            if (now > endTime){
                if (userId != null) {
                    itemOrderDao.changeItemStatus(Integer.parseInt(hmOp.get("itemId")));
                    itemOrderDao.addNewOrder(Integer.parseInt(userId),
                            Integer.parseInt(hmOp.get("itemId")), now,
                            Double.valueOf(hmOp.get("itemPrice")));
                }
                template.delete(key);
            }
        }
    }
}
