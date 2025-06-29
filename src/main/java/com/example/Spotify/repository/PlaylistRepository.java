package com.example.Spotify.repository;

import com.example.Spotify.model.entities.Playlist;
import com.example.Spotify.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    Optional<Playlist> findByUserAndName(User user, String name);
}
