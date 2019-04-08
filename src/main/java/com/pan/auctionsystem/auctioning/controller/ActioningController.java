package com.pan.auctionsystem.auctioning.controller;

import com.pan.auctionsystem.UserBase.service.AuctionItemService;
import com.pan.auctionsystem.auctioning.service.AuctioningService;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.OfferAPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ActioningController {

    @Autowired
    private SimpMessagingTemplate template; //后续可能需要

    @Autowired
    private AuctioningService service;

    @Autowired
    private AuctionItemService itemService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @MessageMapping("/offerAPrice")
    public void offerAPrice(@RequestBody OfferAPrice offerAPrice){

        offerAPrice = service.updateRedisItemPrice(offerAPrice);

        if (offerAPrice == null) return;
        else template.convertAndSend("/auctionSystem/auctioning/" + offerAPrice.getItemId() + "_" + offerAPrice.getItemName(), offerAPrice);
    }

    @GetMapping("/auctionSystem/toAuctioning")
    public String toAuctioning(int itemId, Model model, HttpServletRequest request){
        AuctionUser user = service.signUpInRedis(request.getRemoteAddr());


        model.addAttribute("user", user);

        AuctionItem item = itemService.findOneById(itemId, request.getRemoteAddr());
        BoundHashOperations<String, String, String> hmOp = redisTemplate.boundHashOps("item_" + item.getItemId() + "_" + item.getItemName());

        model.addAttribute("nowPrice", hmOp.get("itemPrice"));
        model.addAttribute("item", item);
        return "Auctioning";
    }

}
