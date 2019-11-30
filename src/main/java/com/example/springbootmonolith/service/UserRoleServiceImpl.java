package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleRepository userRoleRepository;

    // THIS METHOD SAVES A USER ROLE IN THE USER ROLE REPOSITORY
    @Override
    public UserRole createRole(UserRole newRole) {
        return userRoleRepository.save(newRole);
    }

    // THIS METHOD FINDS A USER ROLE IN THE REPOSITORY BY COMPARING IT WITH THE PASSED STRING PARAMETER
    @Override
    public UserRole getRole(String roleName) {
        return userRoleRepository.findByName(roleName);
    }
}
