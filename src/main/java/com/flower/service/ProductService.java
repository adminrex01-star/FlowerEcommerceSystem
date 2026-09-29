package com.flower.service;

import com.flower.po.Product;
import com.flower.util.PageResult;
import java.util.List;

public interface ProductService {
    PageResult<Product> getProductsByPage(int pageNum, int pageSize, String keyword, Integer categoryId);
    Product getProductById(Integer id);
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Integer id);
    List<Product> getAllProductsForExport();   // 新增
}