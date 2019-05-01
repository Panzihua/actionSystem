package com.pan.auctionsystem.ManagerBase.controller;

import com.pan.auctionsystem.ManagerBase.service.ManageOnlineService;
import com.pan.auctionsystem.model.OnlineUserModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.List;

@Controller("manageOnlineController")
@RequestMapping("/Manager")
public class ManageOnlineController {

    @Resource(name = "manageOnlineService")
    private ManageOnlineService service;

    @GetMapping("/getAllOnlineUser")
    public String selectAll(Model model){
        model.addAttribute("userList", service.selectAllUser());

        return "OnlineUserList";
    }

    @GetMapping("/offUser")
    public String deleteOnlineUser(String ip){
        service.removeOnlineUser(ip );

        return "redirect:getAllOnlineUser";
    }
}
