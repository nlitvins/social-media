INSERT INTO users (username)
VALUES ('alice'),
       ('bob'),
       ('charlie'),
       ('diana'),
       ('eve');

INSERT INTO posts (author_id, content, creation_time)
VALUES (1, 'Hello world', '2025-01-01 10:00:00'),
       (2, 'My first post', '2025-01-02 11:15:00'),
       (3, 'Post about SQL', '2025-01-03 09:30:00'),
       (4, 'Thoughts of the day', '2025-01-04 18:45:00'),
       (5, 'Random notes', '2025-01-05 22:10:00');


INSERT INTO comments (author_id, post_id, content, creation_time)
VALUES (2, 1, 'Nice post', '2025-01-01 10:05:00'),
       (3, 1, 'Agree with you', '2025-01-01 10:10:00'),
       (1, 2, 'Good luck', '2025-01-02 11:20:00'),
       (5, 3, 'SQL is powerful', '2025-01-03 09:45:00'),
       (4, 4, 'Interesting thought', '2025-01-04 19:00:00');
