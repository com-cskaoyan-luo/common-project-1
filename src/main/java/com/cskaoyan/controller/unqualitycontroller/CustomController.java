package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.Custom;
import com.cskaoyan.bean.CustomExample;
import com.cskaoyan.service.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/custom")
public class CustomController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("get_data")
    @ResponseBody
    public List<Custom> queryCustomList(){
        CustomExample example = new CustomExample();
        example.createCriteria().andCustomIdIsNotNull();
        List<Custom> customs = unqualifyApply.queryCustomList(example);
        return  customs;
    }
}
