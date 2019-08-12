package com.cskaoyan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PicController {
    @Autowired
    ServletContext context;

    //图片上传
    @RequestMapping("pic/upload")
    @ResponseBody
    //用MultipartFile这个类来接收文件
    public Map uploadPic(MultipartFile uploadFile) throws IOException {
        //将文件上传到指定地址
        String path = context.getRealPath("/WEB-INF/pic/" + uploadFile.getOriginalFilename());
        File file = new File(path);
        uploadFile.transferTo(file);
        HashMap<String, Object> map = new HashMap<>();
        int i = path.indexOf("\\pic");
        String replace = path.substring(i).replace("\\", "/");
        map.put("error",0);
        map.put("url",replace);
        return map;
    }

    //图片删除
    @RequestMapping("pic/delete")
    @ResponseBody
    public Map deletePic(String picName){
        String realPath = context.getRealPath("/WEB-INF" + picName);
        File file = new File(realPath);
        file.delete();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("data","success");
        return hashMap;
    }


}
