package org.example.coffeeshopwebsite.service;

import org.example.coffeeshopwebsite.model.Role;
import org.example.coffeeshopwebsite.model.User;
import org.example.coffeeshopwebsite.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService{
    private final Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;
    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void saveRole(Role role, User user) {
            if (!(user.getUsername().equalsIgnoreCase("ADMIN"))) {
                role.setId(1L);
                role.setName("USER");
            } else
            {
                role.setId(2L);
                role.setName("ADMIN");
            }
        roleRepository.save(role);
        logger.info("Saved role successfully");
    }
}
