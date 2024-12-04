package com.qrowdsy.application.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping
    public List<BookDto> filterBooks(
        @RequestParam String criteria, 
        @RequestParam int offset, 
        @RequestParam int limit
    ) throws DomainException {
        // with iterator
        /*
        List<BookDto> result = new ArrayList<>();
        for (var book: bookService.filterBooks(criteria).offset(offset)) {
            if (limit-- == 0) break;
            result.add(BookDto.fromModel(book));
        }
        return result;
        */
        // with List slice
        return bookService.filterBooks(criteria).slice(offset, offset + limit - 1)
            .stream()
            .map(BookDto::fromModel)
            .collect(Collectors.toList());
    }
    
}
