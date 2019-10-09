package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserProfileRepository extends CrudRepository<UserProfile, Long> {

    @Query("FROM UserProfile UserP INNER JOIN User u ON u.username = ?1 AND UserP.id = u.userProfile.id")
    public UserProfile findProfileByUsername(String username);

}
