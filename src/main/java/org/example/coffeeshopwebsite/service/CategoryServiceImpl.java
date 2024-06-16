package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(Category category) {
        if (category.getId() == null) {
            category.setName(category.getName());
            categoryRepository.save(category);
        } else {
            Optional<Category> optionalCategory = categoryRepository.findById(category.getId());
            if (optionalCategory.isPresent()) {
                Category existingCategory = optionalCategory.get();
                existingCategory.setName(category.getName());
                categoryRepository.save(existingCategory);
            } else
                throw new RuntimeException("Category not found for ID :: " + category.getId());
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
