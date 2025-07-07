package com.example.Spotify.model.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistDto {
    private Long id;
    private String name;
    private Long userId; // Reference to the User who owns the playlist
    private List<SongDto> songs;
}
