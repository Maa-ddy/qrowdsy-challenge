package com.qrowdsy.application.controller;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qrowdsy.application.controller.dto.BookDto;
import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @PostMapping
    public UUID addBook(BookDto bookDto) throws DomainException {
        return bookService.createBook(
            bookDto.name(),
            bookDto.author(),
            bookDto.genre(),
            bookDto.releaseDate()
        ).rawId();
    }
}
