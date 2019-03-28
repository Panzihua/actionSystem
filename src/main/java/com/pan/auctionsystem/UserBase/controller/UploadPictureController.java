package com.pan.auctionsystem.UserBase.controller;

import com.pan.auctionsystem.UserBase.service.PictureService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@Controller("uploadPictureController")
public class UploadPictureController {

    @Resource(name = "pictureService")
    private PictureService service;

    public void upload(@RequestParam("uploadOffice") MultipartFile file) {
        service.service(file);
    }

    public void returnPicture(String fileName, HttpServletResponse response) {
        service.service(fileName, response);
    }
}
