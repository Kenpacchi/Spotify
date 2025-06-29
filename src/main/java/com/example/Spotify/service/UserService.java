package com.example.Spotify.service;


import com.example.Spotify.model.entities.Artist;
import com.example.Spotify.model.entities.Playlist;
import com.example.Spotify.model.entities.Song;
import com.example.Spotify.model.entities.User;
import com.example.Spotify.repository.PlaylistRepository;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SongRepository songRepo;

    @Autowired
    private PlaylistRepository playlistRepo;


    public String signUp(User user) {
        if (userRepo.findByUserPhoneNumber(user.getUserPhoneNumber()).isPresent()) {
            return "User already exists. Please login.";
        }
        userRepo.save(user);
        return "Signup successful!";
    }

    public String login(String phoneNumber, String password) {
        return userRepo.findByUserPhoneNumber(phoneNumber)
                .filter(u -> u.getUserPassword().equals(password))
                .map(u -> "Login success!")
                .orElse("Invalid credentials.");
    }

    public void addToPlaylist(Long userId, String playlistName, String songName) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Song song = songRepo.findBySongName(songName).orElseThrow(() -> new RuntimeException("Song not found"));

        Playlist playlist = playlistRepo.findByUserAndName(user, playlistName)
                .orElseThrow(() -> new RuntimeException("Playlist not found"));

        playlist.getSongs().add(song);
        playlistRepo.save(playlist);
    }

    public List<Song> getPlaylist(Long userId, String playlistName) {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        return playlistRepo.findByUserAndName(user, playlistName)
                .map(Playlist::getSongs)
                .orElse(List.of());
    }

    public ResponseEntity<Playlist> getPlaylistByName(String mobileNumber, String playlistName) {
        User user = userRepo.findByUserPhoneNumber(mobileNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        Playlist playlist = playlistRepo.findByUserAndName(user, playlistName)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Playlist not found"));

        playlist.setUser(null);
        return ResponseEntity.ok(playlist);
    }
}
