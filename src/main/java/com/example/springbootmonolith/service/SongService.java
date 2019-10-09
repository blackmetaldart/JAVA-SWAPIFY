package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.Song;
import org.springframework.http.HttpStatus;

public interface SongService {

    public Song createSong(Song song);

    public Iterable<Song> listSongs();

    public void deleteSongById(Long songId);
}
