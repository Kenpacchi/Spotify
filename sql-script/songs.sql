CREATE TABLE songs (
    song_id BIGSERIAL PRIMARY KEY,
    artist_id BIGINT REFERENCES artists(artist_id),
    movie_id BIGINT REFERENCES movies(movie_id),
    name VARCHAR(255),
    views BIGINT,
    upload_date DATE
);