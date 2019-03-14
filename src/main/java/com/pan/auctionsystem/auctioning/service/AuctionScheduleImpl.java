package com.pan.auctionsystem.auctioning.service;

import com.pan.auctionsystem.util.domin.AuctionItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.Resource;

public class AuctionScheduleImpl {
    @Resource(name = "auctionItemDao")
    private AuctionItemDao dao;

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate template;

    @Scheduled(fixedRate = 3600000)
    public static void getActionScheduleInNextHour() {
        Long now = System.currentTimeMillis() - 600000;

    }
}
