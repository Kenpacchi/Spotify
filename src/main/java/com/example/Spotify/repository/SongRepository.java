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

    @Query("SELECT s FROM Song s WHERE LOWER(s.songName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Song> findAllSongBySongName(@Param("name") String songName);

    @Query("SELECT s FROM Song s WHERE s.artist.artistName IN :artistNames")
    List<Song> findAllByArtistNameIn(@Param("artistNames") Set<String> artistNames);

}