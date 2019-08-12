package com.cskaoyan.controller.unqualitycontroller;

import com.cskaoyan.bean.Product;

import com.cskaoyan.bean.ProductExample;
import com.cskaoyan.service.unqualifyapply.UnqualifyApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    UnqualifyApply unqualifyApply;
    @RequestMapping("/get/{id}")
    @ResponseBody
    public Product queryproduct(@PathVariable("id")String id){
        return  unqualifyApply.queeryproduct(id);
    }
    @RequestMapping("/get_data")
    @ResponseBody
    public List<Product> queryproduct1(){
        ProductExample example = new ProductExample();
        example.createCriteria().andProductIdIsNotNull();
        List<Product> products = unqualifyApply.queryProductList(example);
        return products;
    }
}
