package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.UserService;
import com.pan.auctionsystem.model.AuctionUser;
import com.pan.auctionsystem.model.AuctionUserPackage;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller("userController")
@RequestMapping("/auctionSystem")
public class LoginSignUpController {

    @Resource(name = "userService")
    private UserService userService;

    @PostMapping("/signIn")
    public String signIn(AuctionUser user, HttpServletRequest request){
        if (userService.signInService(user, request.getRemoteAddr())){
            return "redirect:getAllItem";
        }else{
            return "redirect:/Login.html";
        }
    }

    @PostMapping("/signUp")
    public String signUp(AuctionUserPackage userPackage){
        userService.signUpService(userPackage);

        return "login页面";
    }

    @GetMapping("toSignUp")
    public String toSignUp(){
        return "SignUp.html";
    }
}
