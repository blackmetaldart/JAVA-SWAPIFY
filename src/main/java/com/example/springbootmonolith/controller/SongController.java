package com.example.springbootmonolith.controller;


import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    //THIS ENDPOINT ALLOWS A USER WITH THE ARTIST ROLE TO CREATE A SONG
    @PreAuthorize("hasRole('ARTIST')")
    @PostMapping
    public Song createSong(@RequestBody Song song){
        return songService.createSong(song);
    }

    //THIS ENDPOINT ALLOWS A USER TO ACCESS THE SONG LIST
    @GetMapping("/list")
    public Iterable<Song> listSongs(){
        return songService.listSongs();
    }

    //THIS ENDPOINT ALLOWS THE USER WITH THE ROLE ADMIN TO DELETE A SONG BY ITS ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{songId}")
    public void deleteSongById(@PathVariable Long songId) {
        songService.deleteSongById(songId);
    }


}
