package org.example.coffeeshopwebsite.service;

import jakarta.servlet.http.HttpSession;
import org.example.coffeeshopwebsite.model.Cart;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService{
    private final Logger logger = LoggerFactory.getLogger(CartServiceImpl.class);
    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public CartServiceImpl(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    @Override
    public void addProductToCart(Long productId, HttpSession session) {
        try {
            User user = userService.getCurrentUser();
            Product product = productService.getProductById(productId);
            Cart cart = (Cart) session.getAttribute("cart");
            if (cart == null) {
                cart = new Cart();
                cart.setUser(user);
                cart.setProducts(new ArrayList<>());
            } else if (cart.getProducts() == null) {
                cart.setProducts(new ArrayList<>());
            }
            cart.setTotalBill(product.getSellingPrice() * product.getQuantity());
            cart.getProducts().add(product);
            session.setAttribute("cart", cart);
            cartRepository.save(cart);
            logger.info("Saved cart successful");
        } catch (Exception e) {
            System.out.println("e = " + e);
        }
    }

    @Override
    public List<Product> getCartItems(HttpSession session) {
        User user = userService.getCurrentUser();
        Cart cart = (Cart) session.getAttribute("cart");

        if (cart == null) {
            cart = cartRepository.findByUser(user);
            if (cart != null) {
                session.setAttribute("cart", cart);
            }
        }

        return cart != null ? cart.getProducts().stream().collect(Collectors.toList()) : null;
    }
}
