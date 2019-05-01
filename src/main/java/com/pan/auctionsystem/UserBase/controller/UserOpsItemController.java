package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.AuctionItemService;
import com.pan.auctionsystem.UserBase.service.SubscribeService;
import com.pan.auctionsystem.UserBase.service.UserService;
import com.pan.auctionsystem.model.AuctionItem;
import com.pan.auctionsystem.model.AuctionSubscribeModel;
import com.pan.auctionsystem.model.AuctionUserInfo;
import com.pan.auctionsystem.util.myInterface.controller.CRUDController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller("userOpsItemController")
@RequestMapping("/auctionSystem")
public class UserOpsItemController implements CRUDController<AuctionItem> {

    @Resource(name = "auctionItemService")
    private AuctionItemService auctionItemService;

    @Resource(name = "subscribeService")
    private SubscribeService subscribeService;

    @Resource(name = "userService")
    private UserService userService;

    @GetMapping("/getAllItem")
    public String selectAll(Model model, HttpServletRequest request) {
        model.addAttribute("itemList", auctionItemService.selectAll());
        model.addAttribute("now", new Date().getTime());

        return "ShowItem"; //首次加载
    }

    @PostMapping("/selectItemByCondition")
    @ResponseBody
    @Override
    public List<AuctionItem> selectByCondition(@RequestBody AuctionItem condition) {
        List list = auctionItemService.selectByCondition(condition);
        return list;
    }

    @GetMapping("/detail")
    @Override
    public String findOneById(Model model, @RequestParam int itemId, HttpServletRequest request) {
        model.addAttribute("item", auctionItemService.findOneById(itemId, request.getRemoteAddr()));

        return "itemDetail";
    }

    @GetMapping("/subscribe")
    @ResponseBody
    public boolean subscribeItem(@RequestParam int itemId, HttpServletRequest request){
        AuctionSubscribeModel model = new AuctionSubscribeModel();
        model.setItemId(itemId);

        subscribeService.addOneByModel(model, request.getRemoteAddr());
        return true;
    }

    @GetMapping("/cancelSubscribe")
    @ResponseBody
    public boolean cancelSubscribe(@RequestParam int itemId, HttpServletRequest request){
        subscribeService.deleteOneByUserIdNItemId(request.getRemoteAddr(), itemId);

        return true;
    }

    @PostMapping("/updateUserInfo")
    public String updateUserInfo(AuctionUserInfo model, HttpServletRequest request){
        userService.updateUserInfoByModel(model, request.getRemoteAddr());

        return "redirect:toUpdateUserInfo";
    }

    @GetMapping("/toUpdateUserInfo")
    public String toUpdateUserInfo(HttpServletRequest request, Model model){
        model.addAttribute("userInfo", userService.findOneById(request.getRemoteAddr()));

        return "UserInfo";
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
