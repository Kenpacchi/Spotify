package com.example.Spotify.Controller;

import com.example.Spotify.model.entities.Song;
import com.example.Spotify.model.entities.User;
import com.example.Spotify.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/signup")
    public String signup(@RequestBody User user) {
        return service.signUp(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String phone, @RequestParam String password) {
        return service.login(phone, password);
    }

    @PostMapping("/{userId}/add-to-playlist")
    public String addToPlaylist(@PathVariable Long userId, @RequestParam String songName) {
        service.addToPlaylist(userId, songName);
        return "Song added to playlist.";
    }
}
