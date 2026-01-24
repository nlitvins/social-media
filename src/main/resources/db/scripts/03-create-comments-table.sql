CREATE TABLE comments
(
    id SERIAL PRIMARY KEY,
    author_id INT NOT NULL REFERENCES "users" (id),
    post_id INT NOT NULL REFERENCES "posts" (id),
    content VARCHAR(100) NOT NULL,
    creation_time TIMESTAMP NOT NULL
);