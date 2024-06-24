package org.example.coffeeshopwebsite.service;

import jakarta.servlet.http.HttpSession;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;

import java.util.List;

public interface CartService {
    void addProductToCart(Long productId, HttpSession session);

    List<Product> getCartItems(HttpSession session);
}
