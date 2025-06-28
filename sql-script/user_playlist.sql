CREATE TABLE user_playlist (
    user_id BIGINT REFERENCES users(id),
    song_id BIGINT REFERENCES songs(song_id),
    PRIMARY KEY (user_id, song_id)
);