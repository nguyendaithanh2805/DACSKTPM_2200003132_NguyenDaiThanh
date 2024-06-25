package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;

public interface ProductCartService {
    void saveProductCart(Cart cart, Product product);
}
