package com.pan.auctionsystem.ManagerBase.controller;

import com.pan.auctionsystem.ManagerBase.service.ManageOnlineService;
import com.pan.auctionsystem.model.OnlineUserModel;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;

@Controller("manageOnlineController")
public class ManageOnlineController {

    @Resource(name = "manageOnlineService")
    private ManageOnlineService service;

    //Json吧
    public List<OnlineUserModel> selectAll(){
        return service.selectAllUser();
    }

    //delete user后让JS再异步请求多次列表吧
    public void deleOnlineUser(String userAccount){
        service.removeOnlineUser(userAccount);
    }
}
