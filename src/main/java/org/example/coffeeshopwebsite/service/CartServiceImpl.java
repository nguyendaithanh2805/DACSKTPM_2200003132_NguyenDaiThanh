package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.ProductCart;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CartRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final ProductCartService productCartService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductRepository productRepository, ProductCartService productCartService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productRepository = productRepository;
        this.productCartService = productCartService;
    }

    @Override
    public void addProductToCart(Integer quantity, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        User user = userService.getCurrentUser();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Cart cart = new Cart();
            cart.setUser(user);
            cart.setTotalBill(product.getSellingPrice() * quantity);
            product.setQuantity(product.getQuantity() - quantity);
            cartRepository.save(cart);
            logger.info("Cart save successfully");
            productRepository.save(product);
            logger.info("Product save successfully");
            productCartService.saveProductCart(cart, product, quantity);
        }
    }

    @Override
    public List<Cart> getCartByUser() {
        User user = userService.getCurrentUser();
        return cartRepository.findByUser(user);
    }
}
