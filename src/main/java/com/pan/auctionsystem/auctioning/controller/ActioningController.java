package com.pan.auctionsystem.auctioning.controller;

import com.pan.auctionsystem.auctioning.service.AuctioningService;
import com.pan.auctionsystem.model.OfferAPrice;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ActioningController {

    @Autowired
    @Getter @Setter
    private SimpMessagingTemplate template; //后续可能需要

    @Autowired
    @Getter @Setter
    private AuctioningService service;

    @MessageMapping("/increaseInPrice")
    public void offerAPrice(OfferAPrice offerAPrice){

        offerAPrice = service.updateRedisItemPrice(offerAPrice);

        if (offerAPrice == null) return;
        else template.convertAndSend("/auctioning/" + offerAPrice.getItemId() + "_" + offerAPrice.getItemName(), offerAPrice);

    }

}
