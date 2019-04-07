package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.SubscribeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("subscribePageController")
@RequestMapping("/auctionSystem")
public class SubscribePageController {

    @Resource(name = "subscribeService")
    private SubscribeService subscribeService;

    @GetMapping("/getAllSubscribe")
    public String selectAll(HttpServletRequest request, Model model){
        model.addAttribute("itemList", subscribeService.selectSubscribeItem(request.getRemoteAddr()));
        model.addAttribute("back", "getAllSubscribe");

        return "ShowSubscribe";
    }
}
