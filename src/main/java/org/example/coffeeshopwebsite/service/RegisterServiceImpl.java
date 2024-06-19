package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.model.UserRole;
import org.example.coffeeshopwebsite.repository.RoleRepository;
import org.example.coffeeshopwebsite.repository.UserRepository;
import org.example.coffeeshopwebsite.repository.UserRoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService{
    private final Logger logger = LoggerFactory.getLogger(RegisterServiceImpl.class);

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final UserRoleRepository userRoleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public RegisterServiceImpl(UserRepository userRepository, RoleRepository roleRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void saveRegister(User user) {
        try {
            Role role = new Role();
            UserRole userRole = new UserRole();
            user.setUsername(user.getUsername());
            logger.info("Saving user: " + user.getUsername());
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            logger.info("User saved successfully");
            userRepository.save(user);
            Role existingRole = roleRepository.findByName(user.getUsername());
            if (existingRole == null) {
                role.setName(user.getUsername());
                roleRepository.save(role);
                logger.info("Role saved successfully");
            } else
                return;
            userRole.setUser(user);
            userRole.setRole(role);
            logger.info(" userRole saved successfully");
            userRoleRepository.save(userRole);
        } catch (Exception e) {
            logger.info("Error saving user: " + e.getMessage());
        }
    }
}
