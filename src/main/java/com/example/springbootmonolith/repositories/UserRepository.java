package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    // RETURNS A USER WITH A USERNAME AND PASSWORD
    @Query("FROM User u WHERE u.username = ?1 and u.password = ?2")
    public User login(String username, String password);

    // RETURNS A USER WITH A USERNAME
    public User findByUsername(String username);

}