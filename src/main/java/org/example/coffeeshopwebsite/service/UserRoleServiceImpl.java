package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.example.coffeeshopwebsite.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService{
    private final Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);
    private final UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }


    @Override
    public void saveUserRole(UserRole userRole, User user, Role role) {
        userRole.setUser(user);
        userRole.setRole(role);
        userRoleRepository.save(userRole);
        logger.info("Saved UserRole successfully");
    }
}
