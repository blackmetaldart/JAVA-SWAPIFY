package com.example.springbootmonolith.controller;


import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/song")
public class SongController {

    @Autowired
    SongService songService;

    @PostMapping
    public Song createSong(@RequestBody Song song){
        return songService.createSong(song);
    }

    @GetMapping("/list")
    public Iterable<Song> listSongs(){
        return songService.listSongs();
    }

}
