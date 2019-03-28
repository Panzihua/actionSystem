package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AuctionItemService;
import com.pan.auctionsystem.UserBase.service.SubscribeService;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionSubscribeModel;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller("userOpsItemController")
public class UserOpsItemController implements CRUDController<AuctionItem> {

    @Resource(name = "auctionItemService")
    private AuctionItemService auctionItemService;

    @Resource(name = "subscribeService")
    private SubscribeService subscribeService;

    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("itemList", auctionItemService.selectAll());

        return "查询页面"; //首次加载
    }

    //Json异步请求
    @Override
    public List<AuctionItem> selectByCondition(AuctionItem condition) {
        return auctionItemService.selectByCondition(condition);
    }

    //click进去查看详情
    @Override
    public String findOneById(Model model, int itemId, HttpServletRequest request) {
        model.addAttribute("auctionItem", auctionItemService.findOneById(itemId, request.getRemoteAddr()));

        return "详情页面";
    }

    //订阅
    public String subscribeItem(HttpServletRequest request ,int itemId){
        AuctionSubscribeModel model = new AuctionSubscribeModel();
        model.setItemId(itemId);

        subscribeService.addOneByModel(model, request.getRemoteAddr());
        return "消息";
    }

    //取消订阅
    public String cancelSubscribe(HttpServletRequest request, int itemId){
        subscribeService.deleteOneByUserIdNItemId(request.getRemoteAddr(), itemId);

        return "消息";
    }

    @Override
    public String updateOneByModel(AuctionItem model) {
        return null;
    }

    @Override
    public String deleteOneById(int modelId) {
        return null;
    }

    @Override
    public String addOneByModel(AuctionItem model) {
        return null;
    }
}
