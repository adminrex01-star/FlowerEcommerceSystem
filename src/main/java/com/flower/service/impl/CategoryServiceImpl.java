package com.flower.service.impl;

import com.flower.mapper.CategoryMapper;
import com.flower.po.Category;
import com.flower.service.CategoryService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getAllCategories() {
        return categoryMapper.selectAll();
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public boolean addCategory(Category category) {
        return categoryMapper.insert(category) > 0;
    }

    @Override
    public boolean updateCategory(Category category) {
        return categoryMapper.update(category) > 0;
    }

    @Override
    public boolean deleteCategory(Integer id) {
        return categoryMapper.deleteById(id) > 0;
    }
    @Override
    public PageResult<Category> getCategoriesByPage(int pageNum, int pageSize) {
        int offset = (pageNum - 1) * pageSize;
        long total = categoryMapper.count();
        List<Category> list = categoryMapper.selectByPage(offset, pageSize);
        return new PageResult<>(pageNum, pageSize, total, list);
    }
}