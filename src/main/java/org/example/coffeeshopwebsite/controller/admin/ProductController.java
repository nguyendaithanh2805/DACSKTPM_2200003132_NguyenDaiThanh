package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.service.CategoryService;
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

    @Autowired
    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // Tao ham xu ly anh rieng de tranh vi pham nguyen tac "Single responsibility principle" trong SOLID
    private void handleImageUpload(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file) {
        String fileName = file.getOriginalFilename(); // Lay ten goc cua file tu HTML form
        String uploadDir =  "D:\\workspace\\DACS\\coffee-shop-website\\src\\main\\resources\\static\\user\\images"; // Tao duong dan de luu tru hinh anh
        Path uploadPath = Paths.get(uploadDir);
        try {
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName); // Ket hop duong dan uploadPath va fileName de tao nen path hoan chinh
            Files.copy(file.getInputStream(), filePath);
            product.setImage(fileName);
            logger.info("Image saved successfully");
        }  catch (Exception e) {
            logger.error("Failed to save image", e);
        }
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
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file, @RequestParam("category") Long categoryId) {
        handleImageUpload(product, file);
        productService.saveProduct(product, categoryId);
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
