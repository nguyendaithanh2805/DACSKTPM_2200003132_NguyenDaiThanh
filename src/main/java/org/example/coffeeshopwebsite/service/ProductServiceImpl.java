package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        if (product.getId() == null) {
            product.setName(product.getName());
            product.setDiscount(product.getDiscount());
            product.setImage(product.getImage());
            product.setQuantity(product.getQuantity());
            product.setSellingPrice(product.getSellingPrice());
            product.setCategory(product.getCategory());
            product.setAccount(product.getAccount());
            productRepository.save(product);
        } else {
            Optional<Product> optionalProduct = productRepository.findById(product.getId());
            if (optionalProduct.isPresent()) {
                Product existingProduct = getExistingProduct(product, optionalProduct);
                productRepository.save(existingProduct);
            } else
                throw new RuntimeException("Product not found for ID ::" + product.getId());
        }
    }

    private Product getExistingProduct(Product product, Optional<Product> optionalProduct) {
        Product existingProduct = optionalProduct.get();
        existingProduct.setName(product.getName());
        existingProduct.setDiscount(product.getDiscount());
        existingProduct.setDescription(product.getDescription());
        existingProduct.setImage(product.getImage());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setSellingPrice(product.getSellingPrice() - product.getDiscount());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setAccount(product.getAccount());
        return existingProduct;
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not founded ID :: " + id));
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }
}
