package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    private final UserService userService;
    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @Autowired
    public RegisterServiceImpl(UserService userService, RoleService roleService, UserRoleService userRoleService) {
        this.userService = userService;
        this.roleService = roleService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void saveRegisterAccount(User user) {
        userService.saveUser(user);
        Role role = new Role();
        roleService.saveRole(role, user);
        UserRole userRole = new UserRole();
        userRoleService.saveUserRole(userRole, user,role);
    }
}
