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

@Service
public class SongService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    public List<Song> getRecommendedSongs(Long userId) {
        List<Song> userPlaylist = userRepository.findById(userId).map(User::getUserPlaylist).orElse(List.of());

        Set<String> artists = new HashSet<>();

        for (Song song : userPlaylist) {
            artists.add(song.getArtist().getArtistName());
        }
        return songRepository.findAllSongByArtistNames(artists);
    }

    public List<Song> findSongsByName(String name) {
        return songRepository.findAllSongBySongName(name);
    }
}
