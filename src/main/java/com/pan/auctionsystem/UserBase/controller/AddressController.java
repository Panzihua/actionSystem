package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AddressService;
import com.pan.auctionsystem.model.AuctionUserAddress;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("addressController")
@RequestMapping("/auctionSystem")
public class AddressController{

    @Resource(name = "addressService")
    private AddressService addressService;

    @GetMapping("/getAllAddress")
    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("addressList",
                addressService.selectAll(request.getRemoteAddr()));

        return "AddressList";
    }

    @GetMapping("/deleteAddress")
    public String deleteOneById(@RequestParam int modelId) {
        addressService.deleteOneById(modelId);

        return "redirect:getAllAddress";
    }

    @PostMapping("/addAddress")
    public String addOneByModel(AuctionUserAddress model, HttpServletRequest request) {
        addressService.addOneByModel(model, request.getRemoteAddr());

        return "redirect:getAllAddress";
    }

    @PostMapping("/updateAddress")
    public String updateOneByModel(AuctionUserAddress model, HttpServletRequest request){
        addressService.updateOneByModel(model, request.getRemoteAddr());

        return "redirect:getAllAddress";
    }

    @GetMapping("/toAddAddress")
    public String toAdd(){
        return "AddAddress";
    }

    @GetMapping("/toUpdateAddress")
    public String toUpdateAddress(Model model, @RequestParam int modelId){
        model.addAttribute("address", addressService.findOneById(modelId));
        return "UpdateAddress";
    }
}
