package com.example.springbootmonolith.controller;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.service.UserRoleServiceStub;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserRoleControllerTest {

    private UserRoleController userRoleController;

    @Before
    public void initializeUserProfileController(){
        userRoleController = new UserRoleController();
        userRoleController.setUserRoleService(new UserRoleServiceStub());
    }

    @Test
    public void createRole_success(){
        UserRole newRole = new UserRole();
        newRole.setName("admin");
        UserRole testRole = userRoleController.createRole(newRole);

        Assert.assertNotNull(testRole);
        Assert.assertEquals("admin",testRole.getName());

    }
}
