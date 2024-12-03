package com.qrowdsy.application.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.qrowdsy.application.controller.BookController;
import com.qrowdsy.application.controller.LibraryController;
import com.qrowdsy.domain.repository.BookRepository;
import com.qrowdsy.domain.repository.LibraryRepository;
import com.qrowdsy.domain.service.BookService;
import com.qrowdsy.domain.service.LibraryManagementService;
import com.qrowdsy.domain.service.LibrarySearchService;
import com.qrowdsy.domain.service.LibraryService;
import com.qrowdsy.domain.service.impl.BookServiceImpl;
import com.qrowdsy.domain.service.impl.LibraryManagementServiceImpl;
import com.qrowdsy.domain.service.impl.LibrarySearchServiceImpl;
import com.qrowdsy.domain.service.impl.LibraryServiceImpl;

@Configuration
public class ApplicationConfiguration {

    @Bean public BookController bookController(BookService bookService) {
        return new BookController(bookService);
    }

    @Bean public LibraryController libraryController(
        LibraryService libraryService,
        LibrarySearchService librarySearchService,
        LibraryManagementService libraryManagementService
    ) {
        return new LibraryController(libraryService, librarySearchService, libraryManagementService);
    }

    @Bean public BookService bookService(BookRepository bookRepository, LibraryRepository libraryRepository) {
        return new BookServiceImpl(bookRepository, libraryRepository);
    }

    @Bean public LibraryService libraryService(LibraryRepository libraryRepository) {
        return new LibraryServiceImpl(libraryRepository);
    }

    @Bean public LibrarySearchService librarySearchService(LibraryRepository libraryRepository, BookRepository bookRepository) {
        return new LibrarySearchServiceImpl(libraryRepository, bookRepository);
        
    }

    @Bean public LibraryManagementService libraryManagementService(LibraryRepository libraryRepository) {
        return new LibraryManagementServiceImpl(libraryRepository);
    }
    
}
