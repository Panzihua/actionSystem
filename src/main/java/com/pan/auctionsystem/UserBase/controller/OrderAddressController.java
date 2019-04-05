package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AddressService;
import com.pan.auctionsystem.UserBase.service.OrderAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("orderAddressController")
public class OrderAddressController {

    @Resource(name = "orderAddressService")
    private OrderAddressService orderAddressService;

    //选择订单地址
    public String setOrderAddress(int orderId, String address){
        orderAddressService.setAddress(orderId, address);

        return "订单页面";
    }


    public String selectAll(HttpServletRequest request, Model model){
        model.addAttribute("orderList", orderAddressService.selectAll(request.getRemoteAddr()));

        return "订单页面";
    }
}
