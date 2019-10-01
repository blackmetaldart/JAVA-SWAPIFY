package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.repositories.SongRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest2 {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private  SongService songService;

    @Mock
    private SongRepository songRepository;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private JwtUtil jwtUtil;

    @InjectMocks
    private User user;


    @Before
    public void initDummyUser(){
        user.setUsername("batman");
        user.setPassword("hello123");
    }

    @Test
    public void getUser_ReturnUser_Success(){

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User tempUser = userService.getUser(user.getUsername());
        assertEquals(tempUser.getUsername(), user.getUsername());
    }

    @Test
    public void login_UserNotFound_Error(){
        when(userRepository.findByUsername(anyString())).thenReturn(user);
        String token = userService.login(user);
        assertEquals(token, null);
    }

    @Test
    public void createUser_Success(){
        when()
    }
}
