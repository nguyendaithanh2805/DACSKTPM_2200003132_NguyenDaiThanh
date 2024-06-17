package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;

import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);

    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    void deleteCategoryById(Long id);
}
