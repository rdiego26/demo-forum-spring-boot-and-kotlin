CREATE TABLE IF NOT EXISTS courses(
    id bigint NOT NULL,
    name VARCHAR(50) NOT NULL,
    category VARCHAR (50),
    primary key(id)
);

-- only for development reasons
INSERT INTO courses values(1, 'Kotlin', 'programming-languages');