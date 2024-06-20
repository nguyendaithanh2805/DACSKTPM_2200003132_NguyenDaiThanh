package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void saveUser(User user) {
        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null)
            throw new IllegalArgumentException("User with username " + user.getUsername() + "already exists");
        user.setPassword(bCryptPasswordEncoder.encode(user.getUsername()));
        userRepository.save(user);
        logger.info("Saved user successfully");
    }
}
