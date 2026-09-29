package com.flower.service.impl;

import com.flower.mapper.ProductMapper;
import com.flower.po.Product;
import com.flower.service.ProductService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public PageResult<Product> getProductsByPage(int pageNum, int pageSize, String keyword, Integer categoryId) {
        int offset = (pageNum - 1) * pageSize;
        long total = productMapper.count(keyword, categoryId);
        List<Product> list = productMapper.selectByPage(offset, pageSize, keyword, categoryId);
        return new PageResult<>(pageNum, pageSize, total, list);
    }

    @Override
    public Product getProductById(Integer id) {
        return productMapper.selectById(id);
    }

    @Override
    public boolean addProduct(Product product) {
        product.setStatus(1);
        return productMapper.insert(product) > 0;
    }

    @Override
    public boolean updateProduct(Product product) {
        return productMapper.update(product) > 0;
    }

    @Override
    public boolean deleteProduct(Integer id) {
        return productMapper.deleteById(id) > 0;
    }
    @Override
    public List<Product> getAllProductsForExport() {
        return productMapper.selectAllForExport();
    }
}
