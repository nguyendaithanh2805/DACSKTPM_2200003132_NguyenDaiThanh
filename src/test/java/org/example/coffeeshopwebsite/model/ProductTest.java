package org.example.coffeeshopwebsite.model;

import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@Transactional
@Rollback(value = true)
class ProductTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;
    private Category category;
    private Product product;

    @BeforeEach
    void setUp() {
        category = new Category();
        category.setName("CoffeeA");
        category = categoryRepository.save(category);

        product = new Product();
        product.setName("Espresso");
        product.setDescription("Strong coffee");
        product.setDiscount(0.0);
        product.setImage("espresso.jpg");
        product.setQuantity(100);
        product.setSellingPrice(5.0);
        product.setCategory(category);
        product = productRepository.save(product);
    }

    @Test
    @DisplayName("Product saved")
    public void saveProductTest() {
        List<Product> products = productRepository.findAll();
        assertNotNull(products);
        assertEquals(1, products.size());
        Product saveProduct = products.get(0);
        assertEquals("Espresso", saveProduct.getName());
        assertEquals("Strong coffee", saveProduct.getDescription());
        assertEquals(0.0, saveProduct.getDiscount());
        assertEquals("espresso.jpg", saveProduct.getImage());
        assertEquals(100, saveProduct.getQuantity());
        assertEquals(5.0, saveProduct.getSellingPrice());
        assertEquals("CoffeeA", saveProduct.getCategory().getName());
    }
}