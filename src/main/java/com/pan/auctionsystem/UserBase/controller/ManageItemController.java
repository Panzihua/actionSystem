package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AuctionItemService;
import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("manageItemController")
@RequestMapping("/auctionSystem")
public class ManageItemController implements CRUDController<AuctionItem> {

    @Resource(name = "auctionItemService")
    private AuctionItemService auctionItemService;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate template;

    @GetMapping("/getAllShopItem")
    @Override
    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("itemList", auctionItemService.selectAllByShopId(request.getRemoteAddr()));

        return "ShopItemList";
    }

    @Override
    public List<AuctionItem> selectByCondition(AuctionItem condition) {
        return null;
    }

    @GetMapping("/shopItemDetail")
    @Override
    public String findOneById(Model model, int itemId, HttpServletRequest request) {
        model.addAttribute("item", auctionItemService.findOneById(itemId, request.getRemoteAddr()));

        return "ManageItemDetail";
    }

    @PostMapping("/updateShopItem")
    @Override
    public String updateOneByModel(AuctionItem model) {
        auctionItemService.updateOneByModel(model);

        return "redirect:getAllShopItem";
    }

    @GetMapping("/deleteShopItem")
    @Override
    public String deleteOneById(int itemId) {
        auctionItemService.deleteOneById(itemId);

        return "redirect:getAllShopItem";
    }

    @PostMapping("/addItem")
    @Override
    public String addOneByModel(AuctionItem model) {
        auctionItemService.addOneByModel(model);

        return "redirect:getAllShopItem";
    }

    @GetMapping("/setItemDate")
    public String setItemDate(int itemId, String startTime, String endTime){
        auctionItemService.setDate(itemId, startTime, endTime);

        return "redirect:getAllShopItem";
    }

    @GetMapping("/toAddItem")
    public String toAdd(HttpServletRequest request, Model model){
        int shopId = Integer.parseInt(template.opsForValue().get(request.getRemoteAddr()));

        model.addAttribute("shopId", shopId);

        return "AddItem";
    }

    @GetMapping("/toSetDate")
    private String toSetDate(int itemId, Model model){
        model.addAttribute("itemId", itemId);

        return "SetDate";
    }
}
