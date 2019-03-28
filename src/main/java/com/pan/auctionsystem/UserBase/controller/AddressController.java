package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AddressService;
import com.pan.auctionsystem.model.AuctionUserAddress;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("addressController")
public class AddressController{

    @Resource(name = "addressService")
    private AddressService addressService;

    //加载地址页面
    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("addressList",
                addressService.selectAll(request.getRemoteAddr()));

        return "地址页面";
    }

    //异步删除
    public String deleteOneById(int modelId) {
        addressService.deleteOneById(modelId);

        return "也不刷新地址页面";
    }

    //新增
    public String addOneByModel(AuctionUserAddress model, HttpServletRequest request) {
        addressService.addOneByModel(model, request.getRemoteAddr());

        return "地址页面";
    }

    public String updateOneByModel(AuctionUserAddress model, HttpServletRequest request){
        addressService.updateOneByModel(model, request.getRemoteAddr());

        return "地址页面";
    }
}
