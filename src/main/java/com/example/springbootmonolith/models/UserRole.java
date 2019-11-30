package com.example.springbootmonolith.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity
@Table(name = "user_role")
public class UserRole {

    // ID / NAME COLUMNS FOR THE TABLE
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String name;

    // MAPS THE USER ROLE TO THE USERS
	@OneToMany(mappedBy = "userRole",
            cascade = CascadeType.ALL)
	private List<User> users;

	// EMPTY USERROLE CONSTRUCTOR
    public UserRole() {}

    public void addUser(User user){
        if(users == null)
            users = new ArrayList<>();
        users.add(user);
        user.setUserRole(this);
    }

    // GETTERS AND SETTERS FOR THE COLUMNS IN THE TABLE
    public void setUsers(List<User> users){ this.users = users; }
    public List<User> getUsers(){ return users; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
