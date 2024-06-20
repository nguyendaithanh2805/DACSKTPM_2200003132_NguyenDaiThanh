package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;

public interface RoleService {
    void saveRole(Role role, User user);
}
