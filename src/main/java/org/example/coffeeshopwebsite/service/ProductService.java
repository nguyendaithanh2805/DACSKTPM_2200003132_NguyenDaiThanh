package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    void saveProduct(Product product, Long categoryId);

    Product getProductById(Long id);

    void deleteProductById(Long id);
}
