CREATE TABLE posts
(
    id SERIAL PRIMARY KEY,
    author_id INT NOT NULL REFERENCES "users" (id),
    content VARCHAR(100) NOT NULL UNIQUE,
    creation_time TIMESTAMP NOT NULL
);