package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AuctionItemService;
import com.pan.auctionsystem.domin.AuctionItemDao;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("manageItemController")
public class ManageItemController implements CRUDController<AuctionItem> {

    @Resource(name = "auctionItemService")
    private AuctionItemService auctionItemService;

    @Override
    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("itemList", auctionItemService.selectAllByShopId(request.getRemoteAddr()));

        return "查询物品首页";
    }

    @Override
    public List<AuctionItem> selectByCondition(AuctionItem condition) {
        return null;
    }

    @Override
    public String findOneById(Model model, int itemId, HttpServletRequest request) {
        model.addAttribute("auctionItem", auctionItemService.findOneById(itemId, request.getRemoteAddr()));

        return "更新页面";
    }

    @Override
    public String updateOneByModel(AuctionItem model) {
        auctionItemService.updateOneByModel(model);

        return "查询页面";
    }

    @Override
    public String deleteOneById(int modelId) {
        auctionItemService.deleteOneById(modelId);

        return "查询页面"; //或者Json加载
    }

    @Override
    public String addOneByModel(AuctionItem model) {
        auctionItemService.updateOneByModel(model);

        return "查询页面";
    }

    public String setItemDate(int itemId, Long startTime, Long endTime){
        auctionItemService.setDate(itemId, startTime, endTime);

        return "查询页面";
    }
}
