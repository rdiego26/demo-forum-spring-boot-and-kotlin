CREATE TABLE IF NOT EXISTS topics(
    id BIGINT NOT NULL,
    title VARCHAR(50) NOT NULL,
    message VARCHAR(300) NOT NULL,
    created_at DATETIME NOT NULL,
    status VARCHAR(30) NOT NULL,
    course_id BIGINT NOT NULL,
    author_id BIGINT NOT NULL,
    primary key (id),
    foreign key(course_id) REFERENCES courses(id),
    foreign key(author_id) REFERENCES users(id)
);
