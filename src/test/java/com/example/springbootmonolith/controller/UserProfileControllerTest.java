package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.UserProfile;
import com.example.springbootmonolith.service.UserProfileServiceStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileControllerTest {
    private UserProfileController userProfileController;

    @Before
    public void initUserProfileController(){
        userProfileController = new UserProfileController();
            userProfileController = new UserProfileController();
            userProfileController.setUserProfileService(new UserProfileServiceStub());
    }

    @Test
    public void createUserProfile_SavesUserProfile_Success(0 throws Exception) {
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("user@gmail.com");

        UserProfile newProfile = userProfileController.createUserProfile("testuser", userProfile);
        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }
}
