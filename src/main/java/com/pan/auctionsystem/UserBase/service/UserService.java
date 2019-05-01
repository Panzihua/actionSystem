package com.pan.auctionsystem.UserBase.service;

import com.pan.auctionsystem.domin.AuctionUserDao;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.AuctionUserInfo;
import com.pan.auctionsystem.model.AuctionUserPackage;
import com.pan.auctionsystem.util.translate.TimeTranslation;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service("userService")
public class UserService {

    @Autowired
    @Qualifier("stringRedisTemplate")
    @Getter @Setter
    private RedisTemplate template;

    @Resource(name = "auctionUserDao")
    private AuctionUserDao dao;

    @Autowired
    private TimeTranslation translation;


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
        template.opsForValue().set("ip_" + ip, String.valueOf(user.getUserId()));
        template.expire("ip_" + ip, 1, TimeUnit.HOURS);
    }

    public void signUpService(AuctionUserPackage userPackage){
        //转换
        userPackage.getUserInfo().setUserBirth(translation.StringDate2Long(userPackage.getUserInfo().getUserBirthString()));
        userPackage.getUserInfo().setUserAge(translation.countAge(userPackage.getUserInfo().getUserBirthString()));

        userPackage.getUserInfo().setUserRegisteredDate(new Date().getTime());

        dao.addAuctionUserByModel(userPackage.getUser());
        userPackage.getUserInfo().setUserId(userPackage.getUser().getUserId());
        dao.addAuctionUserInfoByModel(userPackage.getUserInfo());
    }

    public int updateUserInfoByModel(AuctionUserInfo userInfo, String ip){
        //取得userId
        int userId = Integer.parseInt(template.opsForValue().get("ip_" + ip).toString());
        userInfo.setUserId(userId);

        userInfo.setUserBirth(translation.StringDate2Long(userInfo.getUserBirthString()));
        userInfo.setUserAge(translation.countAge(userInfo.getUserBirthString()));

        return dao.updateOneByModel(userInfo);
    }

    public AuctionUserInfo findOneById(String ip){
        int userId = Integer.parseInt(template.opsForValue().get("ip_" + ip).toString());

        return dao.findOneById(userId);
    }
}
