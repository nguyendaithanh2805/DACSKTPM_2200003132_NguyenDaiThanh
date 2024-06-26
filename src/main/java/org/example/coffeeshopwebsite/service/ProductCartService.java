package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.model.User;

import java.util.List;

public interface ProductCartService {
    void saveProductCart(Cart cart, Product product, Integer quantity);
    List<ProductCart> getAllProductByUser();
}
