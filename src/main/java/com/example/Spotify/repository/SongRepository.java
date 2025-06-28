package com.example.Spotify.repository;

import com.example.Spotify.model.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findBySongName(String name);
    List<Song> findAllByArtist_ArtistName(String artistName);
    List<Song> findAllByMovie_MovieId(Long movieId);
}