package com.qrowdsy.domain.service;

import java.util.Date;

import com.qrowdsy.domain.exception.DomainException;
import com.qrowdsy.domain.model.Book;
import com.qrowdsy.domain.model.id.BookId;

public interface BookService {
    BookId createBook(String name, String author, String genre, Date releaseDate) throws DomainException; 
    void updateBook(Book book) throws DomainException;
    void deleteBook(BookId id) throws DomainException;
}
