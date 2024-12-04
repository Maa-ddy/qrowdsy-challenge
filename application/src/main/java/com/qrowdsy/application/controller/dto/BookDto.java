package com.qrowdsy.application.controller.dto;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.qrowdsy.domain.model.Book;

public record BookDto(UUID id, String name, String author, String genre, @DateTimeFormat(pattern="yyyy-MM-dd") Date releaseDate) {
    public static BookDto fromModel(Book book) {
        return new BookDto(
            book.id().rawId(),
            book.name(),
            book.author(),
            book.genre(),
            book.releaseDate()
        );
    }
}
