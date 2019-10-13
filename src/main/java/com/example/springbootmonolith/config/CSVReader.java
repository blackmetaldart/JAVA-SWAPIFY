package com.example.springbootmonolith.config;

import com.example.springbootmonolith.models.Song;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public static void main(String[] args) {
        List<Song> songs = readSongsFromCSV("/Users/marksman/Desktop/OwnWork/GADA-Work/GADA-PROJECTS/Swap-app/songs.csv");

        for(Song s : songs) {
            System.out.println(s);
        }
    }

    private static List<Song> readSongsFromCSV(String fileName) {
        List<Song> songs = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)) ){

            String line = br.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                Song song = createSong(attributes);

                songs.add(song);

                line = br.readLine();
            }


        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return songs;
    }

    private static Song createSong(String[] metadata) {
        String title = metadata[0];
        int songLength = Integer.parseInt(metadata[1]);
        String artist = metadata[2];

        return new Song(title, songLength, artist);
    }
}


