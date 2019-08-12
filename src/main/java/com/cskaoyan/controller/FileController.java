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
@RequestMapping("file")
public class FileController {
    @Autowired
    ServletContext context;
    //文件上传
    @RequestMapping("upload")
    @ResponseBody
    public Map fileUpload(MultipartFile file) throws IOException {
        String realPath = context.getRealPath("/WEB-INF/file" + file.getOriginalFilename());
        File newFile = new File(realPath);
        file.transferTo(newFile);
        HashMap<String, Object> map = new HashMap<>();
        int i = realPath.indexOf("\\file");
        String replace = realPath.substring(i + 5).replace("\\", "/");
        map.put("error",0);
        map.put("url",replace);
        return map;
    }

    //文件删除
    @RequestMapping("delete")
    @ResponseBody
    public Map fileDelete(String fileName){
        String realPath = context.getRealPath("WEB-INF" + fileName);
        File file = new File(realPath);
        file.delete();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("data","success");
        return hashMap;
    }

    //文件下载
    @RequestMapping("download")
    public String fileDownload(String fileName){
        return fileName;
    }
}
