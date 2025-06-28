package com.example.Spotify.service;


import com.example.Spotify.model.entities.Artist;
import com.example.Spotify.model.entities.Song;
import com.example.Spotify.model.entities.User;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private SongRepository songRepo;


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

    public void addToPlaylist(Long userId, String songName) {
        User user = userRepo.findById(userId).orElseThrow();
        Song song = songRepo.findBySongName(songName).orElseThrow();
        user.getUserPlaylist().add(song);
        userRepo.save(user);
    }

    public List<Song> getPlaylist(Long userId) {
        return userRepo.findById(userId).map(User::getUserPlaylist).orElse(List.of());
    }
}
