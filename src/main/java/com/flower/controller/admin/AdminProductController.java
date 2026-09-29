package com.flower.controller.admin;

import com.flower.po.Product;
import com.flower.service.CategoryService;
import com.flower.service.ProductService;
import com.flower.util.PageResult;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String adminIndex() {
        return "admin/index";
    }

    @GetMapping("/products")
    public String list(@RequestParam(defaultValue = "1") int pageNum,
                       @RequestParam(defaultValue = "10") int pageSize,
                       @RequestParam(required = false) String keyword,
                       Model model) {
        PageResult<Product> page = productService.getProductsByPage(pageNum, pageSize, keyword, null);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "admin/product_list";
    }

    @GetMapping("/product/add")
    public String addPage(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product_form";
    }

    @GetMapping("/product/edit")
    public String editPage(@RequestParam Integer id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product != null ? product : new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product_form";
    }

    @PostMapping("/product/save")
    public String save(Product product) {
        if (product.getProductId() == null) {
            productService.addProduct(product);
        } else {
            productService.updateProduct(product);
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/product/delete")
    public String delete(@RequestParam Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }

    // 导出商品列表到Excel
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws IOException {
        List<Product> productList = productService.getAllProductsForExport();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("商品列表");
        Row header = sheet.createRow(0);
        String[] columns = {"商品ID", "商品名称", "分类", "价格", "库存", "状态", "描述"};
        for (int i = 0; i < columns.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(columns[i]);
        }
        int rowNum = 1;
        for (Product p : productList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(p.getProductId());
            row.createCell(1).setCellValue(p.getProductName());
            row.createCell(2).setCellValue(p.getCategoryName() != null ? p.getCategoryName() : "");
            row.createCell(3).setCellValue(p.getPrice());
            row.createCell(4).setCellValue(p.getStock());
            row.createCell(5).setCellValue(p.getStatus() == 1 ? "上架" : "下架");
            row.createCell(6).setCellValue(p.getContent() != null ? p.getContent() : "");
        }
        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=products.xlsx");
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}