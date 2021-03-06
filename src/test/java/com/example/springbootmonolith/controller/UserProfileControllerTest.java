package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.UserProfile;
import com.example.springbootmonolith.repository.UserProfileRepositoryStub;
import com.example.springbootmonolith.service.UserProfileServiceImpl;
import com.example.springbootmonolith.service.UserProfileServiceStub;
import com.example.springbootmonolith.service.UserServiceStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserProfileControllerTest {
    private UserProfileController userProfileController;

    @Before
    public void initializeUserProfileController(){
        userProfileController = new UserProfileController();
        userProfileController.setUserProfileService(new UserProfileServiceStub());
    }

    @Test
    public void createUserProfile_SaveUserProfile_Success(){
        UserProfile userProfile = new UserProfile();
        userProfile.setEmail("batman@superhero.com");

        UserProfile newProfile = userProfileController.createUserProfile("batman", userProfile);

        Assert.assertNotNull(newProfile);
        Assert.assertEquals(newProfile.getEmail(), userProfile.getEmail());
    }

}
