package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.COrder;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/order")
public class COrderController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/get/{id}")
    @ResponseBody
    public COrder queryAllOrder(@PathVariable("id")String id){
        COrder cOrder = unqualifyApply.queryAllOrder(id);
        return cOrder;
    }
    @RequestMapping("get_data")
    @ResponseBody
    public List<COrder> queryOrder(){
        List<COrder> cOrder=unqualifyApply.queryOrder();
        return cOrder;
    }
}
