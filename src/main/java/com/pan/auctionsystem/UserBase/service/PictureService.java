package com.pan.auctionsystem.UserBase.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service("pictureService")
public class PictureService {

    private String filePath = "C:\\Users\\Administrator\\Desktop\\test\\";

    public void service(MultipartFile file){
        if (file.isEmpty()) {
            return;
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();

        // 解决中文问题,liunx 下中文路径,图片显示问题
        //fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath +  "\\" + fileName);

        try {
            file.transferTo(dest);
        } catch (IllegalStateException | IOException e) {
            e.printStackTrace();
            return;
        }
    }

    public boolean service (String fileName, HttpServletResponse response){

        //输出文件的完整路径
        File targetFile = new File(filePath + fileName);

        if (!targetFile.exists()) return false;
        response.setContentType("image/png");
        //写入内存再写入response，以流的形式传到前端
        try {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(targetFile));
            OutputStream outputStream = response.getOutputStream();
            byte[] temp = new byte[8 * 1024];

            while (bufferedInputStream.read(temp) != -1){
                outputStream.write(temp);
            }
            outputStream.flush();

            bufferedInputStream.close();
            outputStream.close();
        }catch (IOException e){
            e.printStackTrace();
            System.out.println("写入流出错啦");
            return false;
        }

        return true;
    }
}
