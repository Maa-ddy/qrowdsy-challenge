CREATE TABLE IF NOT EXISTS book (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    release_date timestamp
);

CREATE TABLE IF NOT EXISTS library (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    town VARCHAR(255) NOT NULL,
    post_code VARCHAR(255) NOT NULL,
    phone_number VARCHAR(255) NOT NULL,
    email_address VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS book_library (
    id UUID PRIMARY KEY,
    book_id UUID NOT NULL,
    library_id UUID NOT NULL,
    quantity INTEGER,
    CONSTRAINT book_library_book FOREIGN KEY (book_id) REFERENCES book(id),
    CONSTRAINT book_library_library FOREIGN KEY (library_id) REFERENCES library(id)
);