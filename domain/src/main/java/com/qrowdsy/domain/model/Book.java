package com.qrowdsy.domain.model;

import java.util.Date;

import com.qrowdsy.domain.model.id.BookId;

public class Book {

    private final BookId id;
    private final String name;
    private final String author;
    private final String genre;
    private final Date releaseDate;

    private Book(BookId id, String name, String author, String genre, Date releaseDate) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }

    public Book(String name, String author, String genre, Date releaseDate) {
        this(new BookId(), name, author, genre, releaseDate);
    }

    public static Book of(BookId id, String name, String author, String genre, Date releaseDate) {
        return new Book(id, name, author, genre, releaseDate);
    }

    public BookId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String author() {
        return author;
    }

    public String genre() {
        return genre;
    }

    public Date releaseDate() {
        return releaseDate;
    }
    
}
