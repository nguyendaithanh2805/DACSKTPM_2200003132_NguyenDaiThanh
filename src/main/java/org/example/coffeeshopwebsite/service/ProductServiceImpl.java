package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Category;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.repository.CategoryRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product, Long categoryId, Long productId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found for ID ::" + categoryId));
        if (productId == null) {
            product.setName(product.getName());
            product.setDiscount(product.getDiscount());
            product.setImage(product.getImage());
            product.setQuantity(product.getQuantity());
            product.setSellingPrice(product.getSellingPrice());
            product.setCategory(category);
            product.setAccount(product.getAccount());
            productRepository.save(product);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(productId);
            if (optionalProduct.isPresent()) {
                Product existingProduct = getExistingProduct(product, optionalProduct, category);
                productRepository.save(existingProduct);
            } else
                throw new RuntimeException("Product not found for ID ::" + product.getId());
        }
    }

    private Product getExistingProduct(Product product, Optional<Product> optionalProduct, Category category) {
        Product existingProduct = optionalProduct.get();
        existingProduct.setName(product.getName());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImage(product.getImage());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setSellingPrice(product.getSellingPrice() - product.getDiscount());
        existingProduct.setCategory(category);
        existingProduct.setAccount(product.getAccount());
        return existingProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not founded ID :: " + id));
    }

    @Override
    @Transactional
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
