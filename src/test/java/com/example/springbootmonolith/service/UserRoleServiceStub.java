package com.example.springbootmonolith.service;
import com.example.springbootmonolith.models.UserRole;


public class UserRoleServiceStub implements UserRoleService {
    @Override
    public UserRole createRole(UserRole newRole) {

        return newRole;
    }

    @Override
    public UserRole getRole(String roleName) {
        return null;
    }
}
