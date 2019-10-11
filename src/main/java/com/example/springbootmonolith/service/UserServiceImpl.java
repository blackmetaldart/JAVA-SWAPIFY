package com.example.springbootmonolith.service;

import com.example.springbootmonolith.config.JwtUtil;
import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.models.User;
import com.example.springbootmonolith.models.UserRole;
import com.example.springbootmonolith.repositories.SongRepository;
import com.example.springbootmonolith.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleService userRoleService;

    @Autowired
    SongService songService;

    @Autowired
    SongRepository songRepository;

    @Autowired
    JwtUtil jwtUtil;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    //THIS METHOD
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }

    //THIS METHOD
    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }

    //THIS METHOD RETURNS A USER IN THE USER REPOSITORY THAT MATCHES THE PASSED STRNG 'USERNAME'
    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username);
    }

    //THIS METHOD RETURNS ALL THE USERS IN THE USER REPOSITORY
    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    //THIS METHOD CREATES A USER AFTER SIGNUP AND RETURNS A TOKEN
    @Override
    public String createUser(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    //THIS METHOD GENERATES A TOKEN IF THE USERNAME AND PASSWORD MATCH THAT OF A USER IN THE USER REPOSITORY
    @Override
    public String login(User user){
        User newUser = userRepository.findByUsername(user.getUsername());
        //userRepository.login(user.getUsername(), user.getPassword()) != null
        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    //THIS ADDS A SONG IN THE REPOSITORY TO A USER'S SONG LIST AND UPDATES THE REPOSITORY
    @Override
    public User addSong(String username, Long songId) {
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.addSong(song);

        return userRepository.save(user);
    }

    //THIS DELETES A USER BY THE USER ID
    @Override
    public HttpStatus deleteById(Long userId){
        userRepository.deleteById(userId);
        return HttpStatus.OK;
    }
}
