package org.example.coffeeshopwebsite.controller.admin;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.example.coffeeshopwebsite.service.RoleService;
import org.example.coffeeshopwebsite.service.UserRoleService;
import org.example.coffeeshopwebsite.service.UserService;
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
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    public RegisterController(UserService userService, RoleService roleService, UserRoleService userRoleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/register")
    public String createAccount(Model model) {
        model.addAttribute("user", new User());
        return "admin/register";
    }

    @PostMapping("/register-save")
    public String saveAccount(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        Role role = new Role();
        roleService.saveRole(role, user);
        UserRole userRole = new UserRole();
        userRoleService.saveUserRole(userRole, user,role);
        return "redirect:/admin/login";
    }
}
