package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Product;
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

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // Tao ham xu ly anh rieng de tranh vi pham nguyen tac "Single responsibility principle" trong SOLID
    private void handleImageUpload(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file) {
        String fileName = file.getOriginalFilename(); // Lay ten goc cua file tu HTML form
        String uploadDir =  "D:\\workspace\\DACS\\coffee-shop-website\\src\\main\\resources\\static\\user\\images"; // Tao duong dan de luu tru hinh anh
        Path uploadPath = Paths.get(uploadDir);
        try {
            assert fileName != null;
            Path filePath = uploadPath.resolve(fileName); // Ket hop duong dan uploadPath va fileName de tao nen path hoan chinh

            if (!(Files.exists(filePath)))
                Files.copy(file.getInputStream(), filePath);
            product.setImage(fileName);
            logger.info("Image saved successfully");
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CREATE
    @GetMapping("/products/form")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
        logger.info("Access successfully");
        return "admin/productForm";
    }

    @PostMapping("product/save")
    public String saveProduct(@ModelAttribute("product") Product product, @RequestParam("imageFile") MultipartFile file) {
        handleImageUpload(product, file);
        productService.saveProduct(product);
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

    // DELETE



}
