package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.UserProfile;
import com.example.springbootmonolith.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class UserProfileController {

    @Autowired
    public void setUserProfileService(UserProfileService userProfileService){
        this.userProfileService = userProfileService;
    }

    //THIS ENDPOINT ALLOWS A USER TO GET A PROFILE WITH A USERNAME
    @GetMapping("/{username}")
    public UserProfile getUserProfile(@PathVariable String username) {
        return userProfileService.getUserProfile(username);
    }

    //THIS ENDPOINT ALLOWS A USER TO CREATE A PROFILE USING THE USERNAME IN THE HEADER AND THE REQUEST BODY
    @PostMapping("/{username}")
    public UserProfile createUserProfile(@PathVariable String username, @RequestBody UserProfile userProfile) {
        return userProfileService.createUserProfile(username, userProfile);
    }

    private UserProfileService userProfileService;


}
