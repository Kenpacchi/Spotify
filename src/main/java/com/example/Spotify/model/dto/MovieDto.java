package com.example.Spotify.model.dto;

import lombok.*;

import java.util.List;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {
    private Long movieId;
    private List<SongDto> movieSong;
}
