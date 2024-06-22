package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.example.coffeeshopwebsite.service.FileUploadService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class ProductController {
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;

    private final CategoryService categoryService;
    private final FileUploadService fileUploadService;

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService, FileUploadService fileUploadService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.fileUploadService = fileUploadService;
    }

    // CREATE
    @GetMapping("/product-form")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        logger.info("Access successfully");
        return "admin/productForm";
    }

    @PostMapping("/product-save")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file, @RequestParam("category") Long categoryId, @RequestParam(value = "productId", required = false) Long productId) {
        fileUploadService.handleImageUpload(product, file);
        productService.saveProduct(product, categoryId, productId);
        logger.info("Saved product successfully");
        return "redirect:/admin/products";
    }

    // READ
    @GetMapping("/products")
    public String index(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        logger.info("Access successfully");
        return "admin/products";
    }

    // UPDATE
    @GetMapping("/product-update")
    public String updateProduct(Model model, @RequestParam Long id) {
        Product product = productService.getProductById(id);
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("product", product);
        return "admin/updateProductForm";
    }

    // DELETE
    @GetMapping("/product-delete")
    public String deleteProduct(@RequestParam Long id) {
      productService.deleteProductById(id);
      return "redirect:/admin/products";
    }
}
