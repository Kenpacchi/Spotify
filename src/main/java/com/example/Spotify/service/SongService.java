package com.example.Spotify.service;

import com.example.Spotify.model.dto.SongDto;
import com.example.Spotify.model.entities.Song;
import com.example.Spotify.model.entities.User;
import com.example.Spotify.repository.SongRepository;
import com.example.Spotify.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SongService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SongRepository songRepository;

    public List<SongDto> getRecommendedSongs(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Set<String> artistNames = user.getPlaylists().stream()
                .flatMap(playlist -> playlist.getSongs().stream())
                .map(song -> song.getArtist().getArtistName())
                .collect(Collectors.toSet());
        List<SongDto> RecommendationDto =new ArrayList<>();
        for (Song song : songRepository.findAllByArtistNameIn(artistNames)) {
            SongDto songDto = new SongDto(song.getSongId(), song.getSongName(), song.getArtist(), song.getMovie(), song.getViews(), song.getDuration(), song.getUploadDate());
            RecommendationDto.add(songDto);
        }
        return RecommendationDto;
    }
    public List<SongDto> getTrendingSongs() {
        List<Song> trending = songRepository.findAll();
        trending.sort((a, b) -> {
            if (Objects.equals(a.getViews(), b.getViews())) {
                // If views are same, compare upload dates (recent first)
                return b.getUploadDate().compareTo(a.getUploadDate());
            }
            // Otherwise, compare views (higher views first)
            return Long.compare(b.getViews(), a.getViews());
        });
        List<SongDto> trendingDto=new ArrayList<>();
        for (Song song : trending) {
            SongDto songDto = new SongDto(song.getSongId(), song.getSongName(), song.getArtist(), song.getMovie(), song.getViews(), song.getDuration(), song.getUploadDate());
            trendingDto.add(songDto);
        }
        return trendingDto;
    }

    public List<SongDto> findSongsByName(String name) {
        List<SongDto> songByNameDto =new ArrayList<>();
        for (Song song : songRepository.findAllSongBySongName(name)) {
            SongDto songDto = new SongDto(song.getSongId(), song.getSongName(), song.getArtist(), song.getMovie(), song.getViews(), song.getDuration(), song.getUploadDate());
            songByNameDto.add(songDto);
        }
        return songByNameDto;
    }
}
