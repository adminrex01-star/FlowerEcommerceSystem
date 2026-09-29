package com.flower.controller;

import com.flower.po.Product;
import com.flower.service.CategoryService;
import com.flower.service.ProductService;
import com.flower.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // 商品列表页（带分页、分类筛选、关键字搜索）
    @GetMapping("/")
    public String index(@RequestParam(defaultValue = "1") int pageNum,
                        @RequestParam(defaultValue = "8") int pageSize,
                        @RequestParam(required = false) String keyword,
                        @RequestParam(required = false) Integer categoryId,
                        Model model) {
        PageResult<Product> page = productService.getProductsByPage(pageNum, pageSize, keyword, categoryId);
        model.addAttribute("page", page);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("keyword", keyword);
        model.addAttribute("categoryId", categoryId);
        return "index";
    }

    // 商品详情
    @GetMapping("/product/detail")
    public String detail(@RequestParam Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product_detail";
    }
}