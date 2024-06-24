package org.example.coffeeshopwebsite.controller.user;

import jakarta.servlet.http.HttpSession;
import org.example.coffeeshopwebsite.model.Product;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.CartService;
import org.example.coffeeshopwebsite.service.ProductService;
import org.example.coffeeshopwebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CartController {
    private final CartService cartService;
    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/cart-add")
    public String addProductToCart(@RequestParam Long productId, HttpSession session) {
            cartService.addProductToCart(productId, session);
            return "redirect:/cart-view";
    }

    @GetMapping("/cart-view")
    public String showCart(HttpSession session, Model model) {
        List<Product> cartItems = cartService.getCartItems(session);
        model.addAttribute("cartItems", cartItems);
        return "user/cart";
    }
}
