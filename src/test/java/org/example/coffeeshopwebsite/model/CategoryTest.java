package org.example.coffeeshopwebsite.model;

import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = true)
class CategoryTest {

    @Autowired
    private CategoryRepository categoryRepository;
    private Category category;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("coffeeA");
        categoryRepository.save(category);
    }

    @Test
    @DisplayName("Category saved")
    public void saveCategoryTest() {
        List<Category> categories = categoryRepository.findAll();
        assertNotNull(categories);
        assertEquals(1,categories.size());
        Category saveCategory = categories.get(0);
        assertEquals("coffeeA", saveCategory.getName());
    }
}