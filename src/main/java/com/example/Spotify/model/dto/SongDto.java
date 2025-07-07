package com.example.Spotify.model.dto;

import com.example.Spotify.model.entities.Artist;
import com.example.Spotify.model.entities.Movie;
import lombok.*;

import java.util.Date;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SongDto {
    private Long songId;
    private String songName;
    private String artistName;
    private String movieName;
    private Long views;
    private int duration;
    private Date uploadDate;

    public SongDto(Long songId, String songName, Artist artist, Movie movie, Long views, int duration, Date uploadDate) {
        this.songId = songId;
        this.songName = songName;
        this.artistName = (artist != null) ? artist.getArtistName() : null; // handle null artist
        this.movieName = null;
        this.views = views;
        this.duration = duration;
        this.uploadDate = uploadDate;
    }

}
