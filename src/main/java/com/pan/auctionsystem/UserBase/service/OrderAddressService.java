package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.ItemOrderDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("orderAddressService")
public class OrderAddressService {

    @Resource(name = "itemOrderDao")
    private ItemOrderDao itemOrderDao;

    public int setAddress(int orderId, String address){
        return itemOrderDao.updateAddressById(orderId, address);
    }

}
