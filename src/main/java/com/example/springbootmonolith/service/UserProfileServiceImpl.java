package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserProfile;
import com.example.springbootmonolith.repositories.UserProfileRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileServiceImpl implements UserProfileService {

    @Autowired
    UserProfileRepository userProfileRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    //THIS METHOD SETS A USER FOR A USERPROFILE AND SAVES IT IN THE USER PROFILE REPOSITORY
    @Override
    public UserProfile createUserProfile(String username, UserProfile newProfile) {
        User user = userService.getUser(username);
        newProfile.setUser(user);
        user.setUserProfile(newProfile);

        return userProfileRepository.save(newProfile);

    }

    //THIS METHOD FINDS A PROFILE IN THE USER PROFILE REPOSITORY USING A USERNAME
    @Override
    public UserProfile getUserProfile(String username) {
        return userProfileRepository.findProfileByUsername(username);
    }
}