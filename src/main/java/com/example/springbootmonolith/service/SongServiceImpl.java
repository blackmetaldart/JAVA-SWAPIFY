package com.example.springbootmonolith.service;

import com.example.springbootmonolith.models.Song;
import com.example.springbootmonolith.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongRepository songRepository;

    /**
     * The song repository extends the CrudRepository which has a save method
     * @param song
     * @return
     */
    @Override
    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    /**
     * The SongRepository extends the CrudRepository which has a findAll method
     * @return
     */
    @Override
    public Iterable<Song> listSongs(){
        return songRepository.findAll();
    }

    @Override
    public void deleteSongById(Long songId){
         songRepository.deleteById(songId);
    }
}
