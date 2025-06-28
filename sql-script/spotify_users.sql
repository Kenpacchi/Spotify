CREATE TABLE spotify_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255),
    phone_number VARCHAR(20) UNIQUE,
    email VARCHAR(255),
    password VARCHAR(255)
);
