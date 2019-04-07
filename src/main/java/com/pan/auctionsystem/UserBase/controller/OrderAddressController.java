package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AddressService;
import com.pan.auctionsystem.UserBase.service.OrderAddressService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("orderAddressController")
@RequestMapping("/auctionSystem")
public class OrderAddressController {

    @Resource(name = "orderAddressService")
    private OrderAddressService orderAddressService;

    @Resource(name = "addressService")
    private AddressService addressService;

    @GetMapping("/setOrderAddress")
    public String setOrderAddress(int orderId, String address){
        orderAddressService.setAddress(orderId, address);

        return "redirect:getAllOrder";
    }

    @GetMapping("/getAllOrder")
    public String selectAll(HttpServletRequest request, Model model){
        model.addAttribute("orderList", orderAddressService.selectAll(request.getRemoteAddr()));

        return "OrderList";
    }

    @GetMapping("/toSetAddress")
    public String toSetOrderAddress(Model model, HttpServletRequest request, int orderId){
        model.addAttribute("addressList",
                addressService.selectAll(request.getRemoteAddr()));
        model.addAttribute("setAddressOrderId", orderId);

        return "SetAddress";
    }

}
