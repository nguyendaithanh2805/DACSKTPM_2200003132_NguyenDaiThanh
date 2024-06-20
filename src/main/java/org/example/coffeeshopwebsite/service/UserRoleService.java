package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;

public interface UserRoleService {
    void saveUserRole(UserRole userRole, User user, Role role);
}
