package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.SubscribeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("subscribePageController")
public class SubscribePageController {

    @Resource(name = "subscribeService")
    private SubscribeService subscribeService;

    //已订阅列表
    public String selectAll(HttpServletRequest request, Model model){
        model.addAttribute("itemList", subscribeService.selectSubscribeItem(request.getRemoteAddr()));

        return "订阅列表页面";
    }
}
