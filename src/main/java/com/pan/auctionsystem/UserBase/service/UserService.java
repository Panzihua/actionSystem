package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionUserDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.AuctionUserInfo;
import com.pan.auctionsystem.model.AuctionUserPackage;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserService {

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Getter @Setter
    private RedisTemplate template;

    @Resource(name = "auctionUserDao")
    private AuctionUserDao dao;


    public boolean signInService(AuctionUser user, String ip){
        AuctionUser rightUser = dao.findOneByAccount(user.getUserAccount());

        if ( rightUser== null || !user.getUserPassword().equals(rightUser.getUserPassword())){
            return false;
        }else{
            this.putUserInRedis(rightUser, ip);
            return true;
        }
    }

    public void putUserInRedis(AuctionUser user, String ip){
        BoundHashOperations<String, String, String> userList = template.boundHashOps("userList");

        //userList放置account ip对, 再放置String ip userId对
        userList.put(user.getUserAccount(), ip);
        template.opsForValue().set(ip, user.getUserId());
    }

    public void signUpService(AuctionUserPackage userPackage){
        System.out.println(dao.addAuctionUserByModel(userPackage.getUser()));
        dao.addAuctionUserInfoByModel(userPackage.getUserInfo());
    }
}
