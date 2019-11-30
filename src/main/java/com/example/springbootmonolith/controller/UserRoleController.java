package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class UserRoleController {

    private UserRoleService roleService;

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService){
        this.roleService=userRoleService;
    }

    // THIS METHOD ALLOWS A USER WITH THE ROLE OF ADMIN TO SEE A LIST OF THOSE WITH SPECIFIC ACCESS
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{rolename}")
    public UserRole getRole(@PathVariable String rolename) {
        return roleService.getRole(rolename);
    }

    // THIS METHOD ALLOWS A USER WITH THE ROLE OF ADMIN TO CREATE A ROLE TO GRANT ACCESS
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/createRole")
    public UserRole createRole(@RequestBody UserRole role) {
        return roleService.createRole(role);
    }

}
