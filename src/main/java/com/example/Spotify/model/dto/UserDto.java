package com.example.Spotify.model.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String userPhoneNumber;
    private String userEmail;
    private String userPassword;
    private List<PlaylistDto> playlists = new ArrayList<>();
}
