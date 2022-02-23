package com.jenkins.hello.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class HelloController {


    @Value("${file.upload.path}")
    private String fileUploadPath;
    @Value("${file.down.path}")
    private String fileDownPath;

    @Getter
    @AllArgsConstructor
    public class Response{
        private String message;
    }


    @GetMapping("/")
    Response hello(){
        return new Response("hello");
    }


    @PostMapping("/file")
    Response setFile(MultipartFile file) throws Exception {

        String imageDownUrl=saveFile(file);
        return new Response("IMG URL: "+imageDownUrl);
    }

    String saveFile(MultipartFile file) throws Exception {
        String current_date = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String basePath = new File("").getAbsolutePath()+fileUploadPath;

        String[] imageFileFlags = file.getOriginalFilename().split("\\.");
        String imageExt=imageFileFlags[imageFileFlags.length-1];

        String imagePath = basePath + "image" + current_date + "." + imageExt;
        String imageDownUrl = fileDownPath + "image" + current_date + "." + imageExt;
        File dest = new File(imagePath);
        file.transferTo(dest);
        return imageDownUrl;
    }


}
