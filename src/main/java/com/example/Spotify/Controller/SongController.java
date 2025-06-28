package com.example.Spotify.Controller;


import com.example.Spotify.model.entities.Song;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private SongService songService;

    @GetMapping("/{userId}/recommendByArtist")
    public List<Song> getRecommendedSongs(@PathVariable Long userId) {
        return songService.getRecommendedSongs(userId);
    }

    @GetMapping("/name")
    public List<Song> getSongByName(@RequestParam String songName) {
        return songService.findSongsByName(songName);
    }
}
