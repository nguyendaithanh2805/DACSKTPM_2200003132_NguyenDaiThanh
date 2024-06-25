package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CartRepository;
import org.example.coffeeshopwebsite.repository.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService{
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productRepository = productRepository;
    }

    @Override
    public void addProductToCart(Integer quantity, Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        User user = userService.getCurrentUser();
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            Cart cart = new Cart();
            cart.setProducts(new ArrayList<>());
            cart.getProducts().add(product);
            cart.setUser(user);
            cart.setQuantity(quantity);
            cart.setTotalBill(product.getSellingPrice() * quantity);
            if(product.getCarts() == null) {
                product.setCarts(new ArrayList<>());
            }
            product.getCarts().add(cart);
            product.setQuantity(product.getQuantity() - quantity);
            cartRepository.save(cart);
            logger.info("Cart save successfully");
            productRepository.save(product);
            logger.info("Product save successfully");
        }
    }
}
