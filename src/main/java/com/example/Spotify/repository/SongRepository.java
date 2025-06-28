package com.example.Spotify.repository;

import com.example.Spotify.model.entities.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface SongRepository extends JpaRepository<Song, Long> {
    Optional<Song> findBySongName(String name);
    List<Song> findAllByArtist_ArtistName(String artistName);
    List<Song> findAllByMovie_MovieId(Long movieId);

    @Query("SELECT s FROM Song s JOIN FETCH s.artist a WHERE a.artistName IN :artists")
    List<Song> findAllSongByArtistNames(@Param("artists") Set<String> artistNames);

    @Query("SELECT s FROM Song s WHERE LOWER(s.songName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Song> findAllSongBySongName(@Param("name") String songName);
}