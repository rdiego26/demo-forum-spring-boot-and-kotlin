CREATE TABLE IF NOT EXISTS users(
    id BIGINT NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    primary key (id)
);

INSERT INTO users VALUES(1, 'User #1', 'user_1@mail.com');