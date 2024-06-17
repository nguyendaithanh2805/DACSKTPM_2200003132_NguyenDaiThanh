package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class CategoryController {
    private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    // CREATE
    @GetMapping("/category-form")
    public String index(Model model) {
        model.addAttribute("category", new Category());
        return "admin/categoryForm";
    }

    @PostMapping("/category-save")
    public String saveCategory(@ModelAttribute("category") Category category) {
        categoryService.saveCategory(category);
        logger.info("Category saved successfully");
        return "redirect:/admin/categories";
    }

    //READ
    @GetMapping("/categories")
    public String getAllCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "admin/categories";
    }

    //UPDATE
    @GetMapping("/category-update")
    public String updateCategory(Model model, @RequestParam Long id) {
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category", category);
        return "admin/updateCategoryForm";
    }

    //DELETE
    @GetMapping("/category-delete")
    public String deleteCategory(@RequestParam Long id) {
        categoryService.deleteCategoryById(id);
        logger.info("Category deleted successfully");
        return "redirect:/admin/categories";
    }
}
