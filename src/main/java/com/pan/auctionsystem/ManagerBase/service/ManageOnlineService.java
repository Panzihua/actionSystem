package com.pan.auctionsystem.ManagerBase.service;

import com.pan.auctionsystem.model.OnlineUserModel;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service("manageOnlineService")
public class ManageOnlineService {

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    public List selectAllUser(){
        Set<String> keySet = template.keys("ip_*");
        List<OnlineUserModel> list = new ArrayList<>();

        for (String key : keySet){
            OnlineUserModel model = new OnlineUserModel();

            model.setIp(key.substring(3));
            model.setUserId(template.opsForValue().get(key));

            list.add(model);
        }

        return list;
    }

    public void removeOnlineUser(String ip){
        template.delete("ip_" + ip);
    }
}
