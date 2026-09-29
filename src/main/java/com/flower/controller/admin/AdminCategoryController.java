package com.flower.controller.admin;

import com.flower.po.Category;
import com.flower.service.CategoryService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminCategoryController {

    @Autowired
    private CategoryService categoryService;

    // 分类列表（分页）
    @GetMapping("/categories")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       Model model) {
        PageResult<Category> page = categoryService.getCategoriesByPage(pageNum, pageSize);
        model.addAttribute("page", page);
        return "admin/category_list";
    }

    // 新增分类页面
    @GetMapping("/category/add")
    public String addPage(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category_form";
    }

    // 编辑分类页面
    @GetMapping("/category/edit")
    public String editPage(@RequestParam Integer id, Model model) {
        Category category = categoryService.getById(id);
        model.addAttribute("category", category);
        return "admin/category_form";
    }

    // 保存（新增或修改）
    @PostMapping("/category/save")
    public String save(Category category) {
        if (category.getCategoryId() == null) {
            categoryService.addCategory(category);
        } else {
            categoryService.updateCategory(category);
        }
        return "redirect:/admin/categories";
    }

    // 删除分类
    @GetMapping("/category/delete")
    public String delete(@RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin/categories";
    }
}