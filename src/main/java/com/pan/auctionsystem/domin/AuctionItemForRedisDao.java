package com.pan.auctionsystem.domin;

import com.pan.auctionsystem.model.AuctionItemForRedis;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("auctionItemForRedisDao")
public interface AuctionItemForRedisDao {
    public List selectItemByCondition(AuctionItemForRedis auctionItemForRedis);
    public List<AuctionItemForRedis> selectItemDateTime2Redis(@Param("startTime")Long startTime, @Param("endTime")Long endTime);
    public List<AuctionItemForRedis> selectItemDateTime2RedisForTest(@Param("endTime") Long endTime);
}
