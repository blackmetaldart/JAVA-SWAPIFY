package com.example.springbootmonolith.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "song")
public class Song {

    // ID / TITLE / SONG-LENGTH / ARTIST COLUMNS FOR TABLE
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private Long songLength;

    @Column
    private String artist;

    // CONNECTS MANY SONGS TO MANY USERS
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH,
                    CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "user_song",
            joinColumns = {@JoinColumn(name = "song_id")},
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    // EMPTY SONG CONSTRUCTOR
    public Song() {}

    public Song(String title, Long songLength, String artist) {
        this.title = title;
        this.songLength = songLength;
        this.artist = artist;
    }

    // GETTERS AND SETTERS FOR THE COLUMNS
    public List<User> getUsers(){ return users; }

    public void setUsers(List<User> users) { this.users = users; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Long getSongLength() { return songLength; }
    public void setSongLength(Long songLength) { this.songLength = songLength; }

    public String getArtist() { return artist; }
    public void setArtist(String artist) { this.artist = artist; }

}
