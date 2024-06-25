package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;

import java.util.List;

public interface CartService {
    void addProductToCart(Integer quantity, Long productId);

    List<Cart> getCartByUser();
}
