package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.User;

public interface UserService {
    void saveUser(User user);
    User getCurrentUser();
}
