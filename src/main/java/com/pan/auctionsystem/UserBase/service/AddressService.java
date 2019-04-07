package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionUserAddressDao;
import com.pan.auctionsystem.model.AuctionUserAddress;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("addressService")
public class AddressService{

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @Resource(name = "auctionUserAddressDao")
    private AuctionUserAddressDao addressDao;

    public List<AuctionUserAddress> selectAll(String ip) {
        return addressDao.selectAllByUserId(Integer.parseInt(template.opsForValue().get(ip)));
    }

    public int updateOneByModel(AuctionUserAddress model, String ip) {
        model.setUserId(Integer.parseInt(template.opsForValue().get(ip)));
        return addressDao.updateOneByModel(model);
    }

    public int deleteOneById(int id) {
        return addressDao.deleteOneById(id);
    }

    public int addOneByModel(AuctionUserAddress model, String ip) {
        model.setUserId(Integer.parseInt(template.opsForValue().get(ip)));
        return addressDao.addOneByModel(model);
    }

    public AuctionUserAddress findOneById(int modelId){
        return addressDao.findOneById(modelId);
    }
}
