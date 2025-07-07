package com.example.Spotify.Controller;


import com.example.Spotify.model.dto.SongDto;
import com.example.Spotify.service.SongService;
import com.example.Spotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/song")
public class SongController {

    @Autowired
    private SongService songService;

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}/recommendByArtist")
    public List<SongDto> getRecommendedSongs(@PathVariable Long userId) {
        return songService.getRecommendedSongs(userId);
    }

    @GetMapping("/name")
    public List<SongDto> getSongByName(@RequestParam String songName) {
        return songService.findSongsByName(songName);
    }

    @GetMapping("/trending")
    public List<SongDto> getTrendingSongs(){
        return songService.getTrendingSongs();
    }
}
