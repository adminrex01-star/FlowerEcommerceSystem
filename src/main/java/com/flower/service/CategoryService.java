package com.flower.service;

import com.flower.po.Category;
import java.util.List;
import com.flower.util.PageResult;
public interface CategoryService {
    List<Category> getAllCategories();
    Category getById(Integer id);
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(Integer id);
    PageResult<Category> getCategoriesByPage(int pageNum, int pageSize);
}