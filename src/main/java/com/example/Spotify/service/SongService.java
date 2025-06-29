package com.example.Spotify.service;

import com.example.Spotify.model.entities.Song;
import com.example.Spotify.model.entities.User;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.repository.UserRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Song> getRecommendedSongs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> artistNames = user.getPlaylists().stream()
                .flatMap(playlist -> playlist.getSongs().stream())
                .map(song -> song.getArtist().getArtistName())
                .collect(Collectors.toSet());

        return songRepository.findAllByArtistNameIn(artistNames);
    }

    public List<Song> findSongsByName(String name) {
        return songRepository.findAllSongBySongName(name);
    }
}
