package com.pan.auctionsystem.auctioning.service;

import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.domin.AuctionItemDao;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AuctionSchedule {
    @Resource(name = "auctionItemDao")
    @Setter @Getter
    private AuctionItemDao dao;

    @Autowired
    @Qualifier("redisTemplate")
    @Setter @Getter
    private RedisTemplate template;


    @Scheduled(fixedRate = 3600000)
    public void putActionScheduleInNextHour() {
        Long now = new Date().getTime();
        Long future = now  + 3600000;
        //调试特殊参数
        List<AuctionItem> list = dao.selectItemDateTime2Redis(Long.valueOf(1), Long.valueOf(100));

        for (AuctionItem item : list){
            BoundHashOperations<String, String, String> hmOp = template.boundHashOps(item.getItemId() + item.getItemName());
            hmOp.put("startTime", item.getItemStartDate().toString());
            hmOp.put("endTime", item.getItemEndDate().toString());
            hmOp.expire(Long.valueOf(1), TimeUnit.HOURS);

            //调试用
            String aaa = hmOp.get("startTime");
            String bbb = hmOp.get("endTime");

            System.out.println(aaa + "  " + bbb);
        }

    }
}
