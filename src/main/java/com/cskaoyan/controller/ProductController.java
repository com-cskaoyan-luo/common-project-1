package com.cskaoyan.controller;

import com.cskaoyan.bean.Custom;
import com.cskaoyan.bean.PageBean;
import com.cskaoyan.bean.Product;
import com.cskaoyan.service.ProductService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;

    //更新产品介绍
    @RequestMapping("update_note")
    @ResponseBody
    public Map updateNote(String productId,String note){
        int i = productService.updateNote(productId,note);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","订单要求更新失败！");}
        return map;
    }

    //根据productType模糊查询
    @RequestMapping("search_product_by_productType")
    @ResponseBody
    public PageBean<Product> searchProductByType(String searchValue, int page, int rows){
        List<Product> products = productService.searchProductByType(searchValue,page,rows);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        PageBean<Product> pageBean = new PageBean<>(products, pageInfo.getTotal());
        return pageBean;
    }

    //根据productName模糊查询
    @RequestMapping("search_product_by_productName")
    @ResponseBody
    public PageBean<Product> searchProductByName(String searchValue, int page, int rows){
        List<Product> products = productService.searchProductByName(searchValue,page,rows);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        PageBean<Product> pageBean = new PageBean<>(products, pageInfo.getTotal());
        return pageBean;
    }

    //根据productId模糊查询
    @RequestMapping("search_product_by_productId")
    @ResponseBody
    public PageBean<Product> searchProductById(String searchValue, int page, int rows){
        List<Product> products = productService.searchProductById(searchValue,page,rows);
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        PageBean<Product> pageBean = new PageBean<>(products, pageInfo.getTotal());
        return pageBean;
    }

    //批量删除产品
    @RequestMapping("delete_judge")
    @ResponseBody
    public String deleteJudge(){
        return "null";
    }

    @RequestMapping("delete_batch")
    @ResponseBody
    public Map deleteBatch(String ids){
        //将id按逗号分隔成数组
        String[] split = ids.split(",");
        //将分割后的id存到List中
        List<String> strings = Arrays.asList(split);
        int i = productService.deleteBatch(strings);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","删除失败！");}
        return map;
    }

    //跳转至客户编辑页面
    @RequestMapping("edit")
    public String edit(){
        return "product_edit";
    }

    @RequestMapping("add_judge")
    @ResponseBody
    public String addJudge(){
        return "null";
    }

    //跳转至产品新增页面
    @RequestMapping("add")
    public String add(){
        return "product_add";
    }

    //新增产品
    @RequestMapping("insert")
    @ResponseBody
    public Map insert(Product product){
        int i = productService.insertProduct(product);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","插入失败！");}
        return map;
    }

    //更新Product
    @RequestMapping("edit_judge")
    @ResponseBody
    public String editJudge(){
        return "null";
    }

    //更新单个Product
    @RequestMapping("update_all")
    @ResponseBody
    public Map updateAll(Product product){
        int i = productService.updateSingleProduct(product);
        Map<String, String> map = new HashMap<>();
        if(i == 1){
            map.put("status","200");}
        else {
            map.put("msg","提交失败！");}
        return map;
    }

    //查询所有产品信息进行回显
    @RequestMapping("get_data")
    @ResponseBody
    public List<Product> queryAllProduct(){
        List<Product> products = productService.queryAllProduct();
        return products;
    }
    //根据id查询单个产品进行回显
    @RequestMapping("get/{id}")
    @ResponseBody
    public Product queryProductById(@PathVariable String id){
        Product product = productService.queryProductById(id);
        return product;
    }

    //点产品管理的页面显示
    @RequestMapping("find")
    public String find(){
        return "product_list";
    }

    @ResponseBody
    @RequestMapping("list")
    public PageBean<Product> queryProduct(int page, int rows){
        List<Product> products = productService.queryProduct(page, rows);
        //查询到的数据给到PageInfo ，只需要把结果集给到该对象，获取分页信息
        // 就可以通过该对象get方法拿到总页数，总记录数，等等你想要的数据
        PageInfo<Product> pageInfo = new PageInfo<>(products);
        PageBean<Product> pageBean = new PageBean<>(products, pageInfo.getTotal());
        /*for (COrder cOrder : cOrders) {
            System.out.println(cOrder);
        }*/
        return pageBean;
    }


}
