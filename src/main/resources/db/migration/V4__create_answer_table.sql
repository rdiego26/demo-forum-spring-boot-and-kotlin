CREATE TABLE IF NOT EXISTS answers(
    id BIGINT NOT NULL,
    message VARCHAR(300) NOT NULL,
    created_at DATETIME NOT NULL,
    topic_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    solution INT NOT NULL,
    primary key (id),
    foreign key(topic_id) REFERENCES topics(id),
    foreign key(author_id) REFERENCES users(id)
);
