package com.cskaoyan.service;

import com.cskaoyan.bean.Product;

import java.util.List;

public interface ProductService {
    Product queryProductById(String id);

    List<Product> queryAllProduct();

    int updateSingleProduct(Product product);

    int insertProduct(Product product);

    int deleteBatch(List<String> strings);

    List<Product> searchProductById(String searchValue, int page, int rows);

    List<Product> searchProductByName(String searchValue, int page, int rows);

    List<Product> searchProductByType(String searchValue, int page, int rows);

    List<Product> queryProduct(int page, int rows);

    int updateNote(String productId, String note);
}
