package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.UserRole;
import org.springframework.data.repository.CrudRepository;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {

    // FIND A USER ROLE WITH A NAME
    public UserRole findByName(String name);
}
