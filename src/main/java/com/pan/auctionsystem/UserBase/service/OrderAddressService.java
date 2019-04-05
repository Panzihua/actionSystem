package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.ItemOrderDao;
import com.pan.auctionsystem.model.ItemOrder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("orderAddressService")
public class OrderAddressService {

    @Resource(name = "itemOrderDao")
    private ItemOrderDao itemOrderDao;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    public int setAddress(int orderId, String address){
        return itemOrderDao.updateAddressById(orderId, address);
    }

    public List<ItemOrder> selectAll(String ip){
        int userId = Integer.parseInt(template.opsForValue().get(ip));

        return itemOrderDao.selectAll(userId);
    }

}
