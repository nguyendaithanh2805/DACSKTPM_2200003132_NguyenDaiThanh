package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class RegisterController {
    private final RegisterService registerService;

    @Autowired
    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @GetMapping("/register")
    public String createAccount(Model model) {
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register-save")
    public String saveAccount(@ModelAttribute("user") User user, Model model) {
        try
        {
            registerService.saveRegisterAccount(user);
            return "redirect:/admin/login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "admin/register";
        }
    }
}
