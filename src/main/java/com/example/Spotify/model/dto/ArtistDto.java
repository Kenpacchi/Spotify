package com.example.Spotify.model.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtistDto {
    private Long artistId;
    private String artistName;
    private List<SongDto> artistSongs;
}
