package com.example.springbootmonolith.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "userProfile")
public class UserProfile {

    //ID / EMAIL/ MOBILE/ ADDRESS / USERID COLUMNS FOR TABLES
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String mobile;

    @Column
    private String address;

    @Column
    private Long userId;

    //MAPS THE USER PROFILES TO THE USERS
    @JsonIgnore
    @OneToOne(mappedBy = "userProfile",
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    private User user;

    //EMPTY USERPROFILE CONSTRUCTOR
    public UserProfile(){}

    //GETTERS AND SETTERS FOR THE COLUMNS
    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getUserId() {return userId; };

    public void setUserId(Long userId) {this.userId = userId;};

}
