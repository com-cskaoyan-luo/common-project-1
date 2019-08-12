package com.cskaoyan.service.impl;

import com.cskaoyan.bean.Product;
import com.cskaoyan.bean.ProductExample;
import com.cskaoyan.mapper.ProductMapper;
import com.cskaoyan.service.ProductService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;
    ProductExample productExample = new ProductExample();

    //根据id回显单个产品
    @Override
    public Product queryProductById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Product> queryAllProduct() {
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }

    //可选择性地更新单个Product
    @Override
    public int updateSingleProduct(Product product) {
        int i = productMapper.updateByPrimaryKeySelective(product);
        return i;
    }

    //新增产品
    @Override
    public int insertProduct(Product product) {
        int i = productMapper.insert(product);
        return i;
    }

    //批量删除产品
    @Override
    public int deleteBatch(List<String> strings) {
        int i = 0;
        for (String string : strings) {
            i = productMapper.deleteByPrimaryKey(string);
        }
        return i;
    }

    //根据id模糊查询
    @Override
    public List<Product> searchProductById(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductIdLike(s);
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }

    //根据name模糊查询
    @Override
    public List<Product> searchProductByName(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductNameLike(s);
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }

    //根据type模糊查询
    @Override
    public List<Product> searchProductByType(String searchValue, int page, int rows) {
        PageHelper.startPage(page,rows);
        String s = "%" + searchValue + "%";
        ProductExample productExample = new ProductExample();
        productExample.createCriteria().andProductTypeLike(s);
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }

    //查询所有的product进行显示
    @Override
    public List<Product> queryProduct(int page, int rows) {
        PageHelper.startPage(page,rows);
        ProductExample productExample = new ProductExample();
        List<Product> products = productMapper.selectByExample(productExample);
        return products;
    }

    //更新产品介绍
    @Override
    public int updateNote(String productId, String note) {
        Product product = new Product();
        product.setProductId(productId);
        product.setNote(note);
        int i = productMapper.updateByPrimaryKeySelective(product);
        return i;
    }
}
