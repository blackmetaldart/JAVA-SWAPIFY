package com.example.springbootmonolith.controller;

import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.models.JwtResponse;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    //THIS ENDPOINT ALLOWS A USER TO LOGIN AND RECEIVE A TOKEN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }

    //THIS ENDPOINT ALLOWS A USER WITH THE ROLE ADMIN TO ACCESS A USER LIST
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("admin/user/list")
    public Iterable<User> listUsers() {
        return userService. listUsers();
    }

    //THIS ENDPOINT ALLOWS A USER TO SIGNUP AND RECEIVE A TOKEN
    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

    //THIS ENDPOINT ALLOWS A USER TO ADD A SONG TO THEIR PROFILE
    @PutMapping("/user/{username}/{songId}")
    public User addSong(@PathVariable String username, @PathVariable Long songId){
        return userService.addSong(username, songId);
    }

    //THIS ENDPOINT ALLOWS A USER WITH THE ROLE ADMIN TO DELETE A SONG BY ITS ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        return userService.deleteById(userId);
    }

    //THIS ENDPOINT ALLOWS A USER WITH THE ROLE ADMIN TO ACCESS THE HELLO WORLD METHOD
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!!";
    }

}
